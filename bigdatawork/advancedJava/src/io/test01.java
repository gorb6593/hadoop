package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class test01 {
	public static void main(String[] args) throws IOException{
		
		
		FileReader fis = null;
		FileWriter fw = null;
				
		try {
			fis = new FileReader("src/io/FileTest01.java");
			fw = new FileWriter("src/data/copy_result.txt", true);	
			while(true) {
				int data = fis.read();
				if(data==-1) { 
					break;
				}
				fw.write(data);					
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {			
			try {
				if(fis!=null) fis.close();
				if(fw!=null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}			
}

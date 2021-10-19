package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest {
	public static void main(String[] args) {
		/*
		 * 1. 파일오픈 - API를 이용해서 작업(File IO를 처리하는 API를 생성하면 내부에서 자동으로 open)
		 * 2. 파일액세스(반복작업)
		 * 3. 파일닫기 - 쓰기작업인 경우 반드시 필요
		 */
		FileInputStream fis = null;
		//1. 파일오픈
		try {
			fis = new FileInputStream("src/data/input.txt");
		//2. 파일액세스
			while(true) {
				int data = fis.read();
				if(data==-1) { //파일의 끝을 만나면 -1을 리턴한다
					break;
				}
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//3. 파일닫기
			try {
				if(fis!=null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

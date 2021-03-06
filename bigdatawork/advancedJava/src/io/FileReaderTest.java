package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
	public static void main(String[] args) {
		/*
		 * 1. 파일오픈 - API를 이용해서 작업(File IO를 처리하는 API를 생성하면 내부에서 자동으로 open)
		 * 2. 파일액세스(반복작업)
		 * 3. 파일닫기 - 쓰기작업인 경우 반드시 필요
		 */
		FileReader fis = null;
		//1. 파일오픈
		try {
			fis = new FileReader("src/data/input.txt");
			int count = 0; //실행횟수
			long start = System.nanoTime(); //실행시간
		//2. 파일액세스
			while(true) {
				int data = fis.read();
				if(data==-1) { //파일의 끝을 만나면 -1을 리턴한다
					break;
				}
				System.out.print((char)data);
				count++;
			}
			long end = System.nanoTime();
			System.out.println("실행횟수=>"+count);
			System.out.println("실행시간=>"+(end-start));
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

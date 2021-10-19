package io;

import java.io.IOException;
import java.io.InputStream;

//InputStream을 이용해서 키보드로 입력받는 문자열을 읽기
public class InputStreamTest {
	public static void main(String[] args) {
		InputStream in = System.in;
		
		try {
			while(true) {				
				int data = in.read();
				if(data==13) {
					break;
				}
				System.out.print((char)data);
				}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
}

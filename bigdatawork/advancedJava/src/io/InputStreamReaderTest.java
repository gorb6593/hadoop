package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//InputStream을 이용해서 키보드로 입력받는 문자열을 읽기
public class InputStreamReaderTest {
	public static void main(String[] args) {
		InputStreamReader in = new InputStreamReader(System.in);
		//InputStream을 InputStreamReader로 변경해서 키보드로 입력한 한글이 잘 출력될 수 있도록 변경
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

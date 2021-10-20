package util;

import java.util.StringTokenizer;

public class StringTokenizerTest {
	public static void main(String[] args) {
		//String클래스의 split과 같은 역할
		String text = "read a book";
		StringTokenizer st = new StringTokenizer(text);
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
		}
		
	}
}

package io;

import java.io.File;
import java.text.SimpleDateFormat;

public class FileTest01 {
	public static void main(String[] args) {
		//엑세스하고 싶은 파일이나 폴더의 위치를 file객체를 만들 때 매개변수로 정의
		//c:/bigdata_iot or c:\\iot
		//File file1 = new File("c:\\bigdata_iot");
		File file1 = new File("c:/bigdata_iot");
		//경로를 정의하지 않으면 기본으로 인식하는 위치가 프로젝트 폴더
		File file2 = new File("test.txt");
		//기본위치에서 src폴더를 찾아서 접근하는 방식
		File file3 = new File("src/data/test.txt");//상대경로
		System.out.println(file1);
		System.out.println(file2);
		System.out.println(file3);
		
		System.out.println("file3.canRead()=>"+file3.canRead());
		System.out.println("file3.canWrite()=>"+file3.canWrite());
		System.out.println("file3.getName()=>"+file3.getName());
		System.out.println("file3.getAbsolutePath()=>"+file3.getAbsolutePath());
		System.out.println("file3.getParent()=>"+file3.getParent());
		System.out.println("file3.getPath()=>"+file3.getPath());
		System.out.println("file3.isDirectory()=>"+file3.isDirectory());
		System.out.println("file1.isDirectory()=>"+file1.isDirectory());
		System.out.println("file3.isFile()=>"+file3.isFile());
		System.out.println("file1.isFile()=>"+file1.isFile());
		System.out.println("file3.isHidden()=>"+file3.isHidden());
		System.out.println("file3.lastModified()=>"+file3.lastModified());
		//마지막 접속한 날짜를 년 월 일의 형식으로 출력
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		String dateStr = sdf.format(file3.lastModified());
		System.out.println("마지막 변경 날짜:"+dateStr);
		System.out.println("file3.length()=>"+file3.length());

		file3.setReadOnly();
	}

}

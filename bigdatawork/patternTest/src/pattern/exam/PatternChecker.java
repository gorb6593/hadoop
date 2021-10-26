package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 문자열의 형식을 검사하는 기능을 갖는 클래스 */
public class PatternChecker {
     
    /** 숫자 모양에 대한 형식 검사 */
    public static boolean isNum(String str) {
		return Pattern.matches("[0-9]+", str);    	
    }
     
   /** 영문으로만 구성되었는지에 대한 형식 검사 */
    public static boolean isEng(String str) {
    	return Pattern.matches("[A-z ]+", str); 
    }
    
   /** 한글로만 구성되었는지에 대한 형식 검사 */
    public static boolean isKor(String str) {
    	return Pattern.matches("[ㄱ-ㅎ가-힣 ]+", str);
    }
     
    /** 영문과 숫자로만 구성되었는지에 대한 형식 검사 */
    public static boolean isEngNum(String str) {
    	return Pattern.matches("[A-z0-9 ]+", str);
    	
    }
     
    /** 한글과 숫자로만 구성되었는지에 대한 형식 검사 */
    public static boolean isKorNum(String str) {
    	return Pattern.matches("[ㄱ-ㅎ가-힣0-9 ]+", str);
    }
     
   /** 이메일 형식인지에 대한 검사 --> "아이디@도메인"의 형식을 충족해야 한다. */
    public static boolean isEmail(String str) {
    	return Pattern.matches("[A-z0-9]+@[0-9A-z\\.]+", str);
    }
    public static void main(String[] args){
    	System.out.println("***********isNum테스트*************");
    	System.out.println(isNum("2345"));//true
    	System.out.println(isNum("2345한글"));//false
    	System.out.println(isNum("2345****"));//false
    	System.out.println("***********isEng테스트*************");
    	System.out.println(isEng("aasdfgasdf"));//true
    	System.out.println(isEng("aasdfgas df"));//true
    	System.out.println(isEng("aasdfgas**df"));//false
    	System.out.println(isEng("aasdfgas1221df"));//false
    	System.out.println(isEng("aasdfgas한글df"));//false
    	System.out.println("***********isKor테스트*************");
    	System.out.println(isKor("aasdfgas한글df"));//false
    	System.out.println(isKor(" 한글"));//true
    	System.out.println(isKor("한글 한긆 ㄴㅇㄻㅁㄹ"));//true
    	System.out.println(isKor("123한글"));//false
    	
    	System.out.println("***********isEngNum테스트*************");
    	System.out.println(isEngNum("aasdfgas한글df"));//false
    	System.out.println(isEngNum(" 한글"));//false
    	System.out.println(isEngNum("   111   asdfa"));//true
    	System.out.println(isEngNum("dfgad998"));//true
    	  	System.out.println("***********isKorNum테스트*************");
    	System.out.println(isKorNum("aasdfgas한글df"));//false
    	System.out.println(isKorNum(" 한글"));//false
    	System.out.println(isKorNum("   111   asdfa"));//true
    	System.out.println(isKorNum("한글998"));//true
    	
    	System.out.println("***********isKorNum테스트*************");
    	System.out.println(isEmail("heaves@hanmail.net"));//true
    	System.out.println(isEmail("heaveshanmail.net"));//false

//    	
//    	System.out.println("***********iptest테스트*************");
//    	System.out.println(isIP("127.0.0.1"));//true
//    	System.out.println(isIP("196.168.59.101"));//true
//    	System.out.println(isIP("250.0.8.9"));//true
//    	System.out.println(isIP("196.168.59"));//false
//    	System.out.println(isIP("300.168.59.101"));//false
//    	System.out.println(isIP("300.168.592332.101"));//false
//    	System.out.println(isIP("300.168.592.문자열"));//false   	
    }   
}

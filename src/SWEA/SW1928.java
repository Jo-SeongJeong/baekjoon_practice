package SWEA;

import java.util.Base64;
import java.util.Scanner;

public class SW1928 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            String str = scan.next();
            String result = new String(Base64.getDecoder().decode(str));

            System.out.println("#" + t + " " + result);
        }
    }
}
/*
java.util.Base64
Base64란, 64문자의 영숫자를 이용하여 멀티 바이트 문자열이나 이진 데이터를 다루기 위한 인코딩 방식.

Base64.getDecoder().decode(str)
입력받은 str을 디코딩한 문자열을 가져와서 result에 저장한다는 뜻이다.
*/
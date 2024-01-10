package SWEA;

import java.util.Scanner;

public class SW1989 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for(int i = 1; i <= t; i++) {
            String str = scan.next();
            char[] array = str.toCharArray();
            int result = 1;

            if(array.length % 2 == 0) {
                int start = 0;
                int end = array.length-1;
                while (start < end) {
                    if(array[start] != array[end]) {
                        result = 0;
                        break;
                    }
                    start++;
                    end--;
                }
            }

            else {
                int start = 0;
                int end = array.length-1;
                while (start != end) {
                    if(array[start] != array[end]) {
                        result = 0;
                        break;
                    }
                    start++;
                    end--;
                }
            }
            System.out.println("#" + i + " " + result);
        }
    }
}

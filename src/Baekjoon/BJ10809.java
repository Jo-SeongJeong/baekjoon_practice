package Baekjoon;

import java.util.Scanner;

public class BJ10809 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        int[] array = new int[26];

        for(int i=0; i<26; i++) {
            array[i] = -1;
        }

        for(int i=0; i<S.length(); i++) {
            char ch = S.charAt(i);

            if(array[ch - 'a'] == -1)
                array[ch - 'a'] = i;
        }

        for(int i=0; i<26; i++)
            System.out.print(array[i]+" ");
    }
}

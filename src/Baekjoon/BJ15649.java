package Baekjoon;

import java.util.Scanner;

public class BJ15649 {
    static int[] array;
    static int[] pick;
    static boolean[] isChecked;

    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        array = new int[n+1];

        pick = new int[m];
        isChecked = new boolean[n+1];

        for(int i = 0; i < n+1; i++) {
            array[i] = i;
        }

        recursion(0);

    }

    public static void recursion(int index) {
        if(index == m) {
            for(int i = 0; i < m; i++) {
                System.out.print(pick[i]+ " ");
            }
            System.out.println();
            return ;
        }

        for(int i = 1; i <= n; i++) {
            if(isChecked[i] == true)
                continue;

            pick[index] = array[i];
            isChecked[i] = true;
            recursion(index+1);
            isChecked[i] = false;
        }
    }

}

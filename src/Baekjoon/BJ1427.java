package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1427 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] array = new Integer[Integer.toString(n).length()];

        int i = 0;
        while(i < array.length) {
            array[i] = n%10;
            n /= 10;
            i++;
        }

        Arrays.sort(array, (a, b) -> b-a);

        String answer = "";
        for(int j = 0; j < array.length; j++) {
            answer += Integer.toString(array[j]);
        }

        System.out.println(Integer.parseInt(answer));
    }
}

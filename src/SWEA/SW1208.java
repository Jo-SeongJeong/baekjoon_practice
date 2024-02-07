package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class SW1208 {
    static int[] land;
    static int count;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int t = 1; t <= 10; t++) {
            land = new int[100];
            count = sc.nextInt();

            for(int i = 0; i < 100; i++) {
                land[i] = sc.nextInt();
            }
            dump(count);

            System.out.println("#" +t+" " +result);
        }
    }

    public static void dump(int count) {
        Arrays.sort(land);
        if(count == 0) {
            result = land[99]-land[0];
            return;
        }
        land[0] += 1;
        land[99] -= 1;

        dump(count-1);
    }
}

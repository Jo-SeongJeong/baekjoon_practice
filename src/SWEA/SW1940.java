package SWEA;

import java.util.Scanner;

public class SW1940 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        int testCase = scan.nextInt();

        for (int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int[][] array = new int[n][2];

            for (int i = 0; i < n; i++) {
                array[i][0] = scan.nextInt();
                if (array[i][0] > 0) {
                    array[i][1] = scan.nextInt();
                }
            }

            int distance = 0;
            int speed = 0;
            for (int i = 0; i < n; i++) {
                switch (array[i][0]) {
                    case 0:
                        distance += speed;
                        break;
                    case 1:
                        speed += array[i][1];
                        distance += speed;
                        break;
                    case 2:
                        speed -= array[i][1];
                        if(speed < 0) {
                            speed = 0;
                        }
                        distance += speed;
                        break;
                }
            }
            System.out.println("#" + t + " " + distance);
        }
    }
}
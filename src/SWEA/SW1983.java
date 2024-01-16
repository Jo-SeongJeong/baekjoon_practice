package SWEA;

import java.util.Scanner;

public class SW1983 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int testCase = scan.nextInt();

        for(int t = 1; t <= testCase; t++) {
            int n = scan.nextInt();
            int k = scan.nextInt();

            double[] array = new double[n];

            for(int i = 0; i < n; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                int c = scan.nextInt();

                array[i] = (a * 0.35) + (b * 0.45) + (c * 0.2);
            }

            double student = array[k-1];

            for(int i = 0; i < n-1; i++) {
                double temp;
                for(int j = i+1; j < n; j++) {
                    if(array[i] <= array[j]) {
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }

            int rank = 0;
            for(int i = 0; i < n; i++) {
                if(student == array[i]) {
                    rank = i;
                    break;
                }
            }

            rank = rank / (n / 10);

            switch (rank) {
                case 0:
                    System.out.println("#" + t + " A+");
                    break;
                case 1:
                    System.out.println("#" + t + " A0");
                    break;
                case 2:
                    System.out.println("#" + t + " A-");
                    break;
                case 3:
                    System.out.println("#" + t + " B+");
                    break;
                case 4:
                    System.out.println("#" + t + " B0");
                    break;
                case 5:
                    System.out.println("#" + t + " B-");
                    break;
                case 6:
                    System.out.println("#" + t + " C+");
                    break;
                case 7:
                    System.out.println("#" + t + " C0");
                    break;
                case 8:
                    System.out.println("#" + t + " C-");
                    break;
                case 9:
                    System.out.println("#" + t + " D0");
                    break;
            }
        }
    }
}
package SWEA;

import java.util.Scanner;

public class SW2056 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        String[] array = new String[8];

        for (int i = 1; i <= t; i++) {
            String n = scan.next();
            array = n.split("");

            String year = "";
            String month = "";
            String day = "";
            for (int k = 0; k < 4; k++) {
                year = year + array[k];
            }
            for (int k = 4; k < 6; k++) {
                month = month + array[k];
            }
            for (int k = 6; k < 8; k++) {
                day = day + array[k];
            }

            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            int d = Integer.parseInt(day);

            if(y<1 || (m<1 || m>13)) {
                System.out.println("#" + i + " -1");
            }
            else {
                switch(m) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (d < 1 || d > 31) {
                            System.out.println("#" + i + " -1");
                        } else {
                            System.out.println("#" + i + " " + year + "/" + month + "/" + day);
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (d < 1 || d > 30) {
                            System.out.println("#" + i + " -1");
                        } else {
                            System.out.println("#" + i + " " + year + "/" + month + "/" + day);
                        }
                        break;
                    case 2:
                        if (d < 1 || d > 28) {
                            System.out.println("#" + i + " -1");
                        } else {
                            System.out.println("#" + i + " " + year + "/" + month + "/" + day);
                        }
                        break;
                }
            }
        }
    }
}

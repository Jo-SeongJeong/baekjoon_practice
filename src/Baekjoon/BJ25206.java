package Baekjoon;

import java.util.Scanner;

public class BJ25206 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        double total = 0.0;
        double tc = 0.0;
        for(int i=0; i<20; i++) {
            String subject = scan.next();
            double credit = scan.nextDouble();
            String score = scan.next();
            double trans = 0.0;

            if(!score.equals("P")) {
                switch (score) {
                    case "A+" :
                        trans = 4.5;
                        break;
                    case "A0" :
                        trans = 4.0;
                        break;
                    case "B+" :
                        trans = 3.5;
                        break;
                    case "B0" :
                        trans = 3.0;
                        break;
                    case "C+" :
                        trans = 2.5;
                        break;
                    case "C0" :
                        trans = 2.0;
                        break;
                    case "D+" :
                        trans = 1.5;
                        break;
                    case "D0" :
                        trans = 1.0;
                        break;
                    case "F" :
                        trans = 0.0;
                        break;
                }
                total += (credit * trans);
                tc += credit;
            }
        }
        System.out.println(total/tc);
    }
}

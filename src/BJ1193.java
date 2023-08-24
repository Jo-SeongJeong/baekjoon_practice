import java.util.Scanner;

public class BJ1193 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        long x = scan.nextInt();
        long prev = 0; // 이전 대각선
        long a = 1; // 분자
        long b = 1; // 분모
        long last = 0; // 이전 대각선에서, 마지막 번째 분수

        for(long i=1; i<x; i++) {
            if(i*(i+1)/2 < x) {
                prev++;
                last = i*(i+1)/2;
            }
        }

        if(prev == 0) {
            System.out.println(a+"/"+b);
        }

        else if(prev%2 == 0){
            a = prev+2;
            b = 0;
            while(last<x) {
                a--;
                b++;
                last++;
            }
            System.out.println(a+"/"+b);
        }
        else {
            a = 0;
            b = prev+2;
            while (last<x){
                a++;
                b--;
                last++;
            }
            System.out.println(a+"/"+b);
        }

    }
}
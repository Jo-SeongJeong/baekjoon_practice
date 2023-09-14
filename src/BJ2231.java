import java.util.Scanner;

public class BJ2231 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = n;
        int sum = 0;
        while(m>10) {
            sum += m % 10;
            m = m/10;
            if(m < 10) {
                sum += m;
                break;
            }
        }
        System.out.println(n-sum);
    }
}

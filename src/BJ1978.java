import java.util.Scanner;

public class BJ1978 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int prime = 0;

        for(int i=0; i<n; i++) {
            int count = 0;
            int num = scan.nextInt();
            if(num == 1) {
               continue;
            }
            for(int j=2; j<num; j++) {
                if(num % j == 0) {
                    count++;
                }
            }
            if(count == 0) {
                prime++;
            }
        }
        System.out.println(prime);
    }
}

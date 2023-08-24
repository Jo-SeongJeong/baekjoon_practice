import java.util.Scanner;

public class BJ2292 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int count = 1;
        int i = 1;
        if(n == 1)
            System.out.println(count);

        else {
            for(; i<n; count++) {
                i = i + (6 * count);
            }
            System.out.println(count);
        }


    }
}

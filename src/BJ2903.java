import java.util.Scanner;

public class BJ2903 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double dot = 0;
        dot = Math.pow(Math.pow(2,n)+1,2);
        int end = (int) dot;
        System.out.println(end);

    }
}

import java.util.Locale;
import java.util.Scanner;

public class BJ11005 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int b = scan.nextInt();

        String change = Integer.toString(n, b);
        change = change.toUpperCase();

        System.out.println(change);
    }
}

import java.util.Scanner;

public class BJ10988 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String n = scan.next();
        int p = 1;

        for (int i=0; i < n.length()/2; i++) {
            if (n.charAt(i) != n.charAt(n.length() - 1 - i))
                p = 0;
        }
        System.out.println(p);
    }
}

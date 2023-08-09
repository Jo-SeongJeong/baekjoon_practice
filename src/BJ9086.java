import java.util.Scanner;

public class BJ9086 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for(int i =0; i<T; i++) {
            String S = scan.next();
            System.out.print(S.charAt(0));
            System.out.println(S.charAt(S.length()-1));
        }
    }
}

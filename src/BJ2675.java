import java.util.Scanner;

public class BJ2675 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for(int i=0; i<T; i++) {
            int R = scan.nextInt();
            String S = scan.next();

            for(int j=0; j<S.length(); j++) {
                for(int k=0; k<R; k++) {
                    char ch = S.charAt(j);
                    System.out.print(ch);
                }
            }
            System.out.println();
        }
    }
}

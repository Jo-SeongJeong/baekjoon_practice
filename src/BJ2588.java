import java.util.Scanner;

public class BJ2588 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = A*(B%10);
        int D = A*((B/10)%10);
        int E = A*(B/100);
        System.out.println(C);
        System.out.println(D);
        System.out.println(E);
        System.out.println(A*B);
    }
}

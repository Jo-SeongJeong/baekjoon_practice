import java.util.Scanner;

public class BJ2480 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A = scan.nextInt();
        int B = scan.nextInt();
        int C = scan.nextInt();

        if(A==B && A==C)
            System.out.println(10000+A*1000);
        else if((A==B && A!=C) || (A==C && A!=B))
            System.out.println(1000+A*100);
        else if(B==C && B!=A)
            System.out.println(1000+B*100);
        else {
            if((A>=B && B>=C) || (A>=B && A>=C))
                System.out.println(A*100);
            else if ((B>=A && A>=C) || (B>=A && B>=C))
                System.out.println(B*100);
            else
                System.out.println(C*100);
        }
    }
}

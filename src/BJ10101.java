import java.util.Scanner;

public class BJ10101 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        if(a==60 && b==60 && c==60)
            System.out.println("Equilateral");
        else if(a+b+c==180 && (a==b || a==c || b==c))
            System.out.println("Isosceles");
        else if(a+b+c==180 && !(a==b || a==c || b==c))
            System.out.println("Scalene");
        else if(a+b+c!=180)
            System.out.println("Error");
    }
}

import java.util.Scanner;

public class BJ5073 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        while(true) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();

            if(a==0 && b==0 && c==0) {
                break;
            }
            int max = Math.max(Math.max(a, b),c);

            if(max==a) {
                if(max>=b+c) {
                    System.out.println("Invalid");
                    continue;
                }
            }
            else if(max==b) {
                if(max>=a+c) {
                    System.out.println("Invalid");
                    continue;
                }
            }
            else {
                if(max>=a+b) {
                    System.out.println("Invalid");
                    continue;
                }
            }

            if(a==b && b==c)
                System.out.println("Equilateral");
            else if(a==b || a==c || b==c)
                System.out.println("Isosceles");
            else {
                System.out.println("Scalene");
            }
        }
    }
}

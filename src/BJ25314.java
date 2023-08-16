import java.util.Scanner;

public class BJ25314 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        for(int i=1; i<=N; i++) {
            if(i%4 == 0)
                System.out.print("long ");
        }
        System.out.println("int");
    }
}

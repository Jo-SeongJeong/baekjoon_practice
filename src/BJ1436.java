import java.util.Scanner;

public class BJ1436 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int i = 1;
        int num = 666;
        while(n != i) {
            num++;

            if(String.valueOf(num).contains("666")) {
                i++;
            }
        }
        System.out.println(num);
    }
}

import java.util.Scanner;

public class BJ9063 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x_min = 10000;
        int y_min = 10000;
        int x_max = -10000;
        int y_max = -10000;
        int width;

        for(int i=0; i<n; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();

            x_min = Math.min(x,x_min);
            y_min = Math.min(y,y_min);
            x_max = Math.max(x,x_max);
            y_max = Math.max(y,y_max);
        }
        width = (x_max - x_min) * (y_max - y_min);
        System.out.println(width);
    }
}

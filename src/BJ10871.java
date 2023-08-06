import java.util.Scanner;

public class BJ10871 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int []array = new int[N];
        int X = scan.nextInt();

        for(int i=0; i<N; i++)
            array[i] = scan.nextInt();

        for(int i=0; i<N; i++) {
            if(array[i] < X)
                System.out.print(array[i]+" ");
        }
    }
}

import java.util.Scanner;

public class BJ10810 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int []array = new int[N];
        int M = scan.nextInt();

        for(int l=0; l<M; l++) {
            int i = scan.nextInt();
            int j = scan.nextInt();
            int k = scan.nextInt();

            while(i<=j) {
                array[i-1] = k;
                i++;
            }
        }
        for(int r=0; r<N; r++)
            System.out.print(array[r]+" ");
    }
}

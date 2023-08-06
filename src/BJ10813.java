import java.util.Scanner;

public class BJ10813 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        int []array = new int[N];
        int temp;

        for(int k=0; k<N; k++)
            array[k] =k+1;

        for(int k=0; k<M; k++) {
            int i = scan.nextInt();
            int j = scan.nextInt();
            temp = array[i-1];
            array[i-1] = array[j-1];
            array[j-1] = temp;
        }

        for(int k=0; k<N; k++)
            System.out.print(array[k] + " ");
    }
}

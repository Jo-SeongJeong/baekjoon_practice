import java.util.Scanner;

public class BJ10811 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int []array = new int[N];
        int M = scan.nextInt();

        for(int i=0; i<N; i++)
            array[i] = i+1;

        for(int k=0; k<M; k++) {
            int i = scan.nextInt();
            int j = scan.nextInt();
            while(i<=j) {
                int temp;
                temp = array[i-1];
                array[i-1] = array[j-1];
                array[j-1] = temp;
                i++;
                j--;
            }
        }

        for(int i=0; i<N; i++)
            System.out.print(array[i]+" ");
    }
}

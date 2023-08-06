import java.util.Scanner;

public class BJ10818 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int []array = new int[N];

        for(int i=0; i<N; i++)
            array[i] = scan.nextInt();
        int min = array[0];
        int max = array[0];

        for(int i=0; i<N; i++) {
            if(min > array[i])
                min = array[i];
            if(max < array[i])
                max = array[i];
        }

        System.out.println(min+" "+max);
    }
}

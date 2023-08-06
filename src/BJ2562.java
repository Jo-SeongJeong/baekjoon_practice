import java.util.Scanner;

public class BJ2562 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int []array = new int[9];
        int max = array[0];
        int j=1;

        for(int i=0; i<9; i++) {
            array[i] = scan.nextInt();
            if(max < array[i]) {
                max = array[i];
                j = i+1;
            }
        }
        System.out.println(max);
        System.out.println(j);
    }
}

import java.util.Scanner;

public class BJ2566 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int [][]array = new int[9][9];
        int max = 0;
        int row = 0;
        int column = 0;

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                array[i][j] = scan.nextInt();
                if(max < array[i][j]) {
                    max = array[i][j];
                    row = i;
                    column = j;
                }
            }
        }

        System.out.println(max);
        System.out.print((row+1) + " " + (column+1));
    }
}

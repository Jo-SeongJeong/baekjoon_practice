import java.util.Scanner;

public class BJ10798 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        char [][]array = new char[5][15];
        int max = 0;

        for(int i=0; i<5; i++) {
            String str = scan.next();
            if(max < str.length())
                max = str.length();

            for(int j=0; j<str.length(); j++) {
                array[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<max; i++) {
            for(int j=0; j<5; j++) {
                if(array[j][i] != '\0') {
                    System.out.print(array[j][i]);
                }
            }
        }
    }
}

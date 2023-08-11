import java.util.Scanner;

public class BJ2908 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String A = scan.next();
        String B = scan.next();

        String reverseA = "";
        String reverseB = "";

        for(int i = A.length()-1; i>=0; i--)
            reverseA = reverseA + A.charAt(i);

        for(int i = B.length()-1; i>=0; i--)
            reverseB = reverseB + B.charAt(i);

        int n = Integer.parseInt(reverseA);
        int m = Integer.parseInt(reverseB);

        if(n>m)
            System.out.println(n);
        else if(n<m)
            System.out.println(m);
    }
}

import java.util.Scanner;

public class BJ3003 {
    public static void main(String []args) {
        Scanner scan = new Scanner(System.in);
        int king = scan.nextInt();
        int queen = scan.nextInt();
        int rook = scan.nextInt();
        int bishop = scan.nextInt();
        int knight = scan.nextInt();
        int pawn = scan.nextInt();

        int k_count = 0;
        int q_count = 0;
        int r_count = 0;
        int b_count = 0;
        int kn_count = 0;
        int p_count = 0;

        while(king != 1) {
            if(king > 1) {
                k_count--;
                king--;
            }
            else if(king < 1) {
                k_count++;
                king++;
            }
        }
        while(queen != 1) {
            if(queen > 1) {
                q_count--;
                queen--;
            }
            else if(queen < 1) {
                q_count++;
                queen++;
            }
        }
        while(rook != 2) {
            if(rook > 2) {
                r_count--;
                rook--;
            }
            else if(rook < 2) {
                r_count++;
                rook++;
            }
        }
        while(bishop != 2) {
            if(bishop > 2) {
                b_count--;
                bishop--;
            }
            else if(bishop < 2) {
                b_count++;
                bishop++;
            }
        }
        while(knight != 2) {
            if(knight > 2) {
                kn_count--;
                knight--;
            }
            else if(knight < 2) {
                kn_count++;
                knight++;
            }
        }
        while(pawn != 8) {
            if(pawn > 8) {
                p_count--;
                pawn--;
            }
            else if(pawn < 8) {
                p_count++;
                pawn++;
            }
        }
        System.out.println(k_count + " " + q_count + " " + r_count + " " + b_count + " " + kn_count + " " + p_count);
    }
}

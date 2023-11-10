package SWEA;

public class SW2027 {
    public static void main(String[] args) {
        String []array = new String[5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(i==j) {
                    System.out.print("#");
                }
                else {
                    System.out.print("+");
                }
            }
            System.out.println();
        }
    }
}

package test;

import java.util.Scanner;

public class BJ2309 {
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int[] array = new int[9];
		int sum = 0;
		
		for (int i = 0; i < array.length; i++) {
			array[i] = scan.nextInt();
			sum += array[i];
		}
		
		

		A: for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				int a = array[i];
				int b = array[j];
				
				if(sum - a - b == 100) {
					array[i] = 0;
					array[j] = 0;
					break A;
				}
			}
		}
		
		for (int i = 0; i < array.length - 1; i++) {
			int temp = 0;
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] >= array[j]) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		
		for(int i = 2; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}

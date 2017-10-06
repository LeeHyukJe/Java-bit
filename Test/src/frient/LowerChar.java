package frient;

import java.util.Scanner;

public class LowerChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String lower="";
		while(sc.hasNextLine()) {
			lower=sc.nextLine();
			System.out.println(lower.toLowerCase());
		}
	}

}

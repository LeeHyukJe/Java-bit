package ch13;

import javax.swing.JOptionPane;

public class ThreadEx6{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String input=JOptionPane.showInputDialog("아무 입력값");
		System.out.println("입력하신 값은 "+input);
		for(int i=10;i>=0;i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(Exception e) {}
		}
	}

}

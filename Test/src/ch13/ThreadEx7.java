package ch13;

import javax.swing.JOptionPane;

class ThreadEx7_1 extends Thread{
	public void run() {
		for(int i=10;i>=0;i--) {
			System.out.println(i);
			try {
				sleep(1000);
			}catch(InterruptedException e) {
				interrupt();
			}
		}
	}
}
public class ThreadEx7 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ThreadEx7_1 th1=new ThreadEx7_1();
		th1.start();
		
		String input=JOptionPane.showInputDialog("아무 입력값");
		System.out.println("입력한 값"+input);
	}

}

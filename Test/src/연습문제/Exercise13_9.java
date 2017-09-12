package 연습문제;

import javax.swing.JOptionPane;

class Exercise13_9 {
	public static void main(String[] args) throws Exception {
		Exercise13_9_1 th1 = new Exercise13_9_1();
		th1.start();
		String input = JOptionPane.showInputDialog(" .");
		System.out.println(" " + input + " .");
		th1.interrupt(); // 멈추지 않을때 인터럽트는 true 멈추었을 때 인터럽트는 false
	}
}

class Exercise13_9_1 extends Thread {
	public void run() {
		int i = 10;
		while (i != 0 && !isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000); // 1초 지연
			} catch (InterruptedException e) {
				interrupt();
			}
			
		}
		System.out.println(" .");
	} // main
}

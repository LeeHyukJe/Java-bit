package ��������;

import javax.swing.JOptionPane;

class Exercise13_9 {
	public static void main(String[] args) throws Exception {
		Exercise13_9_1 th1 = new Exercise13_9_1();
		th1.start();
		String input = JOptionPane.showInputDialog(" .");
		System.out.println(" " + input + " .");
		th1.interrupt(); // ������ ������ ���ͷ�Ʈ�� true ���߾��� �� ���ͷ�Ʈ�� false
	}
}

class Exercise13_9_1 extends Thread {
	public void run() {
		int i = 10;
		while (i != 0 && !isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000); // 1�� ����
			} catch (InterruptedException e) {
				interrupt();
			}
			
		}
		System.out.println(" .");
	} // main
}

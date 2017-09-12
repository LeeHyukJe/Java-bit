package ch13;

class Account {
	private int balance = 1000;

	public int getBalance() {
		return balance;
	}

	public void withdraw(int money) {
		synchronized (this) {
			if (balance >= money) { // �Ӱ迵������ �ؾ� �� �κ�
				try {
					Thread.sleep(1000);
					balance -= money;
				} catch (Exception e) {}
			}
		} // withdraw
	}
}

class RunnableEX21 implements Runnable {
	Account acc = new Account();

	public void run() {
		while (acc.getBalance() > 0) {
			int money = (int) (Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println("����: " + acc.getBalance());
		}
	}
}

public class ThreadEx21 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new RunnableEX21()).start();
		new Thread(new RunnableEX21()).start();
	}

}


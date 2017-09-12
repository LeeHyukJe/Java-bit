package ch13;

class ThreadEx17_1 implements Runnable {
	volatile boolean suspended = false;
	volatile boolean stopped = false;

	Thread th;

	ThreadEx17_1(String name) {
		th = new Thread(this, name);
	}

	public void run() {
		while (!stopped) {
			if (!suspended) {
				System.out.println(Thread.currentThread().getName());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
			else 
				Thread.yield();
		}
		System.out.println(Thread.currentThread().getName() + "-stopped");
	}

	public void suspend() {
		System.out.println("suspend!");
		suspended = true;
	}

	public void resume() {
		System.out.println("resume!");
		suspended = false;
	}

	public void stop() {
		stopped = true;
		Thread.interrupted();
	}

	public void start() {
		th.start();
	}

}

public class ThreadEx17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx17_1 th1 = new ThreadEx17_1("x");
		ThreadEx17_1 th2 = new ThreadEx17_1("xx");
		ThreadEx17_1 th3 = new ThreadEx17_1("xxx");

		th1.start();
		th2.start();
		th3.start();

		try {
			Thread.sleep(3000); //메인 스레드가 잠듦
			th1.suspend();
			Thread.sleep(3000);
			th2.suspend();
			Thread.sleep(3000);
			th1.resume();
			Thread.sleep(3000);
			th1.stop();
			th2.stop();
			Thread.sleep(2000);
			th3.stop();
		} catch (InterruptedException e) {
		}
	}

}

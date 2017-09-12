package ch13;

class ThreadEx2_1 extends Thread{
	public void run() {
		throwException();
	}
	
	public void throwException() {
		try {
			throw new Exception();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


public class ThreadEx2 implements Runnable {
	public void run() {
		try {
			throw new Exception();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx2_1 t1=new ThreadEx2_1();
		Thread t2=new Thread(new ThreadEx2());
		t1.start();
		t2.start();
	}

}

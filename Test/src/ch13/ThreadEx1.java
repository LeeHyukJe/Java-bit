package ch13;

class ThreadEx1_1 extends Thread{
	public void run() {
		for(int i=0;i<5;i++) {
			try {
			Thread.sleep(100);
			System.out.print(getName());
			}catch(Exception e) {}
		}
	}
}

class ThreadEx1_2 implements Runnable{
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			System.out.print(Thread.currentThread().getName());
			}catch(Exception e) {}
		}
	}
}

public class ThreadEx1 implements Runnable {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Runnable run=new ThreadEx1_2(); //Runnable을 구현한 클래스를 인스턴스해서 작성
//		Thread t2=new Thread(run);
		Thread tx=new Thread(new ThreadEx1());
		Thread t1=new Thread(new ThreadEx1_1());
		Thread t3=new Thread(new ThreadEx1_2());
		
		tx.start();
		t1.start();
		//t2.start();
		t3.start();
		
	}
	
	public void run() {
		for(int i=1;i<10;i++) {
			try {
				Thread.sleep(100);
			System.out.print(i);
			}catch(Exception e) {}
		}
	}

}

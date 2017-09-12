package ch13;

class ThreadEx19_1 extends Thread{
	public void run() {
		for(int i=0;i<500;i++) {
			try {
				Thread.sleep(10);
				System.out.print("-");
			}catch(Exception e) {}
		}
	}
}

class ThreadEx19_2 extends Thread{
	public void run() {
		for(int i=0;i<500;i++) {
			try {
				Thread.sleep(10);
				System.out.print("|");
			}catch(Exception e) {}
		}
	}
}

public class ThreadEx19 {
	static long startTime=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx19_1 th1=new ThreadEx19_1();
		ThreadEx19_2 th2=new ThreadEx19_2();
		
		th1.start();
		th2.start();
		startTime=System.currentTimeMillis();
		
		try {
			th1.join();
			th2.join();
		}catch(InterruptedException e) {}
		
		System.out.print("소요시간:"+(System.currentTimeMillis()-ThreadEx19.startTime));
	}

}

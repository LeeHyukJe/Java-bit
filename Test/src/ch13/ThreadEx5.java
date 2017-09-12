package ch13;

class ThreadEx5_1 extends Thread{
	public void run() {
		for(int i=0;i<300;i++) {
			System.out.printf("%s",new String("|"));
		}
		System.out.print("소요시간2:"+(System.currentTimeMillis()-ThreadEx5.startTime));
	}
}

public class ThreadEx5 {
	static long startTime=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadEx5_1 th1=new ThreadEx5_1();
		th1.start(); //실행 준비 상태
		startTime=System.currentTimeMillis();
		for(int i=0;i<300;i++) {
			System.out.printf("%s",new String("-"));
		}
		System.out.print("소요시간:"+(System.currentTimeMillis()-ThreadEx5.startTime));
	}	

}

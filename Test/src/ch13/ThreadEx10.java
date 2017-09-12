package ch13;


public class ThreadEx10 implements Runnable {
	static boolean autosave=false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Thread의 생성자는 매개변수 타입이 Runnable이다.
		Thread t=new Thread(new ThreadEx10());
		
		t.setDaemon(true);
		t.start();
		
		for(int i=1;i<=10;i++) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
				System.out.println(i);
				
				if(i==5)
					autosave=true;
			}
		System.out.println("프로그램을 종료합니다.");
		}
		
		public void run() { //daemon 스레드 실행
			while(true) {
			try {
				Thread.sleep(3*1000);
			}catch(InterruptedException e) {}
			if(autosave)
				autosave();
		}
	}
		
		public void autosave() {
			System.out.println("파일 자동저장 작동!");
		}
}

package ch13;


public class ThreadEx10 implements Runnable {
	static boolean autosave=false;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Thread�� �����ڴ� �Ű����� Ÿ���� Runnable�̴�.
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
		System.out.println("���α׷��� �����մϴ�.");
		}
		
		public void run() { //daemon ������ ����
			while(true) {
			try {
				Thread.sleep(3*1000);
			}catch(InterruptedException e) {}
			if(autosave)
				autosave();
		}
	}
		
		public void autosave() {
			System.out.println("���� �ڵ����� �۵�!");
		}
}

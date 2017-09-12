package ch13;

public class ThreadEx9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadGroup main=Thread.currentThread().getThreadGroup();
		ThreadGroup grp1=new ThreadGroup("Group1");
		ThreadGroup grp2= new ThreadGroup("Group2");
		
		ThreadGroup subGrp1=new ThreadGroup(grp1,"subgroup1");
		ThreadGroup subGrp2=new ThreadGroup(grp1,"subgroup2");
		grp1.setMaxPriority(3);
		
		Runnable r=new Runnable() { //익명 클래스로 Runnable 구현
			public void run() {
				try {
					Thread.sleep(1000);
				}catch(Exception e) {}
			}
		};
		
		new Thread(grp1, r, "th1").start();
		new Thread(subGrp1, r, "th2").start();
		new Thread(subGrp2,r,"th3").start();
		new Thread(grp2,r,"th3").start();
		
		System.out.println(main.getName()+" "+main.activeGroupCount()+" "+main.activeCount()+" "+main.activeCount());
		main.list();
	}

}

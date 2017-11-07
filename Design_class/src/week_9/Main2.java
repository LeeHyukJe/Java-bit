package week_9;

class TicketManager{
	private static TicketManager mgr;
	private int limits;
	private int count;
	
	private TicketManager() {
		count=0;
	}
	
	//오직 하나의 티켓 발행기를 생성함
	public synchronized static TicketManager getTicketManager() {
		if(mgr==null) {
			mgr=new TicketManager();
		}
		return mgr;
	}
	
	public synchronized void setTicketLimits(int limits) {
		this.limits=limits;
	}
	
	public synchronized Ticket getTicket() {
		if(this.count<this.limits) {
			return new NormalTicket(++this.count);
		}
		
		return new NullTicket();
	}
}

interface Ticket{
	public int getTicketNum();
}

class NormalTicket implements Ticket{

	private int serial_num;
	
	public NormalTicket(int num) {
		this.serial_num=num;
	}
	
	
	
	@Override
	public int getTicketNum() {
		// TODO Auto-generated method stub
		return this.serial_num;
	}
	
}

class NullTicket implements Ticket{

	@Override
	public int getTicketNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class UserThread extends Thread{
	private Ticket mTicket;
	
	public UserThread(String name) {
		super(name);
	}
	
	public void run() {
		TicketManager mgr=TicketManager.getTicketManager();
		mTicket=mgr.getTicket();
	}
	
	public Ticket getMyTicket() {
		return mTicket;
	}
}

public class Main2 {
	
	private static final int THREAD_NUM=15;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketManager mgr=TicketManager.getTicketManager();
		mgr.setTicketLimits(5);
		UserThread[] user=new UserThread[THREAD_NUM];
		
		for(int i=0;i<THREAD_NUM;i++) {
			user[i]=new UserThread(i+1+"-thread");
			user[i].start();
		}
		
		for(int i=0;i<THREAD_NUM;i++) {
			try {
				user[i].join();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<THREAD_NUM;i++) {
			if(user[i].getMyTicket().getTicketNum()!=0) {
				System.out.println("User"+i+"-th Thread get ticket"+user[i].getMyTicket().getTicketNum());
			}
		}
	}

}

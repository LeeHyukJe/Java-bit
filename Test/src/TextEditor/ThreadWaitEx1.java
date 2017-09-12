package TextEditor;

import java.util.ArrayList;

class Customer implements Runnable {
	private Table table;
	private String food;

	Customer(Table table, String food) {
		this.table = table;
		this.food = food;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			String name = Thread.currentThread().getName();

			if (eatFood()) {
				System.out.println(name + " ate a" + food);
			} else {
				System.out.println(name + "failed to eat ㅅㅂ");
			}
		}
	}
	boolean eatFood() {return table.remove(food);} 
}

class Cook implements Runnable{
	private Table table;
	Cook(Table table){
		this.table=table;
	}
	public void run() {
		while(true) {
			int inx=(int)(Math.random()*table.dishName());
			table.add(table.dishName[inx]);
			try {
				Thread.sleep(1);
			}catch(InterruptedException e) {}
		}
	}
}

class Table{
	String[] dishName= {"donut","donut","burger"};
	final int MAX_FOOD=6;
	private ArrayList<String>dishes=new ArrayList<>();
	
	public synchronized void add(String dish) { //공유자원
		if(dishes.size()>=MAX_FOOD) {
			return;
		}
		dishes.add(dish);
		System.out.println("Dishes"+dishes.toString());
	}
	
	public synchronized boolean remove(String dishName) { //공유자원
		while(dishes.size()==0) { //lock발생 가능성
			String name=Thread.currentThread().getName();
			System.out.println(name+" is wating");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {}
		}
		
		for(int i=0;i<dishes.size();i++) {
			if(dishName.equals(dishes.get(i))) {
				dishes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public int dishName() {
		return dishName.length;
	}
}

public class ThreadWaitEx1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Table table =new Table();
		new Thread(new Cook(table),"cook1").start();
		new Thread(new Customer(table,"donut")).start();
		new Thread(new Customer(table,"burger")).start();
		Thread.sleep(100);
		System.exit(10);
		
		}

}

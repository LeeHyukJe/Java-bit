package ch13;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;




class Table {
	String[] dishName = { "donut", "donut", "burger" };
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();
	private ReentrantLock lock=new ReentrantLock();
	private Condition forCook = lock.newCondition();// for cooker
	private Condition forCust = lock.newCondition();// for customer

	public void add(String dish) {
		lock.lock();

		try {
			while (dishes.size() >= MAX_FOOD) { //음식량이 초과되면 기다림
				String name = Thread.currentThread().getName();
				System.out.println(name + " is wating");
				try {
					forCook.await(); // cooker thread can wait;
					Thread.sleep(500);
				} catch (InterruptedException e) {}
			}
			dishes.add(dish);
			forCust.signal(); // customet is awake;
			System.out.println("Dishes" + dishes.toString());
		} finally {
			lock.unlock(); // lock has unlocked
		}
	}

	public void remove(String dishName) {
		lock.lock();
		String name = Thread.currentThread().getName();
		try {
			while (dishes.size() == 0) {
				System.out.println(name + " is wating");
				try {
					forCust.await(); // customer thread can wait
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
			while (true) {
				for (int i = 0; i < dishes.size(); i++) {
					if (dishName.equals(dishes.get(i))) {
						dishes.remove(i);
						forCook.signal();
						return;
					}
				}
				try {
					System.out.println(name = " is wating");
					forCust.await();
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}

		} finally {
			lock.unlock();
		}
	}
	public int dishNum() {
		return dishName.length;
	}
}

class Cook implements Runnable{
	private Table table;
	Cook(Table table){
		this.table=table;
	}
	public void run() {
		while(true) {
			int inx=(int)(Math.random()*table.dishNum());
			table.add(table.dishName[inx]);
			try {
				Thread.sleep(1);
			}catch(InterruptedException e) {}
		}
	}
}

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
			table.remove(food);
			System.out.println(name+"ate a  "+food);
		}
	}
}

public class ThreadWaitEx4 {

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

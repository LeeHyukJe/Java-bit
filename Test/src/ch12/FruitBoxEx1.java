package ch12;

import java.util.ArrayList;

class Fruit implements Eatable{
	public String toString() {
		return "fruit";
	}
}

class Apple extends Fruit{
	public String toString() {
		return "Apple";
	}
}

class Grape extends Fruit{
	public String toString() {
		return "Grape";
	}
}

class Toy{
	public String toString() {
		return "Toy";
	}
}

interface Eatable{}

class Box<T extends Fruit>{
	ArrayList<T> list=new ArrayList<T>();
	void add(T item) {
		list.add(item);
	}
	T get(int i) {
		return list.get(i);
	}
	int size() {
		return list.size();
	}
	
	public String toString() {
		return list.toString();
	}
}

public class FruitBoxEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box<Fruit> box= new Box<>();
		box.add(new Apple());
		System.out.println(box.get(0));
	}

}

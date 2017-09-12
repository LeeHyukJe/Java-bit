package ch12;

import java.util.ArrayList;


class Boxes<T extends Fruits>{
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
	
	ArrayList<T> getList(){
		return list;
	}
	public String toString() {
		return list.toString();
	}
}




class Fruits{
	public String toString() {
		return "Fruit";
	}
}

class Orange extends Fruits{
	public String toString() {
		return "Orange";
	}
}

class Melon extends Fruits{
	public String toString() {
		return "Melon";
	}
}

class Juice{
	String name;
	
	Juice(String name){
		this.name=name+"Juice";
	}
	
	public String toString() {
		return name;
	}
}


class Jucier{
	static <T extends Fruits> Juice makeJuice(Boxes <T> box) {
		String temp="";
		
		for(T f:box.getList()) {
			temp+=f+"";
		}
		return new Juice(temp);
	}
}

public class FruitBoxEx3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Boxes<Fruits> FruitsBox=new Boxes<>();
		Boxes<Orange> orangeBox=new Boxes<>();
		Boxes<Melon> MelonBox=new Boxes<>();
		
		//FruitsBox.add(orangeBox); <T>타입은 Fruits타입의 자손만 됨  orangeBox는 Boxes타입
		
		FruitsBox.add(new Fruits());
		orangeBox.add(new Orange());
		MelonBox.add(new Melon());
		
		System.out.println(Jucier.makeJuice(FruitsBox));
		System.out.println(Jucier.<Orange>makeJuice(orangeBox));
		System.out.println(Jucier./*<Melon>*/makeJuice(MelonBox)); //<Melon>생략 가능
		System.out.println(FruitsBox);
		System.out.println(FruitsBox.getList());
		
		
	}

}

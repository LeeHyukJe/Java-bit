interface Flyable{
	public void fly();
}

interface Singable{
	public void sing();
}

class Person implements Flyable, Singable{
	@Override
	public void fly() {
		System.out.println("��� ����� ���ƴٴѴ�!!");
	}

	@Override
	public void sing() {
		System.out.println("��� �뷡 �θ���!!");
	}
	
	public void walk() {
		System.out.println("��� �ȴ´�!!");
	}
}

class Fish implements Flyable{

	@Override
	public void fly() {
		System.out.println("��� ����Ⱑ ���ƴٴѴ�!!");
	}
	
	public void swim() {
		System.out.println("��� ����Ⱑ ���ģ��!!");
	}
	
}
public class InterfaceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p1=new Person();
		Fish f1=new Fish();
		Flyable[] flys= {p1,f1};
		
		for(int i=0;i<flys.length;i++) {
			if(flys[i] instanceof Person)
				((Person)flys[i]).walk();
			if(flys[i] instanceof Fish)
				((Fish)flys[i]).swim();
		}
	}

}

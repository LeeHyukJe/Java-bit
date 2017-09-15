interface Flyable{
	public void fly();
}

interface Singable{
	public void sing();
}

class Person implements Flyable, Singable{
	@Override
	public void fly() {
		System.out.println("우왕 사람이 날아다닌다!!");
	}

	@Override
	public void sing() {
		System.out.println("우왕 노래 부른다!!");
	}
	
	public void walk() {
		System.out.println("우왕 걷는다!!");
	}
}

class Fish implements Flyable{

	@Override
	public void fly() {
		System.out.println("우왕 물고기가 날아다닌다!!");
	}
	
	public void swim() {
		System.out.println("우왕 물고기가 헤엄친다!!");
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

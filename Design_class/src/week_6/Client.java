package week_6;

abstract class Robot{
	private String name;
	private MovingStrategy movingStrategy;
	private AttackStrategy attackStrategy;
	
	public Robot(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void move() {
		movingStrategy.move();
	}
	
	public void attack() {
		attackStrategy.attack();;
	}
	
	public void setMovingStrategy(MovingStrategy movingStrategy) {
		this.movingStrategy=movingStrategy;
	}
	public void setAttackStrategy(AttackStrategy attackStrategy) {
		this.attackStrategy=attackStrategy;
	}
}

class Atom extends Robot{
	public Atom(String name) {
		super(name);
	}
}

class TaekwonV extends Robot{
	public TaekwonV(String name) {
		super(name);
	}
}

interface MovingStrategy{
	public void move();
}

class FlyingStrategy implements MovingStrategy{

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("나는 날 수 있어요!");
	}
	
}

class WalkingStrategy implements MovingStrategy{

	@Override
	public void move() {
		// TODO Auto-generated method stub
		System.out.println("나는 오직 걸을 수만 있어요");
	}
	
}

interface AttackStrategy{
	public void attack();
}

class MissileStrategy implements AttackStrategy{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("나는 크고 아름다운 미사일이 있지!");
	}
	
}

class PunchStrategy implements AttackStrategy{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("나는 크고 아름다운 주먹이 있지!");
	}
	
}


public class Client {
	public static void main(String[] args) {
		Robot taekwonV=new TaekwonV("Taekwon");
		Robot atom=new Atom("Atom");
		
		taekwonV.setMovingStrategy(new WalkingStrategy());
		taekwonV.setAttackStrategy(new MissileStrategy());
		
		atom.setMovingStrategy(new FlyingStrategy());
		atom.setAttackStrategy(new PunchStrategy());
		
		System.out.println("My name is "+taekwonV.getName());
		taekwonV.move();
		taekwonV.attack();
		
		System.out.println();
		
		System.out.println("My name is "+atom.getName());
		atom.move();
		atom.attack();
	}
}

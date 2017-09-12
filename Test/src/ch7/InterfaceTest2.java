package ch7;

interface I{
	public void play();
}

class A {
	void autoPlay(I i) {
		i.play();
	}
}

class B implements I{
	public void play() {
		System.out.println("Play in B class");
	}
}

class C implements I{
	public void play() {
		System.out.println("Play in C class");
	}
}

public class InterfaceTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a = new A();
		a.autoPlay(new B());
		a.autoPlay(new C());
	}

}

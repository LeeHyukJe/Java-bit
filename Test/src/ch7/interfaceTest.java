package ch7;

class Ab {
	void methodA(Bb b) {
		b.methodB();
	}
}

class Bb{
	public void methodB() {
		System.out.println("Play in bb class");
	}
}

public class interfaceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ab a= new Ab();
		a.methodA(new Bb());
	}

}

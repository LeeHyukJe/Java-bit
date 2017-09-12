package ch7;

public class InnerEx6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object iv=new Object() {
			void method() {}
		};
		
		Object ob=new Object();
	}

}

class Object{
	void method() {};
}

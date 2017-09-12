package ch7;

interface Inter{
	public void methodB();
}



class Aa{
	void methodA() {
//		InstanceManager ins=new InstanceManager();
//		Inter i=ins.getStance(); getStance 메소드가 스태틱이 아닐시
		Inter i=InstanceManager.getStance();
		i.methodB();
		System.out.println(i.toString());
	}
}

class Bbb implements Inter{
	public void methodB() {
		System.out.println("methodB in B class");
	}
	
	public String toString() {
		return "class B";
	}
}

class InstanceManager{
	public static Inter getStance() {
		return new Bbb();
	}
}



public class InterfaceTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aa a=new Aa();
		a.methodA();
	}

}

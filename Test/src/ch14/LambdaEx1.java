package ch14;

@FunctionalInterface
interface MyFunction{
	void run(); //메소드가 단 하나! 단 하나!!!!
}

public class LambdaEx1 {

	static void execute(MyFunction f) {
		f.run();
	}
	
	static MyFunction getMyFunction() {
		MyFunction f=()->System.out.println("f3.run()");
		return f;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyFunction f1=()->System.out.println("f1.run"); //람다식으로
		
		MyFunction f2=new MyFunction() { //익명함수로 
			public void run() {
				System.out.println("f2.run()");
			}
		};
		
		MyFunction f3=getMyFunction(); //익명함수 참조
		f1.run();
		f2.run();
		f3.run();
	}

}

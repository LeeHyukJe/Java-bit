package ch14;

@FunctionalInterface
interface MyFunction{
	void run(); //�޼ҵ尡 �� �ϳ�! �� �ϳ�!!!!
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
		
		MyFunction f1=()->System.out.println("f1.run"); //���ٽ�����
		
		MyFunction f2=new MyFunction() { //�͸��Լ��� 
			public void run() {
				System.out.println("f2.run()");
			}
		};
		
		MyFunction f3=getMyFunction(); //�͸��Լ� ����
		f1.run();
		f2.run();
		f3.run();
	}

}

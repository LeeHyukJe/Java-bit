package ch14;

@FunctionalInterface
interface MyLambda{
	int plus(int a,int b);
}

public class Test implements MyLambda{

	@Override
	public int plus(int a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLambda my=(a,b)->a+b;// ¶÷´Ù½Ä
		Test test=new Test();
		int c= my.plus(3, 5);
		int d=test.plus(3, 5);
		
		System.out.println(c);
		System.out.println(d);
	}



}

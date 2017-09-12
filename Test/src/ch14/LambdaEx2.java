package ch14;

interface MyFunction2{
	void run();
}

public class LambdaEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFunction2 f =()->{};
		Object obj=(MyFunction)(()->{}); //MyFunction으로 형변환
		String str=((Object)(MyFunction)(()->{})).toString();
		System.out.println((MyFunction)()->{});
		System.out.println(str);
		System.out.println(obj);
	}

}

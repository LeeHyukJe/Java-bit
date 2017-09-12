
public class ExceptionEx5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(1);
		System.out.println(2);
		try {
			System.out.println(3);
			System.out.println(4/0);
		}catch(ArithmeticException ae) {
			System.out.println("0으로 나눌수 없습니다.");
		}
		System.out.println(6);
	}

}

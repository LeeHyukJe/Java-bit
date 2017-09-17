package interfaceEx;
interface ICook{
	public void makeRice();
	public void makeSoup();
	public void makeSalad();
	public void makeSource();
}

class KoreaCook implements ICook{

	@Override
	public void makeRice() {
		// TODO Auto-generated method stub
		System.out.println("밥!");
	}

	@Override
	public void makeSoup() {
		// TODO Auto-generated method stub
		System.out.println("국");
	}

	@Override
	public void makeSalad() {
		// TODO Auto-generated method stub
		System.out.println("무침");
	}

	@Override
	public void makeSource() {
		// TODO Auto-generated method stub
		System.out.println("고추장");
	}
	
}

class ChineseCook implements ICook{

	@Override
	public void makeRice() {
		// TODO Auto-generated method stub
		System.out.println("중국밥");
	}

	@Override
	public void makeSoup() {
		// TODO Auto-generated method stub
		System.out.println("중국국");
	}

	@Override
	public void makeSalad() {
		// TODO Auto-generated method stub
		System.out.println("중국무침");
	}

	@Override
	public void makeSource() {
		// TODO Auto-generated method stub
		System.out.println("두반장");
	}
	
}

class CookManager{
	private ICook cook;
	
	public CookManager(ICook cook) {
		this.cook=cook;
	}
	
	public void setCook(ICook cook) {
		this.cook=cook;
	}
	
	public void orderRice() {
		cook.makeRice();
	}
	
	public void orderSoup() {
		cook.makeSoup();
	}
}
public class CookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ICook cook=new KoreaCook();
		CookManager manager=new CookManager(cook);
		manager.orderRice();
		manager.orderSoup();
		
		ICook cook1=new ChineseCook();
		manager.setCook(cook1);
		manager.orderRice();
		manager.orderSoup();
	}

}

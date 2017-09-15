
public class PolyArgumentTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Buyer b=new Buyer();
		Tv tv=new Tv();
		Computer com=new Computer();
		Audio audio=new Audio();
		b.buy(tv);
		b.buy(com);
		b.buy(audio);
		b.summary();
	}

}

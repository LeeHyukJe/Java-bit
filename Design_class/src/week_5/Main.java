package week_5;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Song s1=new Song();
		s1.setMode(new NonDiscounted()); //위임
		Song s2=new Song();
		s2.setMode(new NonDiscounted()); //위임
		Song s3=new Song();
		s3.setMode(new Onsale()); //위임
		Song s4=new Song();
		s4.setMode(new TodayEvent()); //위임
		
		CartForSongs c=new CartForSongs();
		c.add(s1);
		c.add(s2);
		c.add(s3);
		c.add(s4);
		System.out.println(c.calculateTotalPrice());
	}

}

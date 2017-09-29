package week_5;

import java.util.ArrayList;
import java.util.Iterator;

public class CartForSongs { //클라이언트 
	ArrayList<Song> cart=new ArrayList<>();
	
	public double calculateTotalPrice() {
		double total=0.0;
		
		Iterator<Song> itr=cart.iterator();
		
		while(itr.hasNext()) {
			Song s= itr.next();
			total=total+s.getPrice();
		}
		return total;
	}
	
	public void add(Song s) {
		cart.add(s);
	}
}

package week_5;

public class Song {
	private DiscountMode mode;
	
	public void setMode(DiscountMode mode) { //다형성 적용
		this.mode=mode;
	}
	
	public double getPrice() {
		return 10.0-(10.0*this.mode.getDiscountRate());
	}
}

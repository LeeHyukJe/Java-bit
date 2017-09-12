package ch12;

enum Direction{
	East(1,">"), South(2,"v"), West(3,"<"), North(4,"^");
	private static final Direction[] DIR_ARR=Direction.values(); 
	private final int value;
	private final String symbol;
	
	Direction(int value, String symbol){
		this.value=value;
		this.symbol=symbol;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public static Direction of(int dir) {
		if(dir<1 || dir>4) {
			throw new IllegalArgumentException("Invaild value: "+dir);
		}
		return DIR_ARR[dir-1];
	}
	
	public Direction rotate(int num) {
		num%=4;
		if(num<0)
			num+=4;
		return  DIR_ARR[(value-1+num)%4];
	}
	
	public String toString() {
		return name()+getSymbol();
	}
}

public class EnumEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(Direction d:Direction.values()) {
			System.out.printf("%s=%d%n",d.name(),d.getValue());
		}
		
		Direction d1=Direction.East;
		Direction d2=Direction.of(1);
		
		System.out.printf("d1=%s, %d%n",d1.name(),d1.getValue());
		System.out.printf("d2=%s, %d%n",d2.name(),d2.getValue());
		
		System.out.println(Direction.East.rotate(1));
		System.out.println(Direction.East.rotate(2));
		System.out.println(Direction.East.rotate(-1));
		System.out.println(Direction.East.rotate(-2));
	}

}

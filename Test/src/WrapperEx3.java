
public class WrapperEx3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=10;
		
		Integer intg=(Integer)i;
		Object obj=(Object)i;
		
		Long lng=100L;
		int i2=intg+10;
		long l=intg+lng;
		
		Integer itng2=new Integer(20);
		int i3=(int)itng2;
		
		Integer intg3=itng2+i3;
	}

}

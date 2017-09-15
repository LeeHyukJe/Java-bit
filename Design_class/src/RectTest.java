
public class RectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1=new Point(10,10);
		Point p2=new Point(20,20);
		Rect a=new Rect(p1,p2);
		System.out.println("시작점("+a.getStart().getX()
				+","+a.getStart().getY()+")");
		System.out.println("끝점("+a.getEnd().getX()
				+","+a.getEnd().getY()+")");
		System.out.println("넓이="+a.getArea());
		Rect b=new Rect(10,10,20,20);
		System.out.println("시작점("+b.getStart().getX()
				+","+b.getStart().getY()+")");
		System.out.println("끝점("+b.getEnd().getX()
				+","+b.getEnd().getY()+")");
		System.out.println("넓이="+b.getArea());
	}

}

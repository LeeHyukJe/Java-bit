
public class RectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1=new Point(10,10);
		Point p2=new Point(20,20);
		Rect a=new Rect(p1,p2);
		System.out.println("������("+a.getStart().getX()
				+","+a.getStart().getY()+")");
		System.out.println("����("+a.getEnd().getX()
				+","+a.getEnd().getY()+")");
		System.out.println("����="+a.getArea());
		Rect b=new Rect(10,10,20,20);
		System.out.println("������("+b.getStart().getX()
				+","+b.getStart().getY()+")");
		System.out.println("����("+b.getEnd().getX()
				+","+b.getEnd().getY()+")");
		System.out.println("����="+b.getArea());
	}

}

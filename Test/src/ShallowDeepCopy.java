
class Circle implements Cloneable{
	Point p;
	double r;
	
	Circle(Point p, double r){
		this.p=p;
		this.r=r;
	}
	
	public Circle shallowcopy() { //얕은 복사
		Object obj=null;
		try {
			obj=super.clone();
		}catch(CloneNotSupportedException e) {}
		return (Circle)obj;
	}
	
	public Circle deepCopy() {
		Object obj=null;
		try {
			obj=super.clone();
		}catch(CloneNotSupportedException e) {}
		Circle c= (Circle)obj;
		c.p=new Point(this.p.x, this.p.y); //차이 주목!!! 객체 인스턴스를 또 하나 생성함
		return c;
	}
	public String toString() {
		return p+" "+r;
	}
}

class Point{
	int x,y;
	Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public String toString() {
		return "("+x+" "+y+")";
	}
}


public class ShallowDeepCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1= new Circle(new Point(1,1),2.0);
		Circle c2=c1.shallowcopy();
		Circle c3=c1.deepCopy();
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println("------------------(변경 후)");
		c1.p.x=2;
		c1.p.y=100;
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
	}

}

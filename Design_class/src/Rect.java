
public class Rect {
	private Point start,end;
	int sx,sy,ex,ey;

	Rect(Point start, Point end){
		this.start=start;
		this.end=end;
	}
	
	Rect(int sx,int sy,int ex,int ey){
		start=new Point(sx,sy);
		end=new Point(ex,ey);
	}
	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
	
	int getArea() {
		int width=end.getX()-start.getX();
		int height=end.getY()-start.getY();
		int area=width*height;
		return area;
	}
	
	
	
	int circum() {
		int width=end.getX()-start.getX();
		int height=end.getY()-start.getX();
		return 2*(width+height);
	}
}

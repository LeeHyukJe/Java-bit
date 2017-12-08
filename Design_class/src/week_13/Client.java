package week_13;

abstract class Display{
	public abstract void draw();
}

class RoadDisplay extends Display{

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("기본 도로 표시");
	}
	
}

abstract class DisplayDecorator extends Display{
	private Display decoratedDisplay;
	
	public DisplayDecorator(Display decoratedDisplay) {
		this.decoratedDisplay=decoratedDisplay;
	}
	
	public void draw() {
		decoratedDisplay.draw();
	}
}

class LaneDecorator extends DisplayDecorator{
	
	public LaneDecorator(Display decoratedDisplay) { //핵심 기능 클래스 설정
		super(decoratedDisplay);
	}
	
	public void drawLane() {
		System.out.println("\t 차선 표시");
	}
	
	public void draw() {
		super.draw();
		drawLane();
	}
	
}

class TrafficDecorator extends DisplayDecorator{
	
	public TrafficDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	
	public void drawTraffic() {
		System.out.println("\t 교통량 표시");
	}
	
	public void draw() {
		super.draw();
		drawTraffic();
	}
}

class CrossingDecorator extends DisplayDecorator{
	
	public CrossingDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	
	public void draw() {
		super.draw();
		drawCrossing();
	}
	
	public void drawCrossing() {
		System.out.println("\t 교차로 표시");
	}
}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display road=new RoadDisplay();
		road.draw(); //기본 도로 표시
		
		Display roadWithLane=new LaneDecorator(new RoadDisplay());
		roadWithLane.draw(); //추가 기능 실행
		
		Display roadWithTraffic =new TrafficDecorator(new RoadDisplay());
		roadWithTraffic.draw(); //추가 기능 실행
		
		Display roadWithCrossingAndLaneAndTraffic=new LaneDecorator(new TrafficDecorator(new RoadDisplay()));
		roadWithCrossingAndLaneAndTraffic.draw();
			
	}

}

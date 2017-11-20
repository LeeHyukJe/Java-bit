package week_12;

import java.util.ArrayList;
import java.util.List;

interface Observer2{
	public abstract void update();
}

abstract class Subject2{
	private List<Observer> observers=new ArrayList<>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(Observer o:observers) {
			o.update();
		}
	}
}

class ElevatorController extends Subject2{
	private int curFloor=1;
	
	public void gotoFloor(int destination) {
		
		curFloor=destination;
		notifyObservers();
	}
	
	public int getcurFloor() {
		return curFloor;
	}
}

class ElevatorDisplay implements Observer2{
	private ElevatorController controller;
	
	public ElevatorDisplay(ElevatorController controller) {
		this.controller=controller;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int curFloor=controller.getcurFloor();
		System.out.println("Elevator Display:"+curFloor);
	}
	
}

class VoiceNotice implements Observer2{
	private ElevatorController controller;
	
	public VoiceNotice(ElevatorController controller) {
		this.controller=controller;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int curFloor=controller.getcurFloor();
		System.out.println("Voice Notice "+curFloor);
	}
}

class FloorDisplay implements Observer2{
	private ElevatorController controller;
	
	public FloorDisplay(ElevatorController controller) {
		this.controller=controller;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int curFloor=controller.getcurFloor();
		System.out.println("Floor Display"+curFloor);
	}
	
}

class ControllerRoomDisplay implements Observer2{
	private ElevatorController controller;
	
	public ControllerRoomDisplay(ElevatorController controller) {
		this.controller=controller;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		int curFloor=controller.getcurFloor();
		System.out.println("Control Room"+curFloor);
	}
	
}
public class EleveClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ElevatorController controller=new ElevatorController();
		
		Observer2 elevatorDisplay=new ElevatorDisplay(controller);
		Observer2 voiceNotice=new VoiceNotice(controller);
		Observer2 floorDisplay=new FloorDisplay(controller);
		Observer2 controlRoom=new ControllerRoomDisplay(controller);
		
		controller.gotoFloor(5);
		controller.gotoFloor(10);
	}

}

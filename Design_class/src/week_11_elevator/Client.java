package week_11_elevator;

import java.util.ArrayList;
import java.util.List;


interface Command{
	public abstract void execute();
}

//엘리베이터 내부에서 버튼을 눌렀을 때 해당 엘리베이터를 지정된 층으로 이동시킴
class DestinationSelectionCommand implements Command{

	private ElevatorController controller; //이동될 엘리베이터에 대한 컨트롤러(receiver)
	private int destination; //목적지
	
	//생성자의 매개로서 목적지 층과 대상 엘리베이터의 이동을 담당하는
	//ElevatorController를 전달받음
	
	public DestinationSelectionCommand(int destination, ElevatorController controller) {
		this.controller=controller;
		this.destination=destination;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		controller.gotoFloor(destination);
	}
	
}

class ElevatorRequestCommand implements Command{
	
	private Direction direction;
	private int destination;
	
	private ElevatorManager manager;
	
	public ElevatorRequestCommand(int destination, Direction direction, ElevatorManager manager) {
		this.destination=destination;
		this.direction=direction;
		this.manager=manager;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		manager.requestElevator(destination,direction);
	}
	
}

class ElevatorController{
	private int id;
	private int curFloor;
	
	public ElevatorController(int id) {
		this.id=id;
	}
	
	public void gotoFloor(int destination) {
		curFloor=destination;
		System.out.println("현재 "+id+"엘리베이터는 "+curFloor);
	}
}

class ElevatorManager{
	private List<ElevatorController> controllers;
	
	public ElevatorManager(int controllerCount) {
		controllers=new ArrayList<ElevatorController>(controllerCount);
	}
	
	public void addController(ElevatorController controller) {
		controllers.add(controller);
	}
	
	public void requestElevator(int destination, Direction direction) {
		int selectedElevator=selectElevator(destination,direction);
		
		controllers.get(selectedElevator).gotoFloor(destination);
		
	}
	
	private int selectElevator(int destination, Direction direction) {
		return 0;
	}
}

class ElevatorButton{
	private Command command;
	
	public ElevatorButton(Command command) {
		this.command=command;
	}
	
	public void pressed() {
		command.execute();
	}
}

enum Direction{up,down}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ElevatorController controller1=new ElevatorController(1);
		ElevatorController controller2=new ElevatorController(2);
		
		ElevatorManager em=new ElevatorManager(2);
		em.addController(controller1);
		em.addController(controller2);
		
		Command destinationCommand1stController=new DestinationSelectionCommand(1,controller1);
		ElevatorButton destinationButton1stFloor=new ElevatorButton(destinationCommand1stController);
		destinationButton1stFloor.pressed();
		
		//2번
		Command destinationCommand2ndController=new DestinationSelectionCommand(2,controller2);
		ElevatorButton destinationButton2ndFloor=new ElevatorButton(destinationCommand2ndController);
		destinationButton2ndFloor.pressed();
		
		Command requestDownCommand=new ElevatorRequestCommand(2,Direction.down,em);
		ElevatorButton requestDownFloorButton2ndFloor=new ElevatorButton(requestDownCommand);
		requestDownFloorButton2ndFloor.pressed();
	}

}

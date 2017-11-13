package week_11;

interface Command{
	public abstract void execute();
}

class Button{
	private Command theCommand; //구체적인 명령을 알지 못함
	
	public Button(Command theCommand) {
		setCommand(theCommand);
	}
	
	public void setCommand(Command newCommand) {
		this.theCommand=newCommand;
	}
	
	public void pressed() {
		theCommand.execute(); //버튼이 눌리면 주어진 Command의 execute 메서드를 호출함
	}
}

class Lamp{
	public void turnOn() {
		System.out.println("Lamp on");
	}
}

class LampOnCommand implements Command{
	
	private Lamp theLamp;
	
	public LampOnCommand(Lamp theLamp) {
		this.theLamp=theLamp;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		theLamp.turnOn();
	}
	
}

class Alarm{
	public void start() {
		System.out.println("Alarming....");
	}
}

class AlarmOnCommand implements Command{
	private Alarm theAlarm;
	
	public AlarmOnCommand(Alarm theAlarm) {
		this.theAlarm=theAlarm;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		theAlarm.start();
	}
}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lamp lamp=new Lamp();
		Command lampOnCommand=new LampOnCommand(lamp);
	
		Button button1=new Button(lampOnCommand);
		button1.pressed();
		
		Alarm alarm=new Alarm();
		Command alarmOnCommand=new AlarmOnCommand(alarm);
		
		Button button2=new Button(alarmOnCommand);
		button2.pressed();
		
		button2.setCommand(lampOnCommand);
		button2.pressed();
	}

}

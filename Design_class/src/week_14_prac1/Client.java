package week_14_prac1;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Motor lgMotor=MotorFactory.getMotor(MotorID.LG);
		ElevatorController controller1=new ElevatorController(1,lgMotor);
		controller1.gotoFloor(5);
		controller1.gotoFloor(1);
	}

}

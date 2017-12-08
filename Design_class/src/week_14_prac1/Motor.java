package week_14_prac1;

enum Direction{UP,DOWN}
enum MotorStatus{MOVING,STOPPED}

public  abstract class Motor {
	private MotorStatus motorStatus;
	
	public Motor() {
		// TODO Auto-generated constructor stub
		motorStatus=MotorStatus.STOPPED;
	}

	public MotorStatus getMotorStatus() {
		return motorStatus;
	}
	
	private void setMotorStatus(MotorStatus motorStatus) {
		this.motorStatus=motorStatus;
	}
	
	public void move(Direction direction) {
		MotorStatus motorStatus=getMotorStatus();
		if(motorStatus==MotorStatus.MOVING) {
			return;
		}
		
		moveMotor(direction);
		setMotorStatus(MotorStatus.MOVING);
	}
	
	protected abstract void moveMotor(Direction direction);
	
	public void stop() {
		motorStatus=MotorStatus.STOPPED;
	}
}

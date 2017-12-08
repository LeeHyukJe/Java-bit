package week_14_prac1;

enum MotorID {LG,HyunDai};

public class MotorFactory {
	public static Motor getMotor(MotorID motorID) {
		Motor motor=null;
		
		switch(motorID) {
		case LG:
			motor=new LGMotor();
			break;
		case HyunDai:
			motor=new HyunDaiMotor();
			break;
		}
		
		return motor;
	}
}

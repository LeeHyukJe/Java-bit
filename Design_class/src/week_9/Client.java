package week_9;

interface LightState{
	public void on_button_pushed(Light light);
	public void off_button_pushed(Light light);
}

class ON implements LightState{
	private static LightState instance=new ON();
	
	public static LightState getInstance() {
		return instance;
	}

	@Override
	public void on_button_pushed(Light light) {
		// TODO Auto-generated method stub
		System.out.println("취침등 상태");
		light.setState(SLEEPING.getInstance());
	}

	@Override
	public void off_button_pushed(Light light) {
		// TODO Auto-generated method stub
		System.out.println("Light OFF");
		light.setState(OFF.getInstance());
	}
	
}

class OFF implements LightState{
	private static LightState instance=new OFF();
	
	public static LightState getInstance() {
		return instance;
	}

	@Override
	public void on_button_pushed(Light light) {
		// TODO Auto-generated method stub
		System.out.println("Light ON");
		light.setState(ON.getInstance());
	}

	@Override
	public void off_button_pushed(Light light) {
		// TODO Auto-generated method stub
		System.out.println("반응없음");
		light.setState(OFF.getInstance());
	}
	
}

class SLEEPING implements LightState{
	private static LightState instance=new SLEEPING();
	
	public static LightState getInstance() {
		return instance;
	}
	
	@Override
	public void on_button_pushed(Light light) {
		// TODO Auto-generated method stub
		System.out.println("Light On Back!!");
		light.setState(ON.getInstance());
	}

	@Override
	public void off_button_pushed(Light light) {
		// TODO Auto-generated method stub
		System.out.println("Light Off Back!");
		light.setState(OFF.getInstance());
	}
	
}

class Light{ //context역할
	private LightState state=OFF.getInstance();
	public void setState(LightState state) {
		this.state=state;
	}
	
	public void on_button_pushed() {
		state.on_button_pushed(this);
	}
	
	public void off_button_pushted() {
		state.off_button_pushed(this);
	}
}


public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Light light=new Light();
		light.on_button_pushed();
		light.on_button_pushed();
		light.on_button_pushed();
		light.off_button_pushted();
		light.on_button_pushed();
	}

}

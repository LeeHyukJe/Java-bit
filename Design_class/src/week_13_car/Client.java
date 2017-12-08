package week_13_car;

abstract class CarComponent{
	abstract int getPrice();
	abstract String getCarInfo();
}

class BasicCar extends CarComponent{
	private int price;
	public BasicCar(int price) {
		this.price=price;
	}
	
	@Override
	int getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	String getCarInfo() {
		// TODO Auto-generated method stub
		return "Car";
	}
	
}

abstract class CarOptionDecorator extends CarComponent{
	private CarComponent decoratedCar;
	
	public CarOptionDecorator(CarComponent decoratedCar) {
		this.decoratedCar=decoratedCar;
	}
	
	public int getPrice() {
		return decoratedCar.getPrice();
	}
	
	public String getCarInfo() {
		return decoratedCar.getCarInfo();
	}
}

class AirBagDecorator extends CarOptionDecorator{
	private int airBagPrice;
	
	public AirBagDecorator(CarComponent CarOptionDecorator,int airBagPrice) {
		super(CarOptionDecorator);
		this.airBagPrice=airBagPrice;
	}
	
	public int getPrice() {
		return super.getPrice()+airBagPrice;
	}
	
	private int getAirBagPrice() {
		return airBagPrice;
	}
	
	public String getCarInfo() {
		return super.getCarInfo()+"AirBagDecorator Built-in Car";
	}
}

class ESCDecorator extends CarOptionDecorator{
	private int escPrice;
	
	public ESCDecorator(CarComponent decoratedCar, int escPrice) {
		super(decoratedCar);
		this.escPrice=escPrice;
	}
	
	public int getPrice() {
		return super.getPrice()+escPrice;
	}
	
	private int getESCPrice() {
		return escPrice;
	}
	
	public String getCarInfo() {
		return super.getCarInfo()+"ESC Built-in Car";
	}
}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CarComponent car=new BasicCar(1000);
		for(String decoratorName:args) {
			if(decoratorName.equalsIgnoreCase("AirBag")) {
				car =new AirBagDecorator(car,100);
			}
			if(decoratorName.equalsIgnoreCase("ESC")) {
				car=new ESCDecorator(car,50);
			}
		}
		System.out.println(car.getPrice());
		System.out.println(car.getCarInfo());
	}

}

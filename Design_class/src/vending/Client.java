package vending;

class MoneyBox { //자판기의 돈 주머니
	private int money = 0;
	public int getMoney() { return money; }
	public void insert(int money) { this.money += money; }
	public void dispense(int price) { this.money -= price; }

	public int returnMoney() {
		int tmp = this.money;
		this.money = 0;
		return tmp;
	}
}

class NoMoneyState implements State { //돈이 없는 상황
	private VendingMachine machine;
	
	public NoMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney(int money) { 
		machine.getMoneyBox().insert(money);
		System.out.println(money + "원을 넣었습니다.");
		
		if(money >= machine.getDrinking().getPrice()) //넣을 돈이 먹으려는 자판기의 음료수  값보다 많을 때
			machine.setState(machine.getEnoughMoneyState()); //돈이 있는 상태
		else 
			machine.setState(machine.getNoEnoughMoneyState()); //돈이 없는 상태
		System.out.println("현재 금액은 " + machine.getMoneyBox().getMoney() + "원입니다."); 
	}

	@Override
	public void pushButton(int price) {
		System.out.println("동전을 넣어주세요.");
	}
	@Override
	public void returnMoney() {
		System.out.println("반환할 동전이 없습니다.");
	}
}

class SoldOutState implements State { //음료수가 없는 상태
	private VendingMachine machine;
	
	public SoldOutState(VendingMachine machine) {
		this.machine = machine;
	}
	@Override
	public void insertMoney(int money) {
		System.out.println("재고가 없습니다.");
	}
	@Override
	public void pushButton(int price) { } //다 팔려서 버튼을 눌러도 의미가 없음
	
	
	@Override
	public void returnMoney() {
		int tmp = machine.getMoneyBox().returnMoney(); //일시적으로 돈이 들어옴
		System.out.println(tmp + "원이 반환되었습니다.");
		machine.setState(machine.getNoMoneyState()); //품절 상태라 돈이 다시 반환 되고 돈 주머니에 돈이 없는 상태
	}
}

interface State {
	public void insertMoney(int money);
	public void pushButton(int price);
	public void returnMoney();
}

class VendingMachine { //자판기
	private State noMoneyState;
	private State noEnoughMoneyState; 
	private State enoughMoneyState;
	private State soldOutState;
	private State state;
	
	private MoneyBox moneyBox; 
	private Drinking drinking;
	
	public VendingMachine(int stock, int price) { //각종 상태 초기화
		noMoneyState = new NoMoneyState(this);
		noEnoughMoneyState = new NoEnoughMoneyState(this);
		enoughMoneyState = new EnoughMoneyState(this);
		soldOutState = new SoldOutState(this);
		
		moneyBox = new MoneyBox();
		drinking = new Drinking(stock, price);
		
		if(drinking.getStock() > 0)
			state = noMoneyState;
		else state = soldOutState;
	}
	public void setState(State state) { //상태 변화
		this.state = state;
	}
	public void insertMoney(int money) { //자판기에 돈을 넣었을 때
		state.insertMoney(money);
	}
	public void pushButton() { 
		int price = drinking.getPrice();
		state.pushButton(price);
	}
	public void returnMoney() {
		state.returnMoney();
	}
	public State getSoldOutState() { //품절 상태
		return soldOutState;
	}
	public State getEnoughMoneyState() { //음료수 뽑아 먹을 돈이 충분한 상태
		return enoughMoneyState;
	}
	public State getNoEnoughMoneyState() { // 음료수 값보다 돈이 적은 상태
		return noEnoughMoneyState;
	}
	public State getNoMoneyState() { //아예 돈이 없는 상태
		return noMoneyState;
	}
	public Drinking getDrinking() {  //마시고
		return drinking;
	}
	public MoneyBox getMoneyBox() { //돈 주머니 상태
		return moneyBox;
	}
}

class Drinking{
	private int price;
	private int stock;
	
	public Drinking(int stock, int price) {
		this.stock=stock;
		this.price=price;
	}
	
	public void dispense() {
		this.stock--;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
}

class NoEnoughMoneyState implements State{  //돈 부족 상태
	
	private VendingMachine machine;
	
	public NoEnoughMoneyState(VendingMachine machine) {
		this.machine=machine;
	}

	@Override
	public void insertMoney(int money) {
		// TODO Auto-generated method stub
		machine.getMoneyBox().insert(money);
		System.out.println(money+"원을 넣었습니다.");
		
		//돈을 충분히 가지고 있는 상태에서 음료수 값 보다 돈이 많은 경우
		if(machine.getMoneyBox().getMoney()>=machine.getDrinking().getPrice()) {
			machine.setState(machine.getEnoughMoneyState());
		}else {
			machine.setState(machine.getNoEnoughMoneyState());
		}
		System.out.println("현재 금액은"+machine.getMoneyBox().getMoney()+"원 입니다.");
	}

	@Override
	public void pushButton(int price) {
		// TODO Auto-generated method stub
		System.out.println("동전이 부족합니다.");
	}

	@Override
	public void returnMoney() {
		// TODO Auto-generated method stub
		int temp=machine.getMoneyBox().returnMoney();
		System.out.println(temp+"원이 반환되었습니다.");
		machine.setState(machine.getNoMoneyState());
	}
	
}

class EnoughMoneyState implements State{
	
	private VendingMachine machine;
	
	public EnoughMoneyState(VendingMachine machine) {
		this.machine=machine;
	}

	@Override
	public void insertMoney(int money) {
		// TODO Auto-generated method stub
		machine.getMoneyBox().insert(money);
		System.out.println(money+"돈을 넣었습니다.");
		System.out.println("현재 금액은 "+machine.getMoneyBox().getMoney()+" 원 입니다.");
	}

	@Override
	public void pushButton(int price) {
		// TODO Auto-generated method stub
		machine.getMoneyBox().dispense(price);
		machine.getDrinking().dispense();
		System.out.println("음료수가 나왔습니다.");
		
		//음료수가 품절 상태라면
		if(machine.getDrinking().getStock()==0) {
			machine.setState(machine.getSoldOutState());
		}
		//돈이 부족할 경우
		else if(machine.getMoneyBox().getMoney()<machine.getDrinking().getPrice()) {
			machine.setState(machine.getNoEnoughMoneyState());
		}
		//돈이 아예 없는 경우
		else if(machine.getMoneyBox().getMoney()==0) {
			machine.setState(machine.getNoMoneyState());
		}
	}

	@Override
	public void returnMoney() {
		// TODO Auto-generated method stub
		int temp=machine.getMoneyBox().returnMoney();
		System.out.println(temp+" 원이 반환되었습니다.");
		machine.setState(machine.getNoMoneyState());
		//
	}
}



public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VendingMachine machine=new VendingMachine(5,1000);
		machine.insertMoney(1000);
		machine.insertMoney(500);
		machine.pushButton();
		machine.returnMoney();
		machine.insertMoney(1000);
		machine.insertMoney(1000);
		machine.pushButton();
	}

}

package week_4;

abstract  class PrinterImpl{
	private String ID;
	public PrinterImpl(String ID) {
		this.ID=ID;
	}
	
	public String getID() {
		return ID;
	}
	
	abstract public void print(Object msg);
}

class Printer{
	private String ID;
	private PrinterImpl printerImpl;
	public Printer(PrinterImpl printerImpl) {
		this.printerImpl=printerImpl;
	}
	
	public String getID() {
		return printerImpl.getID();
	}
	
	public void print(Object msg) {
		printerImpl.print(msg);
	}
}

class DotPrinterImpl extends PrinterImpl{
	public DotPrinterImpl(String ID) {
		super(ID);
	}

	@Override
	public void print(Object msg) {
		// TODO Auto-generated method stub
		System.out.println("도트 방식으로 프린트 시작");
		System.out.println(msg.toString());
		System.out.println("*도트 방식 프린터 종료");
	}
	
	
}

class InkjetPinrterImpl extends PrinterImpl{
	public InkjetPinrterImpl (String ID) {
		super(ID);
	}

	@Override
	public void print(Object msg) {
		// TODO Auto-generated method stub
		System.out.println("잉크젯 방식으로 프린트 시작");
		System.out.println(msg.toString());
		System.out.println("*잉크젯 방식 프린터 종료");
	}
}

class LaserPrinterImpl extends PrinterImpl{
	public LaserPrinterImpl (String ID) {
		super(ID);
	}

	@Override
	public void print(Object msg) {
		// TODO Auto-generated method stub
		System.out.println("레이저 방식으로 프린트 시작");
		System.out.println(msg.toString());
		System.out.println("*레이저 방식 프린터 종료");
	}
}
public class PrinterExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer[] printers=new Printer[3];
		printers[0]=new Printer(new InkjetPinrterImpl("101"));
		printers[1]=new Printer(new DotPrinterImpl("102"));
		printers[2]=new Printer(new LaserPrinterImpl("103"));
		for(int i=0;i<printers.length;i++) {
			if(printers[i].getID().equals("101")) {
				System.out.println("101 프린터 수리 요망");
				continue;
			}
			printerWelcome(printers[i]);
		}
		
	}
	public static void printerWelcome(Printer printer) {
		printer.print("");
	}
}

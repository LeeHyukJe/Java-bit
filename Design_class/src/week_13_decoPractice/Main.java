package week_13_decoPractice;

abstract class Display{
	public abstract int getColumns();
	public abstract int getRows();
	public abstract String getRowText(int row);
	
	public void show() {
		for(int i=0;i<getRows();i++) {
			System.out.println(getRowText(i));
		}
	}
}

class StringDisplay extends Display{
	private String string;
	
	public StringDisplay(String string) {
		this.string=string;
	}

	@Override
	public int getColumns() {
		// TODO Auto-generated method stub
		return string.getBytes().length;
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getRowText(int row) {
		// TODO Auto-generated method stub
		if(row==0) {
			return string;
		}
		else {
			return null;
		}
	}
}

abstract class Border extends Display{
	protected Display display;
	
	protected Border(Display display) {
		this.display=display;
	}
}

class SideBorder extends Border{
	private char borderChar;
	
	public SideBorder(Display display, char ch) {
		super(display);
		this.borderChar=ch;
	}

	protected SideBorder(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumns() {
		// TODO Auto-generated method stub
		return 1+display.getColumns()+1;
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return display.getRows();
	}

	@Override
	public String getRowText(int row) {
		// TODO Auto-generated method stub
		return borderChar+display.getRowText(row)+borderChar;
	}
}

class FullBorder extends Border{

	protected FullBorder(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumns() {
		// TODO Auto-generated method stub
		return 1+display.getColumns()+1;
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return 1+display.getRows()+1;
	}

	@Override
	public String getRowText(int row) {
		// TODO Auto-generated method stub
		if(row==0) {
			return "+"+makeLine('-',display.getColumns())+"+";
		}
		else if(row==display.getRows()+1) {
			return "+"+makeLine('-',display.getColumns())+"+";
		}
		else {
			return "|"+display.getRowText(row-1)+"|";
		}
	}
	
	private String makeLine(char ch, int count) {
		StringBuffer buf=new StringBuffer();
		for(int i=0;i<count;i++) {
			buf.append(ch);
		}
		return buf.toString();
	}
	
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display b1=new StringDisplay("hello.world!");
		Display b2=new SideBorder(b1,'#');
		Display b3=new FullBorder(b2);
		b1.show();
		b2.show();
		b3.show();
								
	}

}

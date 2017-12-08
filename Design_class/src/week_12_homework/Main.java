package week_12_homework;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


//화면에 표시할 JFrame 옵저버
class ShowGraphicObserver extends JFrame implements Observer{
	
	private JPanel panel=new JPanel();
	private JPanel CirclePanel=new JPanel();// 원 그리는 패널
	private JLabel label=new JLabel(); //텍스트로 보여질 부분
	private JLabel label2=new JLabel(); //별표가 보여질 부분
	int angle=0;
	DrawCircle drawcircle;
	public ShowGraphicObserver() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,1200);
		setVisible(true);
		
		setLayout(new BorderLayout());
		add(panel,"North"); //JFrame에 panel붙이기
		add(CirclePanel,"Center"); //JFrame에 CirclePanel 붙이기
		
		
		panel.setLayout(new BorderLayout()); //패널 레이아웃 설정
		CirclePanel.setLayout(new BorderLayout()); //원 그리는 패널 레이아웃 설정
		panel.add(label, "North");
		panel.add(label2, "Center");
		drawcircle=new DrawCircle(10);
		CirclePanel.add(drawcircle, "Center");
	}

	@Override
	public void update(NumberGenerator generator) {
		// TODO Auto-generated method stub
		int number=generator.getNumber();
		this.angle=number;
		String tmp="";
		try {
			Thread.sleep(500);
		}catch(Exception e) {}
		label.setText(number+"");
		for(int i=1;i<number;i++) {
//			label2.setText(tmp+="*");
//			drawcircle.setAngle(number);
//			drawcircle.repaint();
			tmp+="*";
		}
		label2.setText(tmp);
		drawcircle.setAngle(number);
		drawcircle.repaint();
	}
}

class DrawCircle extends Canvas{
	int angle;
	
	public DrawCircle(int angle) {
		this.angle=angle;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillArc(100, 100, 900, 900, 90, (angle*-1));
		//g.fillArc(x, y, width, height, startAngle, arcAngle);
	}
	
	public void setAngle(int angle) {
		this.angle=angle;
	}
}

//Subject
abstract class NumberGenerator{
	private ArrayList<Observer> observers=new ArrayList<>(); //옵저버를 담는 배열
	
	public void addObserver(Observer observer) { //옵저버를 추가
		observers.add(observer);
	}
	
	public void deleteObserver(Observer observer) { //옵저버를 삭제
		observers.remove(observer);
	}
	
	public void notifyObservers() { //변경됨을 통보하는 메소드
		Iterator<Observer> it=observers.iterator();
		
		while(it.hasNext()) {
			Observer o=it.next(); 
			o.update(this); //배열에 들어있는 옵저버에게 통보
		}
	}
	
	public abstract int getNumber();
	public abstract void execute();
}

//Concrete Subject
class IncrementalNumberGenerator extends NumberGenerator{
	private int number;
	private int end;
	private int inc; //inc만큼 증가 하기 위한 변수
	
	public IncrementalNumberGenerator(int start, int end, int inc) { //초기화
		this.number=start;
		this.end=end;
		this.inc=inc;
	}

	@Override
	public int getNumber() { //setState 역할 (상태 변경)
		// TODO Auto-generated method stub
		return number;
	}
	
	@Override
	public void execute() { //변경됨을 통보
		// TODO Auto-generated method stub
		while(true) {
			number+=(int)(Math.random()*10)+1;
			notifyObservers();
			
		}
	}
	
}

interface Observer{ //옵저버
	public abstract void update(NumberGenerator generator);
}

//concrete Observer
class DigitObserver implements Observer{

	@Override
	public void update(NumberGenerator generator) {
		// TODO Auto-generated method stub
		System.out.println("DigitObserver:"+generator.getNumber());
		try {
			Thread.sleep(1000);
		}catch(Exception e) {}
	}
}

//Concrete Observer
class GraphicObserver implements Observer{

	@Override
	public void update(NumberGenerator generator) {
		// TODO Auto-generated method stub
		System.out.println("GraphicObserver");
		int count=generator.getNumber();
		
		for(int i=0;i<count;i++) {
			System.out.print("*");
		}
		System.out.println("");
		try {
			Thread.sleep(1000);
		}catch(Exception e) {}
	}
	
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumberGenerator generator=new IncrementalNumberGenerator(10,50,5);
		Observer observer1=new DigitObserver();
		Observer observer2=new GraphicObserver();
		Observer observer3=new ShowGraphicObserver();
		generator.addObserver(observer1);
		generator.addObserver(observer2);
		generator.addObserver(observer3);
		generator.execute();
	}

}

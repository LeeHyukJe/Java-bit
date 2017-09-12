package ch7;
import java.awt.*;
import java.awt.event.*;

public class InnerEx7_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Button bnt=new Button("start");
		EventHandler handle=new EventHandler();
		bnt.addActionListener(handle); //참조변수를 이용하여 작성
		bnt.addActionListener(new EventHandler()); //바로 인스턴스화해서 작성
		bnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //익명함수를 이용하여 작성
				System.out.println("action Activated!");
			}
		});
	}

}

class EventHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		System.out.println("action activated!");
	}
}

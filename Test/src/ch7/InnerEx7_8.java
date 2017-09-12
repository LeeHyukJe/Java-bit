package ch7;
import java.awt.*;
import java.awt.event.*;

public class InnerEx7_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Button bnt=new Button("start");
		EventHandler handle=new EventHandler();
		bnt.addActionListener(handle); //���������� �̿��Ͽ� �ۼ�
		bnt.addActionListener(new EventHandler()); //�ٷ� �ν��Ͻ�ȭ�ؼ� �ۼ�
		bnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //�͸��Լ��� �̿��Ͽ� �ۼ�
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

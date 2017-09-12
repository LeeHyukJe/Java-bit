package JavaGUI;

import java.awt.*;

class FrameTest{ 
	public static void main(String args[]){ 
		Frame f=new Frame("Login"); 
		f.setSize(500,500);
		
		Button btn= new Button("누르지 마");
		btn.setSize(50, 50);
		btn.setLocation(100,75);
		f.setVisible(true);
		f.add(btn);
	}
}

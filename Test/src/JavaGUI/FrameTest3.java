package JavaGUI;

import java.awt.*;
import java.awt.event.*;

public class FrameTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f=new Frame("Login");
		f.setSize(300,200);
		f.setVisible(true);
		Panel panel=new Panel();
		panel.setLayout(new FlowLayout());
		f.add(panel);
		Button b=new Button("누르지 마");
		panel.add(b);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
			}
		});
		f.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
				
			}
			

			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
				System.exit(0);}

			@Override
			public void windowClosed(WindowEvent e) {		
			}

			@Override
			public void windowIconified(WindowEvent e) {// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
	}

}



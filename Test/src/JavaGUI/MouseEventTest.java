package JavaGUI;
import java.awt.*;
import java.awt.event.*;

public class MouseEventTest extends Frame {
	Label location;
	Label loca=new Label();
	Thread th=new Thread(new Runnable() {
		public void run() {
			for(int i=0;i<100;i++) {
				loca.setText(i+"");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				loca.setSize(200,200);
				add(loca);
				repaint();
			}
		}
	});
	MouseEventTest(String title){
		super(title);
		location=new Label("Mouse Pointer Location: ");
		location.setSize(195, 15);
		location.setLocation(5,30);
		location.setBackground(Color.YELLOW);
		add(location);
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				location.setText(e.getX()+" "+e.getY());
			}
			
		});
		
		setSize(300,200);
		setLayout(new FlowLayout());
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MouseEventTest test=new MouseEventTest("MM");
		test.th.start();
	}

}

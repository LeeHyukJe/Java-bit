package JavaGUI;

import java.awt.Font;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class LookAndFeel extends JFrame{
	
	public static void setDefaultSize(int size) {

	    Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
	    Object[] keys = keySet.toArray(new Object[keySet.size()]);

	    for (Object key : keys) {

	        if (key != null && key.toString().toLowerCase().contains("font")) {

	            System.out.println(key);
	            Font font = UIManager.getDefaults().getFont(key);
	            if (font != null) {
	                font = font.deriveFont((float)size);
	                UIManager.put(key, font);
	            }

	        }

	    }
	}
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		LookAndFeel.setDefaultSize(30);
		
		JFrame window=new JFrame("LookAndFeel");
		window.setVisible(true);
		window.setSize(1000, 1000);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel=new JPanel();
		window.add(panel);
		
		JButton button=new JButton("LookAndFeel");
		panel.add(button);
		
	}
	
}





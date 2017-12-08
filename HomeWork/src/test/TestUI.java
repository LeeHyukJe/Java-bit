package test;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestUI extends JFrame {
	JPanel panel;
	
	public TestUI() {
		panel=new JPanel();
		JButton button=new JButton("hello");
		button.setSize(new Dimension(100,100));
		button.setFont(new Font(button.getFont().getName(),button.getFont().getStyle(),150));
		panel.add(button);
		
		setSize(1000,1000);
		setVisible(true);
		add(panel);
	}
	public static void main(String[] args) {
		new TestUI();
	}
}

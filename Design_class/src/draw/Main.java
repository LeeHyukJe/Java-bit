package draw;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

interface Command{
	public abstract void execute();
}

class DrawCommand implements Command{
	protected Drawable drawable;
	private Point position;
	
	public DrawCommand(Drawable drawable, Point position) {
		this.drawable=drawable;
		this.position=position;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawable.draw(position.x,position.y);
	}
	
}

class MacroCommand implements Command{
	private Stack<Command> commands=new Stack<>();
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Iterator<Command> it=commands.iterator();
		while(it.hasNext()) {
			it.next().execute();
		}
	}
	public void append(Command cmd) {
		if(cmd!=this) {
			commands.push(cmd);
		}
	}
	
	public void undo() {
		if(!commands.empty()) {
			commands.pop();
		}
	}
	
	public void clear() {
		commands.clear();
	}
}

interface Drawable{
	public abstract void draw(int x, int y);
}

class DrawCanvas extends Canvas implements Drawable{

	private Color color=Color.red;
	private int radius=6;
	
	private MacroCommand history;
	
	public DrawCanvas(int width, int height, MacroCommand history) {
		setSize(width, height);
		setBackground(Color.WHITE);
		this.history=history;
	}
	
	public void paint(Graphics g) {
		history.execute();
	}
	@Override
	public void draw(int x, int y) {
		// TODO Auto-generated method stub
		Graphics g=getGraphics();
		g.setColor(color);
		g.fillOval(x-radius, y-radius, radius*2,radius*2);
	}
	
}


public class Main extends JFrame implements ActionListener {
	private MacroCommand history=new MacroCommand();
	private DrawCanvas canvas=new DrawCanvas(400,400,history);
	private JButton clearButton=new JButton("clear");
	
	public Main(String title) {
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Command cmd=new DrawCommand(canvas,e.getPoint());
				history.append(cmd);
				cmd.execute();
			}
		});
		
		clearButton.addActionListener(this);
		
		Box buttonBox=new Box(BoxLayout.X_AXIS);
		buttonBox.add(clearButton);
		Box mainBox=new Box(BoxLayout.Y_AXIS);
		mainBox.add(buttonBox);
		mainBox.add(canvas);
		getContentPane().add(mainBox);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==clearButton) {
			history.clear();
			canvas.repaint();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main("Command Pattern Sample");
	}
	

}

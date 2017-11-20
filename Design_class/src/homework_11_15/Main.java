package homework_11_15;

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
	private Color color;
	
	public DrawCommand(Drawable drawable, Point position,Color color) {
		this.drawable=drawable;
		this.position=position;
		this.color=color;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		drawable.draw(position.x,position.y,color);
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
	public abstract void draw(int x, int y,Color color);
}

class DrawCanvas extends Canvas implements Drawable{

	public Color color=Color.RED;
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
	public void draw(int x, int y,Color color) {
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
	private JButton RedColorButton=new JButton("Red");
	private JButton BlueColorButton=new JButton("blue");
	private JButton GreenColorButton=new JButton("Green");
	
	private JButton UndoButton=new JButton("Undo");
	
	public Main(String title) {
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				Command cmd=new DrawCommand(canvas,e.getPoint(),canvas.color);
				history.append(cmd);
				cmd.execute();
			}
		});
		
		clearButton.addActionListener(this); //클리어버튼
		
		//색깔 바꾸기
		BlueColorButton.addActionListener(this);
		RedColorButton.addActionListener(this);
		GreenColorButton.addActionListener(this);
		
		//undo하기
		UndoButton.addActionListener(this);
		
		Box buttonBox=new Box(BoxLayout.X_AXIS);
		//버튼 붙이기
		buttonBox.add(clearButton); //클리어 버튼
		buttonBox.add(RedColorButton); //뻘겋게 칠하기
		buttonBox.add(GreenColorButton); //녹색으로 칠하기
		buttonBox.add(BlueColorButton); //퍼렇게 칠하기
		buttonBox.add(UndoButton);
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
		else if(e.getSource()==BlueColorButton) {
			canvas.color=Color.BLUE;
		}
		else if(e.getSource()==RedColorButton) {
			canvas.color=Color.RED;
		}
		else if(e.getSource()==GreenColorButton) {
			canvas.color=Color.GREEN;
		}
		else if(e.getSource()==UndoButton) {
			history.undo();
			canvas.repaint();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main("Command Pattern Sample");
	}
}

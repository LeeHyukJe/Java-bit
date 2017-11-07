package week_6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class BallFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	private Field field;

	public BallFrame(Ball[] balls) {
		super("Balls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		field = new Field(balls);
		Thread th = new Thread(field);
		th.start();

		add(field, BorderLayout.CENTER);

		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
}

class Field extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private Ball[] balls;

	public Field(Ball[] balls) {
		this.balls = balls;
		setLayout(new FlowLayout());
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, BallFrame.WIDTH, BallFrame.HEIGHT);
		for (int i = 0; i < balls.length; i++) {
			g.setColor(balls[i].getColor());
			g.fillOval(balls[i].getX(), balls[i].getY(), Ball.SIZE, Ball.SIZE);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}

}

class Ball extends Thread {
	public static final int SIZE = 20;
	public static final int INTERVAL = 10;
	private int x, y;
	private int xInterval, yInterval;
	private DirectionStrategy directionStrategy;
	private DrawStrategy drawStrategy;
	private Color color;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.xInterval = yInterval = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getXInterval() {
		return this.xInterval;
	}

	public int getYInterval() {
		return this.yInterval;
	}

	public void setIntervals(int xInterval, int yInterval) {
		this.xInterval = xInterval;
		this.yInterval = yInterval;
	}

	public void setDirectionStrategy(DirectionStrategy directionStrategy) {
		this.directionStrategy = directionStrategy;
	}

	public void setDrawStrategy(DrawStrategy drawStrategy) {
		this.drawStrategy = drawStrategy;
	}

	public void draw() {
		drawStrategy.draw(this);
	}

	public void move() {
		directionStrategy.move(this);
	}

	public void run() {
		draw();
		move();
	}
}

abstract class DirectionStrategy {
	public abstract void move(Ball ball);
}

class HorizonalMoveStrategy extends DirectionStrategy {
	public void move(Ball ball) {
		ball.setIntervals(Ball.INTERVAL, 0);
		while (true) {
			ball.setX(ball.getX() + ball.getXInterval());
			if (ball.getX() < 0 && ball.getXInterval() < 0
					|| ball.getX() + ball.SIZE > BallFrame.WIDTH - 15 && ball.getXInterval() > 0) {
				ball.setIntervals(-ball.getXInterval(), 0);
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}

	}
}

class VerticalMoveStrategy extends DirectionStrategy {
	@Override
	public void move(Ball ball) {
		// TODO Auto-generated method stub
		ball.setIntervals(0, Ball.INTERVAL);
		while (true) {
			ball.setY(ball.getY() + ball.getYInterval());
			if (ball.getY() < 0 && ball.getYInterval() < 0
					|| ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 40 && ball.getYInterval() > 0) {

				// 프레임 경계에 도달했을 때 볼의 진행 방향을 바꿈
				ball.setIntervals(0, -ball.getYInterval());
			}
			try {
				Thread.sleep(30);

			} catch (InterruptedException e) {
			}
		}
	}
}

class DiagonalMoveStrategy extends DirectionStrategy {

	@Override
	public void move(Ball ball) {
		// TODO Auto-generated method stub
		ball.setIntervals(Ball.INTERVAL, Ball.INTERVAL);
		while (true) {
			ball.setX(ball.getX() + ball.getXInterval());
			ball.setY(ball.getY() + ball.getYInterval());
			if (ball.getX() < 0 && ball.getXInterval() < 0
					|| ball.getX() + Ball.SIZE > BallFrame.WIDTH - 15 && ball.getXInterval() > 0) {
				ball.setIntervals(-ball.getXInterval(), ball.getYInterval());
			}

			if (ball.getY() < 0 && ball.getYInterval() < 0
					|| ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 40 && ball.getYInterval() > 0) {
				ball.setIntervals(ball.getXInterval(), -ball.getYInterval());
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
	}
}

abstract class DrawStrategy {
	public void draw(Ball ball) {
		ball.setColor(Color.RED);
	}
}

class RedDrawStrategy extends DrawStrategy {
	public void draw(Ball ball) {
		ball.setColor(Color.BLUE);
	}
}

public class CircleClient {
	private static final int INIT_LOCATION[] = { 50, 100, 150 };

	public static void main(String[] args) {
		Ball[] balls = new Ball[3];
		balls[0]=new Ball(INIT_LOCATION[0],INIT_LOCATION[0]);
		balls[0].setDirectionStrategy(new HorizonalMoveStrategy());
		balls[0].setDrawStrategy(new RedDrawStrategy());
		
		balls[1]=new Ball(INIT_LOCATION[1],INIT_LOCATION[1]);
		balls[1].setDirectionStrategy(new VerticalMoveStrategy());
		balls[1].setDrawStrategy(new RedDrawStrategy());
		
		balls[2]=new Ball(INIT_LOCATION[2],INIT_LOCATION[2]);
		balls[2].setDirectionStrategy(new DiagonalMoveStrategy());
		balls[2].setDrawStrategy(new RedDrawStrategy());

		
		balls[0].start();
		balls[1].start();
		balls[2].start();
		

		new BallFrame(balls);
	}
}

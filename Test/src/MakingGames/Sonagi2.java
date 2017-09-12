package MakingGames;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Sonagi2 extends Frame {
	final int FRAME_WIDTH=400;
	final int FRAME_HEIGHT=300;
	
	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;
	
	int speed=500; //�������� �ӵ�
	int interval = 2 * 1000; //���ο� �ܾ ������ ����
	
	
	int level=0;
	int score=0;
	int line=0;
	int life = 3; // ������ ������

	boolean isPlaying=false;
	
	
	FontMetrics fm;
	
	//ServerSocket
	ServerSocket serverSocket;
	
	//Socket
	Socket socket;
	
	ArrayList<Word> words = new ArrayList<>(); // Word��ü �迭
	  String[] data = {"�¿�","����","����","ȿ��","����","����","Ƽ�Ĵ�","���","����ī"}; 
	Thread wg=null;
	Thread wd=null;

	TextField tf=new TextField();
	Panel pScore=new Panel(new GridLayout(1,3));
	Label lbLevel=new Label("Level: "+level,Label.CENTER);
	Label lbScore=new Label("Score: "+score,Label.CENTER);
	Label lbLife=new Label("Life: "+life,Label.CENTER);
	MyCanvas screen=new MyCanvas();
	
	Sonagi2(){
		this("�ҳ��� ver 1.0");
	}
	
	Sonagi2(String title){
		super(title);
		
		pScore.setBackground(Color.CYAN);
		pScore.add(lbLevel);
		pScore.add(lbScore);
		pScore.add(lbLife);
		add(pScore,"North");
		add(screen,"Center");
		add(tf,"South");
		
		MyEventHandler handler=new MyEventHandler();
		addWindowListener(handler);
		tf.addActionListener(handler);
		
		setBounds(500,200,FRAME_WIDTH,FRAME_HEIGHT);
		setResizable(false);
		setVisible(true);
		
		SCREEN_WIDTH=screen.getWidth();
		SCREEN_HEIGHT=screen.getHeight();
		fm=getFontMetrics(getFont());
	}
	
	public void repaint() {
		super.repaint();
		screen.repaint();
	}
	
	public void start() {
		isPlaying=true;
		
		wg = new Thread(new WordGenerator());
		wg.start();
		
		wd = new Thread(new WordDropper());
		wd.start();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sonagi2 game = new Sonagi2();
		game.start();

//		ArrayList words = game.words; // word��ü �޾ƿ�
//		while (true) {
//
//			System.out.println("LIFE:" + game.life + " " + "SCORE:" + game.score);
//			System.out.println(words);
//
//			String prompt = ">>";
//			System.out.println(prompt);
//
//			// ȭ�����κ��� ���δ����� �Է¹޴´�.
//			Scanner s = new Scanner(System.in);
//			String input = s.nextLine().trim();
//
//			for (int i = 0; i < words.size(); i++) {
//				// wordsȮ��
//				if (((Word) words.get(i)).word.equals(input)) {
//					words.remove(i);// �Է��� ���� words�� ������ ����
//					game.score += input.length() * ((Word) words.get(i)).y * 50;
//				}
//			}
//		}
	}

	public void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
		}
	}

	class WordGenerator implements Runnable {
		public void run() {
			while (isPlaying) {
				int rand = (int) (Math.random() * data.length);
				//���̷��� �����ֱ�
				boolean isVirus=((int)(Math.random()*10)+1)/10!=0; 
				
				words.add(new Word(data[rand],isVirus));// word�迭����Ʈ�� Word��ü �߰�
				delay(interval); //���� �ֱ�
				
				
			}
		}
	}

	class WordDropper implements Runnable {
		public void run() {
			outer:
			while (isPlaying) {
				delay(speed); // �ð� ����
				for (int i = 0; i < words.size(); i++) {
//					((Word) words.get(i)).y--; //y�� ����
//					if (((Word) words.get(i)).y < 0) {// y���� 0���� ������
//						words.remove(i);// �ܾ �����ϰ�
//						life--; // life ����
//					}
//					if (life == 0) { // life�� 0�� �Ǹ�
//						System.out.println("���� :" + life + " " + "����: " + score);
//						System.exit(0);
//					}
					
					Word temp=words.get(i);
					temp.y+=temp.step;
					
					if(temp.y>SCREEN_HEIGHT) {
						temp.y=SCREEN_HEIGHT;
						words.remove(temp);
						life--;
						lbLife.setText("Life: "+life);
						break;
					}
					
					if(life==0) {
						isPlaying=false;
						break outer;
					}
				}//end of for
				repaint();
			}
		} //end of run
	}
	
	class MyCanvas extends Canvas{
		public void clear() {
			Graphics g= getGraphics();
			g.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		}
		
		public void paint(Graphics g) {
			clear();
			
			for(int i=0;i<words.size();i++) {
				Word temp=(Word)words.get(i);
				g.setColor(temp.color);
				g.drawString(temp.word, temp.x,temp.y);
			}
		}
	}

	class Word {
		String word = ""; // �������� ����
		int x=0;
		int y=0;
		int step=5;
		
		Color color=Color.BLACK;
		boolean isVirus=false;

		Word(String word) {
			this(word, 10,false);
		}
		
		Word(String word, boolean isVirus){
			this(word,10,isVirus);
		}

		Word(String word, int step,boolean isVirus) {
			this.word = word;
			this.step=step;
			this.isVirus=isVirus;
			
			if(isVirus)
				color=Color.RED;
			
			int strWidth=fm.stringWidth(word);
			x=(int)(Math.random()*SCREEN_WIDTH);
			if(x+strWidth>=SCREEN_WIDTH) {
				x=SCREEN_WIDTH-strWidth;
			}
		}

		public String toString() {
			return word;
		}
	}
	
	class MyEventHandler extends WindowAdapter implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			String curText=tf.getText();
			tf.setText("");
			if(isPlaying) {
					for(int j=0;j<words.size();j++) {
						//textfield�� �Է��� ���� ã����
						Word temp=((Word)words.get(j));
						if(curText.equals(temp.word)) {
							words.remove(j);
							lbScore.setText("score: "+(score+=curText.length())+"");
						}
						if(temp.isVirus) {
							new VirusThread().start();
							break;
						}
					}
			}
		}
		public void windowClosing(WindowEvent e) {
			 e.getWindow().setVisible(false); 
	         e.getWindow().dispose(); 
	         System.exit(0);
		}
	}
	
	class VirusThread extends Thread{
		public void run() {
			int rand=(int)(Math.random()*5);
			
			int oldValue=0;
			int virusTime=10*1000;//10�ʰ�  ����
			
			switch(rand) {
				case 0:speed/=2; break;
				case 1:interval/=2; break;
				case 2:speed*=2; break;
				case 3:interval*=2; break;
				case 4:words.removeAll(words); break;
			}
		}
	}
}

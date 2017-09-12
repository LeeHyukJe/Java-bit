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
import java.util.ArrayList;

public class Sonagi extends Frame {
	final int FRAME_WIDTH=400;
	final int FRAME_HEIGHT=300;
	
	final int SCREEN_WIDTH;
	final int SCREEN_HEIGHT;
	
	int speed=500; //떨어지는 속도
	int interval = 2 * 1000; //새로운 단어가 나오는 간격
	
	
	int level=0;
	int score=0;
	int line=0;
	int life = 3; // 게임의 라이프

	boolean isPlaying=false;
	
	
	FontMetrics fm;
	
	ArrayList words = new ArrayList(); // Word객체 배열
	  String[] data = {"태연","유리","윤아","효연","수영","서현","티파니","써니","제시카"}; 
	Thread wg=null;
	Thread wd=null;

	TextField tf=new TextField();
	Panel pScore=new Panel(new GridLayout(1,3));
	Label lbLevel=new Label("Level: "+level,Label.CENTER);
	Label lbScore=new Label("Score: "+score,Label.CENTER);
	Label lbLife=new Label("Life: "+life,Label.CENTER);
	MyCanvas screen=new MyCanvas();
	
	Sonagi(){
		this("소나기 ver 1.0");
	}
	
	Sonagi(String title){
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
		Sonagi game = new Sonagi();
		game.start();

//		ArrayList words = game.words; // word객체 받아옴
//		while (true) {
//
//			System.out.println("LIFE:" + game.life + " " + "SCORE:" + game.score);
//			System.out.println(words);
//
//			String prompt = ">>";
//			System.out.println(prompt);
//
//			// 화면으로부터 라인단위로 입력받는다.
//			Scanner s = new Scanner(System.in);
//			String input = s.nextLine().trim();
//
//			for (int i = 0; i < words.size(); i++) {
//				// words확인
//				if (((Word) words.get(i)).word.equals(input)) {
//					words.remove(i);// 입력한 것이 words에 있으면 삭제
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
				words.add(new Word(data[rand]));// word배열리스트애 Word객체 추가
				delay(interval); //생성 주기
			}
		}
	}

	class WordDropper implements Runnable {
		public void run() {
			outer:
			while (isPlaying) {
				delay(speed); // 시간 지연
				for (int i = 0; i < words.size(); i++) {
//					((Word) words.get(i)).y--; //y값 감소
//					if (((Word) words.get(i)).y < 0) {// y값이 0보다 작으면
//						words.remove(i);// 단어를 삭제하고
//						life--; // life 감소
//					}
//					if (life == 0) { // life가 0이 되면
//						System.out.println("생명 :" + life + " " + "점수: " + score);
//						System.exit(0);
//					}
					
					Word temp=(Word)words.get(i);
					
					temp.y+=temp.step;
					
					if(temp.y>SCREEN_HEIGHT) {
						temp.y=SCREEN_HEIGHT;
						words.remove(temp);
						life--;
						lbLife.setText("Life: "+life);
						break;
					}
					
					if(life<0) {
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
				g.drawString(temp.word, temp.x,temp.y);
			}
		}
	}

	class Word {
		String word = ""; // 지워야할 글자
		int x=0;
		int y=0;
		int step=5;

		Word(String word) {
			this(word, 10);
		}

		Word(String word, int step) {
			this.word = word;
			this.step=step;
			
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
				for(int i=0;i<curText.length();i++) {
					for(int j=0;j<words.size();j++) {
						//textfield에 입력한 값을 찾으면
						if(curText.equals(((Word)words.get(j)).word)) {
							words.remove(j);
							lbScore.setText("score: "+(score+=curText.length())+"");
						}
					}
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

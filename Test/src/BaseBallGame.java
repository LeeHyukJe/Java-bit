import java.util.Scanner;

class Baseball {
	int game[] = new int[3];
	int flag[] = new int[3];
	int ballcnt;
	int strikecnt;
	int random;
	int flagVal;
	
	public void roll() {
		for (int i = 0; i < game.length; i++) {
			random = (int) (Math.random() * 10+1);
			game[i] = random;
			System.out.print(game[i]+" ");
		}
	}

	String check(int[] g, int[] a) {
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				if (g[i] == a[j]) {
					strikecnt++;
				}
			}
		}
		for(int i=0;i<g.length;i++) {
			if(g[i]==a[i]) {
				ballcnt++;
			}
		}
		//for(int i=0;i<3;i++){
		//	for(int j=0;j<3;j++){
		//		if(g[i]==a[i] &&i==j){
		//			strikecnt++;
		//		else if(g[i]==a[i] &&i!=j){
		//			ballcnt++;
		//		}
		//	}
		return strikecnt+"S"+ballcnt+"B";
	}

}

public class BaseBallGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int myAnswer[] = new int[3];
		System.out.println("3개의 숫자를 입력하십시오");
		for(int i=0;i<3;i++) {
			myAnswer[i]=sc.nextInt();
		}
		Baseball baseball=new Baseball();
		baseball.roll();
		baseball.check(baseball.game, myAnswer);
		
	}

}

package MakingGames;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;



class Card implements Comparable{
	int kind; // 1~4의 값을 갖는다.(1:club, 2:heart, 3:diamond, 4:spade
	int num; // 1~13의 값을 갖는다.
	String kinds[] = { "", "♣", "♥", "◆", "♠" };
	Card() {
		this(1, 1);
	}

	Card(int kind, int num) {
		this.kind = kind;
		this.num = num;
	}

	

	String check(Card[] cArr) {
		boolean threeCard = false;
		boolean fourCard = false;
		boolean flush = false;
		boolean straightFlush = false;
		boolean one_pair=false;
		int pair = 0;
		int straight = 0;
		String result;
		int[] cntArr = new int[13 + 1];
		int[] cntArrk = new int[4 + 1];
			
		for (int i = 0; i < 5; i++) {
			cntArr[cArr[i].num]++;

		}
		
		for (int i = 0; i < 5; i++) {
			cntArrk[cArr[i].kind]++;
		}
		
		for(int i=0;i<cArr.length;i++) {
			System.out.print("("+kinds[cArr[i].kind]+" "+cArr[i].num+")"+" ");
		}
		System.out.println();

		for (int i = 0; i < cntArr.length; i++) {
			if (cntArr[i] == 4)
				fourCard = true;
			else if (cntArr[i] == 3)
				threeCard = true;
			else if (cntArr[i] == 2)
				one_pair=true;
			else if (i < cntArr.length - 1 && (cntArr[i] == 1 && cntArr[i + 1] == 1))
				straight++;

		}
		for (int j = 0; j < cntArrk.length; j++) {
			if (cntArrk[j] == 5) {
				flush = true;
			}
		}
		
		for(int i=0;i<cArr.length;i++) {
			for(int j=0;j<cArr.length;j++) {
				if(i < cntArr.length - 1 && (cntArr[i] == 1 && cntArr[i + 1] == 1) && cntArrk[j] == 5 ) {
					straightFlush=true;
					straight=0;
					flush=false;
				}
			}
		}

		if (fourCard) {
			result = "four card!";
			return "four card!";
		} else if (threeCard) {
			result = "three Card";
			return "three Card!";
		} 
		else if(pair==1) {
			return "1 pair";
		}
		else if (one_pair) {
			result = "one pair";
			return "one pair!";
		} else if (straight == 4) {
			result = "Straight";
			return "Straight";
		} else if (flush) {
			result = "flush";
			return "flush";
		}
		
		return "no rank";
	}

	public String toString() {
		String kinds[] = { "", "CLOVER", "HEART", "DIAMOND", "SPADE" };
		String numbers = "0123456789XJQK";
		return "카드 : " + kinds[this.kind] + " " + numbers.charAt(this.num);
	}
	
	public int compareTo(Object o1) {
		if(o1 instanceof Comparable) {
			Card c=(Card)o1;
			return this.num-c.num;
		}
		return -1;
	}
}

class Deck{
	final int CARD_NUM = 52;
	int randomK, randomN;
	int k = 0;
	int flags[] = new int[CARD_NUM];
	Card arr[] = new Card[CARD_NUM]; //섞을 카드
	Card[] player1 = new Card[5];
	Card[] player2 = new Card[5];
	Card[] player3= new Card[5];
	Deck() {
		for (int i = 0; i < arr.length; i++) {
			do {
				randomK = (int) (Math.random() * 4 + 1);
				randomN = (int) (Math.random() * 13 + 1);
			} while (flags[i] == 1);
			flags[i] = 1;
			arr[i] = new Card(randomK, randomN);
		}
	}

	public void person_roll() {
		int roll;
		for (int i = 0; i < player1.length; i++) {
			roll = (int) (Math.random() * CARD_NUM);
			player1[i] = arr[roll];
		}
		for (int i = 0; i < player2.length; i++) {
			roll = (int) (Math.random() * CARD_NUM);
			player2[i] = arr[roll];
		}
		for (int i = 0; i < player3.length; i++) {
			roll = (int) (Math.random() * CARD_NUM);
			player3[i] = arr[roll];
		}
		
		Arrays.sort(player1,new CardSort());
		Arrays.sort(player2,new CardSort());
		Arrays.sort(player3,new CardSort());
	}
}

class CardSort implements Comparator{
	public int compare(Object o1, Object o2) {
		if(o1 instanceof Card && o2 instanceof Card) {
			int num=((Card)o1).num;
			int num2=((Card)o2).num;
			if(num==num2) {
				int kind=((Card)o1).kind;
				int kind2=((Card)o2).kind;
				return kind-kind2;
			}
			else {
				return num-num2;
			}
		}
		return -1;
	}
}

public class PokerGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		Card checkCard = new Card();
		deck.person_roll();
		System.out.println("플레이어1: "+checkCard.check(deck.player1));
		System.out.println("플레이어2: "+checkCard.check(deck.player2));
		System.out.println("플레이어3: "+checkCard.check(deck.player3));
	}

}

package MakingGames;
import static org.junit.Assert.*;

import org.junit.Test;



public class PokerTestCase {
	int kind; // 1~4의 값을 갖는다.(1:club, 2:heart, 3:diamond, 4:spade
	int num; //  1~13의 값을 갖는다.
	boolean threeCard=false;
	boolean fourCard=false;
	boolean flush=false;
	boolean straightFlush=true;
	int pair=0;
	int straight=0;
	String result;
	@Test
	public void test() {
		Card[] card={new Card(1,4),new Card(2,5),new Card(3,1),new Card(4,2),new Card(5,3)};
	}
	

	String check(Card[] cArr) {
		int [] cntArr=new int[13+1];
		int [] cntArrk=new int[4+1];
		
		for(int i=0;i<5;i++) {
			cntArr[cArr[i].num]++;
			cntArrk[cArr[i].kind]++;
		}
		

		for(int i=0;i<cntArr.length*2;i++) {
			if (cntArr[i%(cntArr.length)] == 4) {
				fourCard = true;
				break;
			}
			else if (cntArr[i%(cntArr.length)] == 3) {
				threeCard = true;
				break;
			}
			else if (cntArr[i%(cntArr.length)] == 2) {
				pair++;
				if(pair==1) {
					return "1 pair";
				}
				else if(pair==2) {
					return "2 pair";
				}
			}
			else if (i < cntArr.length - 1 && (cntArr[i%(cntArr.length)] == 1 &&cntArr[i+1%(cntArr.length)] == 1)) {
				straight++;
				if(straight==4) {
					return "Straight";
				}
			}
		}
		for (int j = 0; j < cntArrk.length; j++) { //플러시
			if (cntArrk[j% cntArrk.length] == 5) {
				flush = true;
				break;
			}
		}
		
		
//		if(fourCard) {
//			result="four card!";
//			return "four card!";
//		}
//		if(threeCard) {
//			result="three Card";
//			return "three Card!";
//		}
//		if(pair==2) {
//			result="2 pair";
//			return "2 pair!";
//		}
//		if(straight==4) {
//			result="Straight";
//			return "Straight";
//		}
//		if(flush) {
//			result="flush";
//			return "flush";
//		}
//		if(flush && straight==4)
//			return "StraightFlush";
		
		return "no rank";
	}
}
		

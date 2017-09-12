package ch11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.HashMap;

import java.util.Iterator;

import java.util.List;

import java.util.Map;

import java.util.Scanner;

import java.util.Set;

class Card implements Comparable {

	int kind; // 1~4

	int num; // 1~13

	Card() {

		this(1, 1);

	}

	Card(int kind, int num) {

		this.kind = kind;

		this.num = num;

	}

	public int compareTo(Object obj) { // ī�� ����

		if (obj instanceof Card) {

			return ((Card) obj).kind - this.kind;

		}

		return -1;

	}

}

class Player implements Comparable {

	ArrayList card = new ArrayList(); // player�� ���� ������ �ִ� 5���� ī��

	int rank;

	int money;

	String res = "";

	Player(Dealer dcard) {

		rank = 0;

		money = 0;

		for (int i = 0; i < 5; i++) {

			int ran = (int) (Math.random() * 52); // 52�� ����

			card.add(dcard.deck.get(ran));// 5���� ī�� ����

		}

		// Arrays.sort(card);

	}

	public int compareTo(Object obj) {// �÷��̾� ��ŷ ����

		if (obj instanceof Player) {

			return ((Player) obj).rank - this.rank;

		}

		return -1;

	}

}

class Dealer {

	ArrayList deck = new ArrayList(52);

	HashMap rankTable = new HashMap();

	int cntplayer;

	int flagsN[] = new int[15];

	int flagsK[] = new int[5];

	int randomK;

	int randomN;

	int k, n = 0;

	Dealer(ArrayList card, int cntplayer) { // ī��� �÷��̾� ��

		this.deck = card;

		this.cntplayer = cntplayer;

		// ī�� �����ϱ�

		for (int i = 0; i < 52; i++) { // 13�� ���� �ߺ����� �ʵ��� ����

			if (n % 13 == 0) {

				n = 0;

				Arrays.fill(flagsN, 0);

			}

			if (k % 4 == 0) {

				k = 0;

				Arrays.fill(flagsK, 0);

			}

			do {

				randomK = (int) (Math.random() * 4 + 1);

				randomN = (int) (Math.random() * 13 + 2);

			} while (flagsN[randomN] == 1 || flagsK[randomK] == 1);

			flagsN[k] = 1;

			flagsK[k] = 1;

			n++;

			k++;

			deck.add(new Card(randomK, randomN));

		}

		// p[i]�ʱ�ȭ

		// for (int i = 0; i < cntplayer; i++) {

		// p[i] = new Player();

		// }

		// ��Ŀ ��� ������ �ؽ��ʿ� ����

		rankTable.put("straightflush", 9000);

		rankTable.put("flush", 8000);

		rankTable.put("straight", 7000);

		rankTable.put("full house", 6000);

		rankTable.put("four card", 5000);

		rankTable.put("three card", 4000);

		rankTable.put("2 pair", 3000);

		rankTable.put("1 pair", 2000);

		rankTable.put("no rank", 1000);

	}

	public void deal(Player p) {

		boolean threeCard = false;

		boolean fourCard = false;

		boolean flush = false;

		boolean straightFlush = false;

		boolean one_pair = false;

		int pair = 0;

		int straight = 0;

		String result;

		int[] cntArr = new int[13 + 1];

		int[] cntArrk = new int[4 + 1];

		for (int i = 0; i < 5; i++) {

			cntArr[((Card) (p.card.get(i))).num]++;

		}

		for (int i = 0; i < 5; i++) {

			cntArrk[((Card) (p.card.get(i))).kind]++;

		}

		for (int i = 0; i < p.card.size(); i++) {

			System.out.print("(" + ((Card) (p.card.get(i))).kind + " " + ((Card) (p.card.get(i))).num + ")" + " ");

		}

		System.out.println();

		for (int i = 0; i < cntArr.length; i++) {

			if (cntArr[i] == 4) {

				fourCard = true;

				p.res = "four card";

			} else if (cntArr[i] == 3) {

				threeCard = true;

				p.res = "three card";

			} else if (cntArr[i] == 2) {

				one_pair = true;

				p.res = "1 pair";

			} else if (i < cntArr.length - 1 && (cntArr[i] == 1 && cntArr[i + 1] == 1)) {

				straight++;

				p.res = "straight";

			}

			else {

				p.res = "no rank";

			}

		}

		for (int j = 0; j < cntArrk.length; j++) {

			if (cntArrk[j] == 5) {

				flush = true;

				p.res = "flush";

			}

		}

		for (int i = 0; i < cntArr.length; i++) {

			for (int j = 0; j < cntArrk.length; j++) {

				if (i < cntArr.length - 1 && (cntArr[i] == 1 && cntArr[i + 1] == 1) && cntArrk[j] == 5) {

					straightFlush = true;

					p.res = "straightflush";

				}

			}

		}

	}

	public void checkRank(Player[] players) {

		Set set = rankTable.entrySet();

		Iterator it = set.iterator();

		for (int i = 0; i < players.length; i++) {

			while (it.hasNext()) {

				Map.Entry e = (Map.Entry) it.next();

				if (e.getKey().equals(players[i].res))

					(players[i].rank) = (int) e.getValue();

			}

		}

		Arrays.sort(players);

		for (int i = 0; i < players.length; i++) {

			System.out.println(players[i].rank);

		}

	}

}

public class PokerGamesOptimize {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		System.out.println("�÷��̾ ����Դϱ�?");

		int player;

		Scanner sc = new Scanner(System.in);

		player = sc.nextInt();

		ArrayList card = new ArrayList();

		Dealer deal = new Dealer(card, player); // ī�� �ѹ��� �÷����� ��� ��

		Player players[] = new Player[player];
		
		for (int i = 0; i < player; i++) {

			players[i] = new Player(deal); // �÷��̾�� 5���� ī�带 ���� ��

			deal.deal(players[i]);

		}

		deal.checkRank(players);

	}
}

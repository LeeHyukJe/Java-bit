import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class RectTest {

	/**
	 * Time complexity: Space complexity:
	 */
	public static void main(String[] args) throws Exception {
		// try (BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in))) {
		// int T = Integer.parseInt(br.readLine().trim());
		// for (int i = 0; i < T; ++i) {
		// int count = 0;
		// String word = br.readLine().trim();
		// for (int j = 0; j < word.length(); j++) {
		// for (char k = 'A'; k <= 'Z'; k++) {
		// if (word.charAt(j) == k) {
		// count++;
		// if (j + 1 < word.length()) {
		// if (word.charAt(j) != word.charAt(j + 1)) {
		// System.out.print(count + "" + word.charAt(j));
		// count = 0;
		// }
		// }
		// }
		// }
		// }
		// System.out.print(count + "" + word.charAt(word.length() - 1));
		// System.out.println("");
		// }
		// }

		/*
		 *1
		 *11
		 *12
		 *1121
		 *122111
		 *112213
		 *12221131 
		 */
				ArrayList<String> antArray = new ArrayList<>();
		antArray.add(1+"");
		for (int i = 0; i < antArray.size(); i++) {
			
			int intCount[] = new int[11];
			for (int j = 0; j < antArray.get(i).length(); j++) {
				int count=0;
				for (int k = 1; k <= 9; k++) {
					// 리스트 안에 있는 숫자와 같은 지를 비교
					if (antArray.get(i).charAt(j) - '0' == k) {
						count++;
					}
				}
			}
		}

	}

	public String solution(int n) {
		ArrayList<Integer> antArray = new ArrayList<>();
		ArrayList<Integer> intCount = new ArrayList<>();
		antArray.add(1);
		for (int i = 0; i < antArray.size(); i++) {
			int count = 0;
			for (int j = 0; j < 10; j++) {
				// 리스트 안에 있는 숫자와 같은 지를 비교
				if (antArray.get(i) == j) {
					count++; // 리스트에 있는 수와 비슷하면 카운트 1증가
					intCount.add(j + 1);
					String temp = j + 1 + "" + intCount.get(j);
					antArray.add(Integer.parseInt(temp));
				}
			}

		}
		return antArray.get(n) + "";
	}
}

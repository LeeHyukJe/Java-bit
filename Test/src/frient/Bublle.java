package frient;

import java.util.Arrays;

public class Bublle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 이름1,92,80,82 이름2,100,90,70 이름3,80,77,100 이름4,100,100,58 이름5,100,63,100
		 * 이름6,89,97,91 이름7,23,69,100 이름8,87,69,59 이름9,10,100,100 이름10,20,90,100
		 */
		int kor[] = { 92, 100, 80, 100, 100, 89, 23, 87, 10, 20 };
		int eng[] = { 80, 90, 77, 100, 63, 97, 69, 69, 100, 90 };
		int math[] = { 82, 70, 100, 58, 100, 91, 100, 59, 100, 100 };
		int student_score[] = new int[10];
		for (int i = 0; i < kor.length; i++) {
			student_score[i] += kor[i] + eng[i] + math[i];
		}

		int[] order = new Bublle().order_print(student_score);

		System.out.println("성명	국어	영어	수학	총합	평균	석차");
		for (int i = 0; i < order.length; i++) {
			for (int j = 0; j < order.length; j++) {
				if (order[i] == (kor[j] + eng[j] + math[j])) {
					System.out.println("학생" + (j + 1) + "    " + kor[j] + "      " + eng[j] + "      " + math[j]
							+ "      " + (kor[j] + eng[j] + math[j]) + "      " + (kor[j] + eng[j] + math[j]) / 3 + "      " + +(i + 1));
				}
			}
		}
	}

	public int[] order_print(int curr[]) {
		int temp;

		for (int i = 1; i < curr.length; i++) {
			for (int j = 0; j < curr.length - i; j++) {
				if (curr[j] < curr[j + 1]) {
					temp = curr[j];
					curr[j] = curr[j + 1];
					curr[j + 1] = temp;
				}
			}
		}
		return curr;
	}

}

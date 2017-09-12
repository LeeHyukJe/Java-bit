package ConsoleMaker;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console2 {
	static String[] argArr; // �Ű������� ��� ���� ���ڿ� �迭
	static LinkedList q = new LinkedList(); // queue����
	static final int MAX_SIZE = 5;

	static File curDir;

	static {
		try {
			File user = new File(System.getProperty("user.dir"));
			curDir = user;
		} catch (Exception e) {
			System.out.println("������ �߻��Ͽ����ϴ�.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while (true) {
			try {
				String prompt = curDir.getCanonicalPath() + ">>";
				System.out.print(prompt);
				String input;
				input = sc.nextLine();

				save(input);

				input = input.trim(); // ���� ����
				argArr = input.split("\\s+");

				String command = argArr[0].trim();

				if ("".equals(command)) // �Է��Ѱ� �ƹ��͵� ������ �ٽ� �Է¹���
					continue;
				command = command.toLowerCase();// �ҹ��ڷ� �ٲٱ�

				if (command.equals("q"))
					System.exit(0);
				else if (command.equals("history")) {
					history();
				} else if (command.equals("dir")) {
					dir();
				} else {
					for (int i = 0; i < argArr.length; i++) {
						System.out.println(argArr[i]);
					}
				}
			} catch (Exception e) {
				System.out.println("�Է� ���� �Դϴ�.");
			}
		}
	}

	public static void save(String input) {
		if (input == null || "".equals(input))
			return;
		else {
			if (input.equals("history"))
				return;
			q.add(input);
			if (q.size() > MAX_SIZE) {
				q.removeFirst(); // ������� ũ�� ù��° ������ ����
			}
		}
	}

	public static void history() {
		int i = 0;
		System.out.println(q.toString());
	}

	public static void dir() {
		String pattern = "";
		File[] files = curDir.listFiles();
		switch (argArr.length) {
		case 1: // dir�� �Է��� ��� ���� ���丮, ���� ��θ� �����ش�.
			if (argArr[0].equals("dir")) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						System.out.println("[" + files[i].getName() + "]");
					}
				}
			}
			break;

		case 2: // dir�� ������ ���� �Է��� ���
			pattern = argArr[1];
			pattern = pattern.toUpperCase();
			pattern=pattern.replace(".","\\");
			pattern=pattern.replace("*","*");
			pattern=pattern.replace("?","{1}");
			
			Pattern p=Pattern.compile(pattern);
			for(File f:curDir.listFiles()) {
				String temp=f.getName().toUpperCase();
				Matcher m=p.matcher(temp);
				
				if(m.matches()) {
					if(f.isDirectory())
						System.out.println("["+f.getName()+"]");
					else
						System.out.println(f.getName());
				}
			}
			break;
		default:
			System.out.println("USAGE: dir [FILENAME]");
		}
	}

}

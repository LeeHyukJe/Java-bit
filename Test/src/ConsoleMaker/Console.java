package ConsoleMaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Console {
	static String[] argArr; // 매개변수를 담기 위한 문자열 배열
	static LinkedList q = new LinkedList(); // queue생성
	static final int MAX_SIZE = 5;

	static File curDir;

	static {
		try {
			File user = new File(System.getProperty("user.dir"));
			curDir = user;
		} catch (Exception e) {
			System.out.println("오류가 발생하였습니다.");
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

				input = input.trim(); // 공백 제거
				argArr = input.split("\\s+");

				String command = argArr[0].trim();

				if ("".equals(command)) // 입력한게 아무것도 없으면 다시 입력받음
					continue;
				command = command.toLowerCase();// 소문자로 바꾸기

				if (command.equals("q"))
					System.exit(0);
				else if (command.equals("history")) {
					history();
				} else if (command.equals("dir")) {
					dir();
				} else if (command.equals("type")) {
					type();
				} else if (command.equals("find")) {
					find();
				} else if (command.equals("find2")) {
					find2();
				} 
				else if(command.equals("cd")) {
					cd();
				}
				else {
					for (int i = 0; i < argArr.length; i++) {
						System.out.println(argArr[i]);
					}
				}
			} catch (Exception e) {
				System.out.println("입력 오류 입니다.");
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
				q.removeFirst(); // 사이즈보다 크면 첫번째 데이터 삭제
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
		case 1: // dir만 입력한 경우 현재 디렉토리, 파일 모두를 보여준다.
			if (argArr[0].equals("dir")) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						System.out.println("[" + files[i].getName() + "]");
					}
				}
			}
			break;

		case 2: // dir과 패턴을 같이 입력한 경우
			pattern = argArr[1];
			pattern = pattern.toUpperCase();
			pattern = pattern.replace("*", "");
			pattern = pattern.replace(".", "");
			for (int i = 0; i < files.length; i++) {
				String temp = files[i].getName();
				temp = temp.toUpperCase();
				if (temp.contains(pattern)) {
					if (files[i].isDirectory()) {
						System.out.println("[" + files[i].getName() + "]");
					}
					System.out.println(files[i].getName());
				}
			}
			break;
		default:
			System.out.println("USAGE: dir [FILENAME]");
		}
	}

	public static void type() throws IOException {
		if (argArr.length != 2) {
			System.out.println("Usage:type FILENAME");
			return;
		}

		String fileName = argArr[1];
		File temp = new File(fileName);

		if (temp.exists()) { // 파일이 존재하면
			FileReader fr = new FileReader(temp);
			BufferedReader br = new BufferedReader(fr);
			String read = "";
			while ((read = br.readLine()) != null) {
				System.out.println(read);
			}
			br.close();
		} else {
			System.out.println("존재 하지 않는 파일 입니다.");
		}
	}

	public static void find() throws IOException {
		if (argArr.length != 3) {
			System.out.println("Usage : find KEYWORD FILE_NAME");
			return;
		}

		String keyword = argArr[1];
		String fileName = argArr[2];

		File temp = new File(fileName);

		if (temp.exists()) {
			FileReader fr = new FileReader(temp);
			BufferedReader br = new BufferedReader(fr);

			String reader = "";
			for (int i = 1; (reader = br.readLine()) != null; i++) {
				if (reader.contains(keyword)) {
					System.out.println(i + ": " + reader);
				}
			}
		} else {
			System.out.println("존재 하지 않는 파일 입니다.");
		}
	}

	public static void find2() throws IOException {
		if (argArr.length != 3) {
			System.out.println("Usage : find KEYWORD FILE_NAME");
			return;
		}

		String keyword = argArr[1];
		String pattern = argArr[2];
		pattern = pattern.replace(".", "\\");
		pattern = pattern.replace("*", ".*");
		pattern =pattern.replace("?",".{1}");
		
		Pattern p=Pattern.compile(pattern);
		
		for(File f:curDir.listFiles()) {
			String temp=f.getName().toUpperCase();
			Matcher m=p.matcher(temp);
			
			if(m.matches()) {
				if(f.isDirectory()) {
					continue;
				}
				FileReader fr=new FileReader(f);
				BufferedReader br=new BufferedReader(fr);
				
				String line="";
				System.out.println("------------+"+f.getName());
				for(int i=1;(line=br.readLine())!=null;i++) {
					if(line.indexOf(keyword)!=-1) {
						System.out.println(i+":"+line);
					}
				}
			}
		}
	}
	public static void cd() {
		if(argArr.length==1) {
			System.out.println(curDir);
			return;
		}
		else if(argArr.length>2) {
			System.out.println("Usage : cd directory");
			return;
		}
		
		String subDir=argArr[1];
		File child;
		if(subDir.equals("..")) {
			child=curDir;
			curDir=curDir.getParentFile();//현재 조상디렉터리를 얻음
		}
		else if(subDir.equals(".")) {
			System.out.println(curDir.getName());
		}
		else {
			System.out.println("유효하지 않은 명령입니다.");
		}
	}
}

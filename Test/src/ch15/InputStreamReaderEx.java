package ch15;
import java.io.*;

public class InputStreamReaderEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line="";
		
		try {
			InputStreamReader ins=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ins);
			
			System.out.println("사용중인 os 문자 인코딩"+ins.getEncoding());
			
			do {
				System.out.print("문장을 입력하세요>");
				line=br.readLine();
				System.out.println("입력하신 문장 "+line);
			}while(!line.equalsIgnoreCase("q"));
			br.close();
			System.out.println("프로그램을 종료합니다.");
		}catch(IOException e) {}
	}

}

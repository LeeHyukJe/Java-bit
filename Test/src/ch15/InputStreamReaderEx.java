package ch15;
import java.io.*;

public class InputStreamReaderEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line="";
		
		try {
			InputStreamReader ins=new InputStreamReader(System.in);
			BufferedReader br=new BufferedReader(ins);
			
			System.out.println("������� os ���� ���ڵ�"+ins.getEncoding());
			
			do {
				System.out.print("������ �Է��ϼ���>");
				line=br.readLine();
				System.out.println("�Է��Ͻ� ���� "+line);
			}while(!line.equalsIgnoreCase("q"));
			br.close();
			System.out.println("���α׷��� �����մϴ�.");
		}catch(IOException e) {}
	}

}

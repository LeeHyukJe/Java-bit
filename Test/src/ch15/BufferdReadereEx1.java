package ch15;
import java.io.*;

public class BufferdReadereEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader fr=new FileReader("ch15//BufferdReadereEx1.java");
			BufferedReader br=new BufferedReader(fr);
			
			String line="";
			for(int i=0;(line=br.readLine())!=null;i++) {
				if(line.indexOf(";")!=1) {
					System.out.println(i+":"+line);
				}
			}
			br.close();
		}catch(IOException e) {}
	}

}

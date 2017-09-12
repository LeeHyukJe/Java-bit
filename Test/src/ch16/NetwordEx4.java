package ch16;
import java.net.*;
import java.io.*;

public class NetwordEx4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url=null;
		BufferedReader input=null;
		String address="http://www.naver.com";
		String line="";
		
		try {
			url=new URL(address);
			input=new BufferedReader(new InputStreamReader(url.openStream()));
			while((line=input.readLine())!=null) {
				System.out.println(line);
			}
			input.close();
		}catch(Exception e) {}
	}	

}

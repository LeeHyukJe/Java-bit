package ch15;
import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String fileName="UserInfo.ser";
			FileOutputStream fos=new FileOutputStream(fileName);
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			
			ObjectOutputStream out=new ObjectOutputStream(bos);
			
			UserInfo u1=new UserInfo("Heyman","1234",10);
			UserInfo u2=new UserInfo("goodMan","1244",23);
			
			ArrayList<UserInfo> list=new ArrayList<>();
			list.add(u1);
			list.add(u2);
			
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			out.close();
			System.out.println("Serialization is completed");
		}catch(IOException e) {}
	}

}

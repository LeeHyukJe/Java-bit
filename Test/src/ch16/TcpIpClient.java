package ch16;
import java.net.*;
import java.io.*;
public class TcpIpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String serverIp="127.0.1";
			
			System.out.println("������ �������Դϴ�. ���� IP "+serverIp);
			Socket socket=new Socket(serverIp,7777);
			
			InputStream in=socket.getInputStream();
			DataInputStream dis=new DataInputStream(in);
			
			System.out.println("�����κ��� ���� �޼��� "+dis.readUTF());
			System.out.println("������ �����մϴ�.");
			
			dis.close();
			socket.close();
			System.out.println("������ ����Ǿ����ϴ�.");
		}catch(ConnectException e) {
			
		}catch(Exception e) {
			
		}
	}

}

package ch16;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TcpIpServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket=null;
		
		try {
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"������ �غ�Ǿ����ϴ�.");
		}catch(IOException e) {}
		
		try {
			System.out.println(getTime()+"�����û�� ��ٸ��� �ֽ��ϴ�.");
			Socket socket=serverSocket.accept();
			System.out.println(getTime()+socket.getInetAddress()+"�κ��� �����û�� �Ǿ����ϴ�.");
			
			OutputStream out=socket.getOutputStream();
			DataOutputStream dos=new DataOutputStream(out);
			
			dos.writeUTF("[notice] Test Message1 from Server");
			System.out.println(getTime()+"�����͸� �����Ͽ����ϴ�.");
			
			dos.close();
		}catch(IOException e) {}
	}
	static String getTime() {
		SimpleDateFormat f=new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}
package ch16;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TcpIpServer2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket=null;
		try {
			
			//���� ������ �����Ͽ� 7777�� ��Ʈ�� ���� ��Ų��.
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"������ �غ�Ǿ����ϴ�.");
		}catch(IOException e) {}
		
		while(true) {
			
			//serverSocket
			System.out.println(getTime()+"�����û�� ��ٸ��ϴ�.");
			try {
				serverSocket.setSoTimeout(1000); //��û������ 5�ʷ� ����
				Socket socket =serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+" ���κ��� �����û�� ���Խ��ϴ�.");
				System.out.println("getPort(): "+socket.getPort());
				System.out.println("getLocalPort() "+socket.getLocalPort());
				
				//������ ��½�Ʈ���� ��´�.
				OutputStream out=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);
				
				//���� ���Ͽ� �����͸� ������.
				dos.writeUTF("notice from server");
				System.out.println(getTime()+"�����͸� �����Ͽ����ϴ�.");
				dos.close();
				socket.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static String getTime() {
		SimpleDateFormat f=new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}

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
			
			//서버 소켓을 생성하여 7777번 포트와 결합 시킨다.
			serverSocket=new ServerSocket(7777);
			System.out.println(getTime()+"서버가 준비되었습니다.");
		}catch(IOException e) {}
		
		while(true) {
			
			//serverSocket
			System.out.println(getTime()+"연결요청을 기다립니다.");
			try {
				serverSocket.setSoTimeout(1000); //요청대기시잔 5초로 설정
				Socket socket =serverSocket.accept();
				System.out.println(getTime()+socket.getInetAddress()+" 으로부터 연결요청이 들어왔습니다.");
				System.out.println("getPort(): "+socket.getPort());
				System.out.println("getLocalPort() "+socket.getLocalPort());
				
				//소켓의 출력스트림을 얻는다.
				OutputStream out=socket.getOutputStream();
				DataOutputStream dos=new DataOutputStream(out);
				
				//원격 소켓에 데이터를 보낸다.
				dos.writeUTF("notice from server");
				System.out.println(getTime()+"데이터를 전송하였습니다.");
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

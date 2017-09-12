package ch16;
import java.io.*;
import java.net.*;
import java.util.*;
public class TcpIpMultichatClient2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length!=1) {
			System.out.println("Usage: java TcpIpMutichatClient ��ȭ��");
			System.exit(0);
		}
		
		try {
			String serverIp="192.168.104.26";
			
			//��Ĺ�� �����Ͽ� �����Ѵ�.
			Socket socket=new Socket(serverIp,7777);
			System.out.println("������ ����Ǿ����ϴ�.");
			Thread sender=new Thread(new ClientsSender(socket,args[0]));
			Thread receiver=new Thread(new ClientsReceiver(socket));
			sender.start();
			receiver.start();
			
		}catch(ConnectException e) {}
		catch(Exception e) {}
	}
	
	static class ClientsSender extends Thread{
		Socket socket;
		DataOutputStream dos;
		String name;
		
		ClientsSender(Socket socket, String name){
			this.socket=socket;
			try {
				dos=new DataOutputStream(socket.getOutputStream());
				this.name=name;
			}catch(Exception e) {}
		}
		
		public void run() {
			Scanner sc=new Scanner(System.in);
			try {
				if(dos!=null) {
					dos.writeUTF(name);
				}
				while(dos!=null) {
					dos.writeUTF("["+name+"]"+sc.nextLine());
				}
			}catch(IOException e) {}
		}
	}
	
	static class ClientsReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		
		ClientsReceiver(Socket socket){
			this.socket=socket;
			try {
				dis=new DataInputStream(socket.getInputStream());
			}catch(IOException e) {}
		}
		
		public void run() {
			while(dis!=null) {
				try {
					System.out.println(dis.readUTF());
				}catch(IOException e) {}
			}
		}
	}

}

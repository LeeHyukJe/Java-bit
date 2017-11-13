package tcp_ip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	String serverIP;
	Socket socket;
	String client_name;
	
	public Client() {
		try {
			serverIP="127.0.0.1";
			System.out.println("플레이어의 아이디를 입력하세요!");
			Scanner sc=new Scanner(System.in);
			client_name=sc.nextLine();
			socket=new Socket(serverIP,30013);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client=new Client();
		Thread sender=new Thread(new ClientSender(client.socket,client.client_name));
		Thread receiver=new Thread(new ClientReceiver(client.socket));
		sender.start();
		receiver.start();
	}

}

class ClientSender implements Runnable{
	Socket socket;
	DataOutputStream dos;
	String client_name;
	
	public ClientSender(Socket socket,String client_name) {
		this.socket=socket;
		
		try {
			this.client_name=client_name;
			dos=new DataOutputStream(socket.getOutputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		try {
			if(dos!=null) {
				dos.writeUTF(client_name);
			}
			
			try {
				while(dos!=null) {
					dos.writeUTF("["+client_name+"]"+sc.nextLine());
				}
			}catch(Exception  e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

class ClientReceiver implements Runnable{
	Socket socket;
	DataInputStream dis;
	
	public ClientReceiver(Socket socket) {
		this.socket=socket;
		try{
			dis=new DataInputStream(socket.getInputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(dis!=null) {
				System.out.println(dis.readUTF());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

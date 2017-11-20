package tcp_ip;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
	HashMap<String,DataOutputStream> clients;
	
	public Server(){
		clients=new HashMap<String,DataOutputStream>();
		Collections.synchronizedMap(clients);
	}
	
	public void ServerStart() {
		ServerSocket serverSocket=null;
		Socket socket=null;
		
		try {
			serverSocket=new ServerSocket(30013);//���� ���� ����
			System.out.println("Ŭ���̾�Ʈ ���� ��û�� ��ٸ��� �ֽ��ϴ�.");
			while(true) {
				socket=serverSocket.accept(); //Ŭ���̾�Ʈ ���� ���� ȹ��
				System.out.println(socket.getInetAddress()+"�� ����.");
				ServerThread serverThread=new ServerThread(socket);
				serverThread.start();
			}
		}catch(Exception e) {
			System.out.println("��û�� ������ �߻��Ͽ����ϴ�.");
		}
	}
	
	//Ŭ���̾�Ʈ���� ��� ����
	public void sendToAll(String msg) {
		Iterator<String> it=clients.keySet().iterator();
		System.out.println(msg); //���� �α� ���
		while(it.hasNext()) {
			DataOutputStream dos=clients.get(it.next());
			try {
				dos.writeUTF(msg);
			}catch(Exception e) {
				System.out.println(e);
				System.out.println(dos.getClass()+"��(��) �������� �ʾ� ���� ����");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server().ServerStart();
	}
}

class ServerThread extends Thread{
	Server server=new Server();
	Socket socket;
	DataInputStream dis; //client receive
	DataOutputStream dos; //client send
	String name;
	
	public ServerThread(Socket socket) {
		this.socket=socket;
		
		try {
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {		
		try {
			if(dis!=null) {
				name=dis.readUTF();
				server.clients.put(name, dos); //client�� �̸��� Ŭ���̾�Ʈ ���� ����
				server.sendToAll("#"+name+"���� �����Ͽ����ϴ�.");
				System.out.println("���� ������ ���� "+server.clients.size()+"�Դϴ�.");
			}
			
			while(dis!=null) {
				String result=dis.readUTF(); //client receive
				server.sendToAll(result); //��� Ŭ���̾�Ʈ���� ����
			}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println(dis.getClass()+"��(��) ���� ���� �ʾ� ���� ����");
		}
		finally {
			server.sendToAll("#"+name+"���� �����̽��ϴ�.");
			server.clients.remove(name);
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+"���� ������ �����Ͽ����ϴ�.");
			System.out.println("���� ������ ����"+server.clients.size()+"�� �Դϴ�.");
		}
	}
}

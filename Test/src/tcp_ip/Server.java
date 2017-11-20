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
			serverSocket=new ServerSocket(30013);//서버 소켓 생성
			System.out.println("클라이언트 연결 요청을 기다리고 있습니다.");
			while(true) {
				socket=serverSocket.accept(); //클라이언트 연결 소켓 획득
				System.out.println(socket.getInetAddress()+"가 접속.");
				ServerThread serverThread=new ServerThread(socket);
				serverThread.start();
			}
		}catch(Exception e) {
			System.out.println("요청에 문제가 발생하였습니다.");
		}
	}
	
	//클라이언트에게 모두 전송
	public void sendToAll(String msg) {
		Iterator<String> it=clients.keySet().iterator();
		System.out.println(msg); //서버 로그 찍기
		while(it.hasNext()) {
			DataOutputStream dos=clients.get(it.next());
			try {
				dos.writeUTF(msg);
			}catch(Exception e) {
				System.out.println(e);
				System.out.println(dos.getClass()+"이(가) 존재하지 않아 전송 종료");
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
				server.clients.put(name, dos); //client의 이름과 클라이언트 소켓 저장
				server.sendToAll("#"+name+"님이 입장하였습니다.");
				System.out.println("현재 접속자 수는 "+server.clients.size()+"입니다.");
			}
			
			while(dis!=null) {
				String result=dis.readUTF(); //client receive
				server.sendToAll(result); //모든 클라이언트에게 전송
			}
		}catch(Exception e) {
			System.out.println(e);
			System.out.println(dis.getClass()+"이(가) 존재 하지 않아 전송 종료");
		}
		finally {
			server.sendToAll("#"+name+"님이 나가셨습니다.");
			server.clients.remove(name);
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+"에서 접속을 종료하였습니다.");
			System.out.println("현재 접속자 수는"+server.clients.size()+"명 입니다.");
		}
	}
}

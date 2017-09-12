package MakingGames;

import java.net.*;
import java.io.*;
import java.util.*;
public class SonagiServer {
	HashMap clients;
	
	
	ServerSocket serverSocket;
	Socket socket;
	
	SonagiServer(){
		clients=new HashMap();
		Collections.synchronizedMap(clients);
	}
	
	public void start(){
		try {
			serverSocket=new ServerSocket(7777);
			System.out.println("연결을 기다리고 있습니다.....");
			
			while(true) {
				socket=serverSocket.accept();
				System.out.println("["+socket.getInetAddress()+"]"+"님께서 접속하였습니다.");
				ServerReceiver serverreceiver=new ServerReceiver(socket);
				serverreceiver.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		public  void sendToAll(String msg) throws IOException {
			Iterator it=clients.keySet().iterator();
			while(it.hasNext()) {
				DataOutputStream dos=(DataOutputStream)clients.get(it.next());
				dos.writeUTF(msg);
			}
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SonagiServer().start();
	}
	
	public class ServerReceiver extends Thread{
		DataInputStream dis;
		DataOutputStream dos;
		ServerReceiver(Socket socket){
			try {
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run() {
			String name="";
			try {
				name=dis.readUTF();
				clients.put(name,dos);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				clients.remove(name);
			}
		}
	}

}

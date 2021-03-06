package ch16;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String serverIP = "192.168.104.26";
			Socket socket = new Socket(serverIP, 7777);
			Thread sender = new Thread(new ClientSender2(socket,args[0]));
			Thread receive = new Thread(new ClientReceive2(socket));

			sender.start();
			receive.start();
		} catch (Exception e) {
			System.out.println("연결 오류 입니다.");
		}
	}

}

class ClientSender2 implements Runnable {
	Socket socket;
	DataOutputStream dos;
	String name;

	ClientSender2(Socket socket, String name) {
		this.socket = socket;
		try {
			this.name = name;
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {}
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		
		try {
			if(dos!=null) {
				dos.writeUTF(name);
			}
			
			while (dos != null) {
				dos.writeUTF("[" + name + "]" + sc.nextLine());
			}
		} catch (Exception e) {}
	}
}

class ClientReceive2 implements Runnable {
	Socket socket;
	DataInputStream dis;
	
	
	ClientReceive2(Socket socket){
		this.socket=socket;
		try {
			dis=new DataInputStream(socket.getInputStream());
		}catch(Exception e) {}
	}

	public void run() {
		try {
			while(dis!=null) {
				System.out.println(dis.readUTF());
			}
		}catch(Exception e) {}
	}
}

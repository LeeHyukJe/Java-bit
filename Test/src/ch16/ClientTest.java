package ch16;

import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.pool.OracleDataSource;

public class ClientTest {
	static OracleDataSource ods = null;
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static String name = "";
	static int dbrow;
	ClientTest() {
		try {
			ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ods.setUser("student");
			ods.setPassword("1234");
			conn = ods.getConnection();
			String cName = "select * from student";
			pstmt = conn.prepareStatement(cName);
			rs = pstmt.executeQuery();
			for(int i=1;rs.next();i++) {
				dbrow=i;
			}
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClientTest();
		try {
			String serverIP = "192.168.104.26";
			Socket socket = new Socket(serverIP, 7777);
			Thread sender = new Thread(new ClientSender(socket,args[0]));
			Thread receive = new Thread(new ClientReceive(socket));

			sender.start();
			receive.start();
		} catch (Exception e) {
		}
	}

	static class ClientSender implements Runnable {
		Socket socket;
		DataOutputStream dos;
		String name;

		ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
				this.name = name;
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
			}
		}

		public void run() {
			Scanner sc = new Scanner(System.in);

			try {
				if (dos != null) {
					dos.writeUTF(name);
				}

				while (dos != null) {
					dos.writeUTF("[" + name + "]" + sc.nextLine());
				}
			} catch (Exception e) {
			}
		}
	}

	static class ClientReceive implements Runnable {
		Socket socket;
		DataInputStream dis;
		int next=1;
		ClientReceive(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
			}
		}

		public void run() {
			try {
				while (dis != null) {
					System.out.println(dis.readUTF());
				}

			} catch (Exception e) {
			}
		}
	}
	
	
}
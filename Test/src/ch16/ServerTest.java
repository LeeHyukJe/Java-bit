package ch16;
import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;
//import oracle.jdbc.pool.OracleDataSource;

public class ServerTest {
	HashMap clients;
	
	ServerTest(){
		clients=new HashMap();
		Collections.synchronizedMap(clients);
		
	}
	public void start() {
		ServerSocket serverSocket=null;
		Socket socket=null;
		
		try {
			serverSocket=new ServerSocket(7777);
			System.out.println("Ŭ���̾�Ʈ ��û�� ��ٸ��� �ֽ��ϴ�.");
			while(true) {
				socket=serverSocket.accept();
				System.out.println(socket.getInetAddress()+"���� �����Ͽ����ϴ�.");
				ServerThread server=new ServerThread(socket);
				server.start();
			}
		}catch(Exception e) {
			System.out.println("��û�� ������ �߻��Ͽ����ϴ�.");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerTest().start();
	}
	
	void sendToAll(String msg) {
		Iterator it=clients.keySet().iterator();
		
		while(it.hasNext()) {
			DataOutputStream dos=(DataOutputStream)clients.get(it.next());
			try {
				dos.writeUTF(msg);
			}catch(Exception e) {}
		}
	}

}

class ServerThread extends Thread{
	ServerTest serverTest=new ServerTest();
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	DBThread dbserver;
	ServerThread(Socket socket){
		this.socket=socket;
		
		try {
			dis=new DataInputStream(socket.getInputStream());
			dos=new DataOutputStream(socket.getOutputStream());
		}catch(Exception e) {}
		
	}
	
	public void run() {
		String name="";
		int next=1;
		try {
			if(dis!=null) {
				name=dis.readUTF();
				//dos.writeUTF(name+"���� �����Ͽ����ϴ�.");
				serverTest.clients.put(name, dos);
				serverTest.sendToAll("#"+name+"���� �����ϼ̽��ϴ�.");
				System.out.println("���� ������ ���� "+serverTest.clients.size()+" �Դϴ�.");
			}
			while(dis!=null) {
				String res=dis.readUTF();
				//dos.writeUTF(dis.readUTF());
				serverTest.sendToAll(res);
				//DBread(dis,next++);
				dbserver=new DBThread(res,next);
				dbserver.start();
			}
		}catch(Exception e) {}
		finally {
			serverTest.sendToAll("#"+name+"���� �����̽��ϴ�.");
			serverTest.clients.remove(name);
			System.out.println(socket.getInetAddress()+":"+socket.getPort()+"����  ������ �����Ͽ����ϴ�.");
			System.out.println("���� ������ ���� "+serverTest.clients.size()+"�Դϴ�.");
		}
	}
	
	
}

class DBThread extends Thread{
	//Socket socket;
	DataInputStream dis=null;
	//OracleDataSource ods = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2=null;
	String reader;
	ResultSet rs;
	int next;
	
	DBThread (String res,int next){
		this.reader=res;
		this.next=next;
	}
	public void run() {
		String jdbc_driver="com.mysql.jdbc.Driver";
		String jdbc_url="jdbc:mysql://127.0.0.1:3306/db";
		try {
			//ods = new OracleDataSource();
			//ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			//ods.setUser("student");
			//ods.setPassword("1234");
			//conn = ods.getConnection();
			Class.forName(jdbc_driver);
			conn=DriverManager.getConnection(jdbc_url,"root","1234");
			String getRow="select * from student";
			pstmt2=conn.prepareStatement(getRow);
			rs=pstmt2.executeQuery();
			
			for(int i=1;rs.next();i++) {
				next=i;
			}
		}catch(Exception e) {}
		
		try {
			int ok;
			String input = "insert into student (id,data) values(?,?) ";
			pstmt = conn.prepareStatement(input);
			pstmt.setInt(1, next);
			pstmt.setString(2,reader);
			ok = pstmt.executeUpdate();
			next+=1;
			if(ok==1) {
				System.out.println("����� �Ϸ�Ǿ����ϴ�.");
			}
				
		} catch (Exception e) {
			System.out.println("���� ������ �����ϴ�.");
		}
	}
}



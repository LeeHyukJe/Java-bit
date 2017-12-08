package tcp_ip;

import java.sql.*;

public class DbServer implements Runnable {
	String receiveData;
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	DbServer(String receiveData){
		this.receiveData=receiveData;
		String jdbc_driver="com.mysql.jdbc.Driver";
		String jdbc_url="jdbc:mysql://127.0.0.1:3306/chat";
		try {
			Class.forName(jdbc_driver);
			conn=DriverManager.getConnection(jdbc_url,"root","1234");
		}catch(Exception e) {}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int ok;
		String query="insert into room (roomName) values(?)";
		try {
			pstmt.setString(1, receiveData);
			pstmt=conn.prepareStatement(query);
			ok=pstmt.executeUpdate();
			if(ok==1)
				System.out.println("등록 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

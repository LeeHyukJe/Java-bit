package tcp_ip;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	Connection conn;
	String tableName="";
	String jdbc_driver;
	String jdbc_url;
	
	Dao(){
		this(null,"");
	}
	
	Dao(String tableName){
		this(null,tableName);
	}
	
	Dao(Connection conn, String tableName){
		this.tableName=tableName;
		
		jdbc_driver="com.mysql.jdbc.Driver";
		jdbc_url="jdbc:mysql://223.194.156.177:3306/TalkTalk";
		try {
			this.conn=DriverManager.getConnection(jdbc_url,"netp","1234");
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void rollback() {
		if(conn!=null) {
			try {
				conn.rollback();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	void close(AutoCloseable...acs) {
		for(AutoCloseable ac: acs) {
			if(ac!=null) {
				try {
					ac.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	Connection setConnect() {
		return conn;
	}
}

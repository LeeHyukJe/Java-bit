package ch16;
import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Scanner;
import oracle.jdbc.pool.OracleDataSource;
public class testDB {
	OracleDataSource ods;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String carr[]= {};
	testDB(){
		try {
			int lengh;
			ods=new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ods.setUser("student");
			ods.setPassword("1234");
			conn=ods.getConnection();
			String getCarrier="select is_male from test";
			pstmt=conn.prepareStatement(getCarrier);
			rs=pstmt.executeQuery();
			carr= new String[6];
			for(int i=0;rs.next();i++) {
				carr[i]=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("문제가 발생하였습니다->"+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String is_male[]=new testDB().carr;
		for(int i=0;i<is_male.length;i++) {
			if(is_male[i].equals("1")) {
				System.out.println("남자");
			}
			else
				System.out.println("여자");
		}
	}

}

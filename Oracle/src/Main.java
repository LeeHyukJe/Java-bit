import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import oracle.jdbc.pool.OracleDataSource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OracleDataSource ods=null;
		Connection con=null;
		//Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd;
		try {
			ods=new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ods.setUser("student");
			ods.setPassword("1234");
			con=ods.getConnection();
			String q="select * from s_emp";
			pstmt=con.prepareStatement(q);
			//pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			rsmd=rs.getMetaData();
			System.out.println("이름");
			while(rs.next()) {
				String s_name=rs.getString(2);
				String salary=rs.getString(3);
				System.out.println(s_name);
				System.out.println(salary);
			}
			System.out.print("행의 갯수는 "+rsmd.getColumnCount());
			System.out.println("2번째 행의 이름 "+rsmd.getColumnName(2)+"3번째 행의 이름 "+rsmd.getColumnName(3));
			System.out.println("2번째 필드의 타입은  "+rsmd.getColumnTypeName(2));
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			try {
				if(con!=null)
					con.close();
				if(pstmt!=null)
					
					pstmt.close();
				if(rs!=null)
					rs.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}

}

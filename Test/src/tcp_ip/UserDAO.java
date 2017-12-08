package tcp_ip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Dao {
	String tableName;
	PreparedStatement pstmt;
	ResultSet rs;
	
	UserDAO(){
		this("");
	}
	
	UserDAO(String tableName){
		this.tableName=tableName;
	}
	
	UserDAO(Connection conn, String tableName){
		super(conn,tableName);
	}
	
	private static UserDAO userDao=new UserDAO();
	
	public static UserDAO getInstance() {
		return userDao;
	}
	
	int insertUser(User user) {
		String sql="insert into user values(?,?,?) ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			rollback();
			return -1;
		}finally {
			close(pstmt);
		}
	}
	
	List<User> selectAllUser(){
		String sql="select * from user";
		ArrayList<User> list=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
			rollback();
		}finally {
			close(pstmt,rs);
		}
		return list;
	}
}

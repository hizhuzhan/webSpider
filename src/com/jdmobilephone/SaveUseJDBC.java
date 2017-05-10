package com.jdmobilephone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 测试连接数据库
 */
public class SaveUseJDBC {
	static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String url = "jdbc:sqlserver://localhost:1433;DatabaseNmae=Test";
	static final String userName = "sa";
	static final String userPwd = "123123";
	
	/*
	 * 修改数据库，增删改
	 */
	public void changeInformation(String sql){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, userPwd);
			System.out.println("成功连接数据库...");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("数据库修改完成...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("驱动加载失败...");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		try{
			if(conn != null){
				conn.close();
			}
			if(stmt != null){
				stmt.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("内存释放失败...");
		}		
	}	
}

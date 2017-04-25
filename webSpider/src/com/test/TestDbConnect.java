package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TestDbConnect {
	static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String url = "jdbc:sqlserver://localhost:1433;DatabaseNmae=Test";
	static final String userName = "sa";
	static final String userPwd = "123123";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, userPwd);
			System.out.println("成功连接数据库...");				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(conn != null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("内存释放失败...");
		}
	}

}

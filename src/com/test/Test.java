package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 测试连接数据库
 */
public class Test {
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
	
	/*
	 * 查询数据库
	 */
	public void getInformation(String sql){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, userPwd);
			System.out.println("成功连接数据库...");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					System.out.println("Id：" + id + "\t" + "Name：" + name + "\t" + "Age：" + age + "\\d");
				}				
			}else{
				System.out.println("未搜索到...");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(conn != null){
				conn.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("内存释放失败...");
		}
		
	}
	
	public static void main(String[] args){
		Test test = new Test();
//			String sql = "INSERT INTO JDMobilePhones.dbo.MobilePhones(Id, Introduce, Url)VALUES(1, '苹果 Apple iPhone 6  移动联通电信4G手机 金色 32GB', '//item.jd.com/11410791227.html')";
//			test.changeInformation(sql);
//			System.out.println("数据已更新");


		
//		String sql2 = "SELECT id, name, age " +
//				"FROM Test.dbo.Student " +
//				"WHERE id BETWEEN 3 AND 20";
//		
		String sql3 = "DELETE FROM JDMobilePhones.dbo.MobilePhones where Id = 1";
		test.changeInformation(sql3);
		
		
		
//		test.changeInformation(sql3);
		
	}
}

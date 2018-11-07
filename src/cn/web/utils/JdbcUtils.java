package cn.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//用来连接数据库的工具类
public class JdbcUtils {

	
	//有些时候有些代码只执行一次，这种代码可以存放在静态块中
	//静态块，在当前JdbcUtils。class文件加载到JVM中执行，且执行一次
	static {
		System.out.println("static......");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main() {
		Connection con = JdbcUtils.getConnection();
	}
}

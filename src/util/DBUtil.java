package util;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static String driverClass;
	private static String url;
	private static String user;
	private static String pwd;
	
	//static block静态块
	static{
		//产生properties类的实例
		Properties prop = new Properties();
		//转换成文件流
		InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("util/config.properties");
		
		try {
			prop.load(is);
			driverClass = prop.getProperty("driverClass").trim();
			url = prop.getProperty("url").trim();
			user = prop.getProperty("user").trim();
			pwd = prop.getProperty("pwd").trim();
			Class.forName(driverClass);
			
			//当前类的位置
			System.out.println(DBUtil.class.getResource(""));
			//当前类的根目录的位置
			System.out.println(DBUtil.class.getClassLoader().getResource(""));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static Connection getConnection(){
		// 数据库的连接
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user,pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}
	
}
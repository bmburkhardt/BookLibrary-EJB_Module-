package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection createConnection() {
		Connection conn = null;
		
		try {
			DriverManager.setLoginTimeout(10);
			conn = DriverManager.getConnection("jdbc:mysql://easel2.fulgentcorp.com:3306/wun846?useSSL=false",
					"wun846", "tacocat123");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return conn;
	}
}
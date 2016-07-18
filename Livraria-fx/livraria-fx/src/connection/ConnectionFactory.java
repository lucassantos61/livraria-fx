package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try{
		String url = "jdbc:mysql://localhost/livraria";
		return DriverManager.getConnection(url,"root","123");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public static void main (String [] args) throws SQLException{
		Connection conn = new ConnectionFactory().getConnection();
		System.out.println("Conexão aberta agora");
		conn.close();
	}
}

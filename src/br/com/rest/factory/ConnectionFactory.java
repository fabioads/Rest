package br.com.rest.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * 
 * Class responsible for creating and close connection
 *
 * @author Fábio Henrique Pires <fabioh.ads@gmail.com>
 * @since 18/06/2015 
 * 
 */
public class ConnectionFactory {

	// Path do Data Base.
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/webservice";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123";
	

	/**
	 * 
	 * Method responsible for create connection
	 *
	 */
	public Connection createConnection(){
		
		Connection connection = null;
		
		try {
			
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (Exception e) {
			System.out.println("Error in connection create: " + URL);
			e.printStackTrace();
		}
		return connection;
	}
	
	
	public void closeConnection(Connection connection, PreparedStatement pstmt, ResultSet rs){
		
		try {
			
			if(connection != null){
				connection.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(rs != null){
				rs.close();
			}
					
		} catch (Exception e) {
			System.out.println("Error in connection closing: " + URL);
		}
	}
}


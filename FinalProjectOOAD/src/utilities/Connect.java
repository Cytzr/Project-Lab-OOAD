package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "ooad";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	private Connection con;
	private static Connect database;
	
	public static Connect getInstance() {
		if(database == null){
			database = new Connect();
		}
		return database;
	}
	
	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			System.out.println("db connected");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
	    return con;
	}
	
//	public List<Orang> selectAllOrang(){
//		List<Orang> orangList = new ArrayList<>();
//		String query = "SELECT * FROM orang";
//		try (Statement st = con.createStatement();ResultSet rs = st.executeQuery(query)){
//			while(rs.next()) {
//				orangList.add(new Orang(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("phone")));
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return orangList;
//	}
}

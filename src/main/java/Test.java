import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	private static String url = "jdbc:mysql://localhost:3306/login";
	private static String username = "root";
	private static String password = "nguyenvandung";
	public static void main(String[] args) {
		ResultSet resultSet = getJDBC();
		try {
			while ( resultSet.next()) {
				String usernameString = resultSet.getString("username");
				String passwordString = resultSet.getString("password");
				System.out.println(usernameString + " " + passwordString);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static ResultSet getJDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement preparedStatement = 
					con.prepareStatement("select * from login");
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

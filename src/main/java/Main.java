import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	private static String connectionUrl = "jdbc:mysql://localhost:3306/jdbc-demo?serverTimezone=UTC";
	private static String dbLogin = "root";
	private static String dbPassword = "toor";

	public Main() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		create("Test1", "Test1");
		create("Test2", "Test2");
		readAll();
		update(1000, "Andrew", "Gromov");
		delete(1001);
		readAll();
	}

	private static void create(String firstName, String lastName) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManager.getConnection(connectionUrl, dbLogin, dbPassword);
			statement = connection.prepareStatement("INSERT INTO user (first_name, last_name) VALUES (?,?)");

			statement.setString(1, firstName);
			statement.setString(2, lastName);

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readAll() {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(connectionUrl, dbLogin, dbPassword);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM user");

			while (rs.next())
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void update(int id, String firstName, String lastName) {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(connectionUrl, dbLogin, dbPassword);
			statement = connection.createStatement();

			statement.executeUpdate("UPDATE user SET first_name = '" + firstName + "', last_name = '" + lastName
					+ "' WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void delete(int id) {
		Connection connection = null;
		Statement statement = null;

		try {
			connection = DriverManager.getConnection(connectionUrl, dbLogin, dbPassword);
			statement = connection.createStatement();

			statement.executeUpdate("DELETE FROM user WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

}

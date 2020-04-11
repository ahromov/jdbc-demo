package ua.lviv.lgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemoApplication {

	private static String connectionUrl = "jdbc:mysql://localhost:3306/jdbc-demo?serverTimezone=UTC";
	private static String dbLogin = "root";
	private static String dbPassword = "toor";

	private static Connection connection = null;

	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(connectionUrl, dbLogin, dbPassword);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		create(1001, "Created", "Created");
		readById(1001);
		update(1001, "Updated", "Updated");
		readById(1001);
		delete(1001);
		readAll();

		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void create(int id, String firstName, String lastName) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("INSERT INTO user (id, first_name, last_name) VALUES (?,?,?)");

			statement.setInt(1, id);
			statement.setString(2, firstName);
			statement.setString(3, lastName);

			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readById(int id) {
		Statement statement = null;

		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, first_name, last_name FROM user");

			while (rs.next())
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void update(int id, String firstName, String lastName) {
		Statement statement = null;

		try {
			statement = connection.createStatement();

			statement.executeUpdate("UPDATE user SET first_name = '" + firstName + "', last_name = '" + lastName
					+ "' WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void delete(int id) {
		Statement statement = null;

		try {
			statement = connection.createStatement();

			statement.executeUpdate("DELETE FROM user WHERE id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	private static void readAll() {
		Statement statement = null;

		try {
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
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

}

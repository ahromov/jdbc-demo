import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/i_shop?serverTimezone=UTC", "root", "root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("SELECT * FROM user");
	while(rs.next())
	    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
    }

}

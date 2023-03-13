package jdbcbasic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AccountDAO {
public static void main(String[] args)  {
	Connection connetion=null;
	Statement statement=null;
	ResultSet rs=null;
	try {
	connetion=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Hitheshraj@17");
	statement=connetion.createStatement();
	rs=statement.executeQuery("Select * from account");
	while(rs.next()) {
		int accn=rs.getInt(1);
		String lastname=rs.getString(2);
		String firstname=rs.getString(3);
		int bal=rs.getInt(4);
		System.out.println(accn+"|"+lastname+"|"+firstname+"|"+bal);
	}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	finally {
		try {
			rs.close();
			statement.close();
			connetion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
}
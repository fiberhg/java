package gitTest;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
public class SQLinjection {
	public static void main(HttpServletRequest request){
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
		try {
			SQL(id,pw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	private static void SQL(String id, String pwd) throws ClassNotFoundException, SQLException {
        
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select name, country, password from Users where email = ? and password = ?";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);
             
            //set the parameter
            ps.setString(1, id);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
 
            while (rs.next()) {
                System.out.println("Name=" + rs.getString("name") + ",country="
                        + rs.getString("country") + ",password="
                        + rs.getString("password"));
            }
        } finally {
            if (rs != null)
                rs.close();
            ps.close();
            con.close();
        }
 
    }
}

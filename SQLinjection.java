package gitTest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Statement stmt = null;
        ResultSet rs = null;
        try{
        con = DBConnection.getConnection();
        stmt = con.createStatement();
        String query = "select name, country, password from Users where email = '"+id+"' and password='"+pwd+"'";
        System.out.println(query);
        rs = stmt.executeQuery(query);
         
        while(rs.next()){
            System.out.println("Name="+rs.getString("name")+",country="+rs.getString("country")+",password="+rs.getString("password"));
        }
        }finally{
            if(rs != null) rs.close();
            stmt.close();
            con.close();
        }
         
    }
}

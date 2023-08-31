package Connection;
import java.sql.*;

public class SelectQuery {
	public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;        
        ResultSet rs = null;  
        
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/factory_crucialpoint?useSSL=false";
        String user = "root";
        String passwd = "20206319";
        String sql = "select * from machine";
        
        try{
            Class.forName(className);
 
            conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Successfully Connected!");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                String id = rs.getString("id");
                String price = rs.getString("price");
                String machine_life= rs.getString("machine_life");

                System.out.println(id + " " + price + " " + machine_life);
            }
        }
 
        catch(ClassNotFoundException e){
        	System.out.println("[JDBC Connector Driver ¿À·ù : " + e.getMessage() + "]");
        }
        catch(SQLException e){
        	System.out.println("[SQL Error : " + e.getMessage() + "]");
        }
        finally{
        	if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}

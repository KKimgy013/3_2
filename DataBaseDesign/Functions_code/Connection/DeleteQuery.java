package Connection;
import java.sql.*;
import java.util.Scanner;

public class DeleteQuery {
	public static void main(String[] args){
        Connection conn = null;
        PreparedStatement pstmt = null;  
        
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/factory_crucialpoint?useSSL=false";
        String user = "root";
        String passwd = "20206319";
        String sql = "delete from machine where id=?";
        
        try{
            Class.forName(className);
 
            conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Successfully Connected!");
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, 16);

            int r = pstmt.executeUpdate();
            System.out.println("����� row : " + r);
        }
 
        catch(ClassNotFoundException e){
        	System.out.println("[JDBC Connector Driver ���� : " + e.getMessage() + "]");
        }
        catch(SQLException e){
        	System.out.println("[SQL Error : " + e.getMessage() + "]");
        }
        finally{
        	if (pstmt != null) {
                try {
                    pstmt.close();
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

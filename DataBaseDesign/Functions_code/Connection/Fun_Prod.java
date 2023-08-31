package Connection;
import java.sql.*;
import java.util.*;

public class Fun_Prod {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null; //SQL���� �����ͺ��̽��� ������ ���� ��ü
		Statement stmt = null;
		ResultSet rs = null; //��� �����ϱ� ���� ��ü

	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://43.200.203.69:57751/factory"; //mysql ���� ���� ȣ��Ʈ�� port��ȣ, ���� ��Ű�� �ּ�
	        String user = "admin"; //user id
	        String passwd = "pass"; //user password
	  
			try {
				Class.forName(className); //JDBC ����̹� �ε�
	            conn = DriverManager.getConnection(url, user, passwd); //Connection ��ü ����
	            System.out.println("Successfully Connected!");

	            System.out.println("� product�� ����ðڽ��ϱ�?(id�� �亯) ");
	            int pid = sc.nextInt();
	            System.out.println("���� ����� �󸶷� �Ͻðڽ��ϱ�?(���������� �� �����ּ���) ");
	            int pcnt = sc.nextInt();
	            String sql1 = "update product_stock set stock=? where id=?";
	            
	            pstmt = conn.prepareStatement(sql1);

	            pstmt.setInt(1, pcnt);
	            pstmt.setInt(2, pid);
	            
	            int r = pstmt.executeUpdate();
	            System.out.println("����� row : " + r);
	            
	            String sql2 = "select * from product_stock";
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql2);
	            System.out.println("<product_stock table>");
	            while (rs.next()) {

	                String id = rs.getString("id");
	                String ex_date = rs.getString("expiration_date");
	                String stock= rs.getString("stock");

	                System.out.println(id + " " + ex_date + " " + stock);
	            }
	        
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

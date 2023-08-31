package Connection;

import java.sql.*;

public class Fuc_StockCheck {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null; //SQL���� �����ͺ��̽��� ������ ���� ��ü
		ResultSet rs = null; //��� �����ϱ� ���� ��ü

	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://43.200.203.69:57751/factory"; //mysql ���� ���� ȣ��Ʈ�� port��ȣ, ���� ��Ű�� �ּ�
	        String user = "admin"; //user id
	        String passwd = "pass"; //user password
	        String sql = "SELECT name, stock FROM ingredient"; //���� sql��
	  
			try {
				Class.forName(className); //JDBC ����̹� �ε�
				 
	            conn = DriverManager.getConnection(url, user, passwd); //Connection ��ü ����
	            System.out.println("Successfully Connected!");
				stmt = conn.createStatement(); // Statement��ü ����
	            rs = stmt.executeQuery(sql); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ��� �־��� 

	            while (rs.next()) {
	            	String name = rs.getString("name");
	                String stock = rs.getString("stock");
	                int st = Integer.parseInt(stock);
	                if(st<1) System.out.println(name + "�� ��� �����ϴ�. ��� Ȯ�� �� �ֹ��ٶ��ϴ�.");
	                else System.out.println(name + "�� ��� �����մϴ�.");
	            }
	            
	        }

	        catch(ClassNotFoundException e){
	        	System.out.println("[JDBC Connector Driver ���� : " + e.getMessage() + "]");
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

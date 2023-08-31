package Connection;
import java.sql.*;

public class Fun_ProductMade {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; //SQL���� �����ͺ��̽��� ������ ���� ��ü
		ResultSet rs = null; //��� �����ϱ� ���� ��ü

	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://43.200.203.69:57751/factory"; //mysql ���� ���� ȣ��Ʈ�� port��ȣ, ���� ��Ű�� �ּ�
	        String user = "admin"; //user id
	        String passwd = "pass"; //user password
	  
			try {
				Class.forName(className); //JDBC ����̹� �ε�
	            conn = DriverManager.getConnection(url, user, passwd); //Connection ��ü ����
	            System.out.println("Successfully Connected!");

	            StringBuilder sb = new StringBuilder();
				sb.append("SELECT recipe.ingredient_id, recipe.product_id, recipe.machine_id, product.name, product_stock.stock ");
				sb.append("FROM	recipe join product join product_stock ");
				sb.append("WHERE recipe.product_id = product.id && recipe.product_id = product_stock.id");
				pstmt = conn.prepareStatement(sb.toString());
				
				rs = pstmt.executeQuery(); // ��� ����� �޾ƿ��� ResultSet Ÿ���� rs ������ �������� ������ ��� �־��� 
				while (rs.next()) {
					String ing = rs.getString("recipe.ingredient_id");
	            	String id = rs.getString("recipe.product_id");
	            	String machine = rs.getString("recipe.machine_id");
	                String name = rs.getString("product.name");
	                String stock = rs.getString("product_stock.stock");
	                int st = Integer.parseInt(stock);
	                if(st<1) {
	                	System.out.println(name + "�� ��� �����ϴ�. ingredient " + ing + "�� ���" + machine + "���� ������ ��û�մϴ�.");
	                	continue;
	                }
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

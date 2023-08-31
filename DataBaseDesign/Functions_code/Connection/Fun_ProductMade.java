package Connection;
import java.sql.*;

public class Fun_ProductMade {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; //SQL문을 데이터베이스에 보내기 위한 객체
		ResultSet rs = null; //결과 리턴하기 위한 객체

	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://43.200.203.69:57751/factory"; //mysql 접근 위한 호스트와 port번호, 접근 스키마 주소
	        String user = "admin"; //user id
	        String passwd = "pass"; //user password
	  
			try {
				Class.forName(className); //JDBC 드라이버 로딩
	            conn = DriverManager.getConnection(url, user, passwd); //Connection 객체 생성
	            System.out.println("Successfully Connected!");

	            StringBuilder sb = new StringBuilder();
				sb.append("SELECT recipe.ingredient_id, recipe.product_id, recipe.machine_id, product.name, product_stock.stock ");
				sb.append("FROM	recipe join product join product_stock ");
				sb.append("WHERE recipe.product_id = product.id && recipe.product_id = product_stock.id");
				pstmt = conn.prepareStatement(sb.toString());
				
				rs = pstmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과 넣어줌 
				while (rs.next()) {
					String ing = rs.getString("recipe.ingredient_id");
	            	String id = rs.getString("recipe.product_id");
	            	String machine = rs.getString("recipe.machine_id");
	                String name = rs.getString("product.name");
	                String stock = rs.getString("product_stock.stock");
	                int st = Integer.parseInt(stock);
	                if(st<1) {
	                	System.out.println(name + "의 재고가 없습니다. ingredient " + ing + "로 기계" + machine + "에게 생산을 요청합니다.");
	                	continue;
	                }
	            }
	        }

	        catch(ClassNotFoundException e){
	        	System.out.println("[JDBC Connector Driver 오류 : " + e.getMessage() + "]");
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

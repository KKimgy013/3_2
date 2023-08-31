package Connection;
import java.sql.*;
import java.util.*;

public class Fun_Prod {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		PreparedStatement pstmt = null; //SQL문을 데이터베이스에 보내기 위한 객체
		Statement stmt = null;
		ResultSet rs = null; //결과 리턴하기 위한 객체

	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://43.200.203.69:57751/factory"; //mysql 접근 위한 호스트와 port번호, 접근 스키마 주소
	        String user = "admin"; //user id
	        String passwd = "pass"; //user password
	  
			try {
				Class.forName(className); //JDBC 드라이버 로딩
	            conn = DriverManager.getConnection(url, user, passwd); //Connection 객체 생성
	            System.out.println("Successfully Connected!");

	            System.out.println("어떤 product를 만드시겠습니까?(id로 답변) ");
	            int pid = sc.nextInt();
	            System.out.println("최종 재고량을 얼마로 하시겠습니까?(기존개수를 꼭 더해주세요) ");
	            int pcnt = sc.nextInt();
	            String sql1 = "update product_stock set stock=? where id=?";
	            
	            pstmt = conn.prepareStatement(sql1);

	            pstmt.setInt(1, pcnt);
	            pstmt.setInt(2, pid);
	            
	            int r = pstmt.executeUpdate();
	            System.out.println("변경된 row : " + r);
	            
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

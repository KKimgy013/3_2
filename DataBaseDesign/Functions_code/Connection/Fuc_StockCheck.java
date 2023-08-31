package Connection;

import java.sql.*;

public class Fuc_StockCheck {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null; //SQL문을 데이터베이스에 보내기 위한 객체
		ResultSet rs = null; //결과 리턴하기 위한 객체

	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://43.200.203.69:57751/factory"; //mysql 접근 위한 호스트와 port번호, 접근 스키마 주소
	        String user = "admin"; //user id
	        String passwd = "pass"; //user password
	        String sql = "SELECT name, stock FROM ingredient"; //실행 sql문
	  
			try {
				Class.forName(className); //JDBC 드라이버 로딩
				 
	            conn = DriverManager.getConnection(url, user, passwd); //Connection 객체 생성
	            System.out.println("Successfully Connected!");
				stmt = conn.createStatement(); // Statement객체 생성
	            rs = stmt.executeQuery(sql); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과 넣어줌 

	            while (rs.next()) {
	            	String name = rs.getString("name");
	                String stock = rs.getString("stock");
	                int st = Integer.parseInt(stock);
	                if(st<1) System.out.println(name + "의 재고가 없습니다. 재고 확인 후 주문바랍니다.");
	                else System.out.println(name + "의 재고가 존재합니다.");
	            }
	            
	        }

	        catch(ClassNotFoundException e){
	        	System.out.println("[JDBC Connector Driver 오류 : " + e.getMessage() + "]");
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

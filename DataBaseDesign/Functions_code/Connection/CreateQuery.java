package Connection;
import java.sql.*;

public class CreateQuery {
	 public static void main(String[] args){
	        Connection conn = null;
	        Statement stmt = null;
	        
	        String className = "com.mysql.cj.jdbc.Driver";
	        String url = "jdbc:mysql://localhost:3306/factory_crucialpoint?useSSL=false";
	        String user = "root";
	        String passwd = "20206319";
	 
	        try{
	            Class.forName(className);
	 
	            conn = DriverManager.getConnection(url, user, passwd);
	            System.out.println("Successfully Connected!");
	            stmt = conn.createStatement();
	 
	            StringBuilder sb = new StringBuilder();
	            String sql = sb.append("CREATE TABLE product_order (")
	                    .append("sales_order_id int NOT NULL,")
	                    .append("product_id int NOT NULL,")
	                    .append("buyer_id int NOT NULL,")
	                    .append("expiration_date date NOT NULL,")
	                    .append("quantity int NOT NULL,")
	                    .append("PRIMARY KEY (sales_order_id),")
	                    .append("UNIQUE KEY sales_order_id_UNIQUE (sales_order_id),")
	                    .append("KEY product_order_ibfk_1_idx (product_id),")
	                    .append("KEY product_order_ibfk_2 (buyer_id),")
	                    .append("CONSTRAINT product_order_ibfk_1 FOREIGN KEY (product_id) REFERENCES product_price_list (product_id) ON DELETE NO ACTION ON UPDATE NO ACTION,")
	                    .append("CONSTRAINT product_order_ibfk_2 FOREIGN KEY (buyer_id) REFERENCES product_price_list (buyer_id) ON DELETE NO ACTION ON UPDATE NO ACTION")
	                    .append(") ENGINE=InnoDB DEFAULT CHARSET=latin1 ;").toString();
	            stmt.execute(sql);
	        }
	 
	        catch(ClassNotFoundException e){
	            e.printStackTrace();
	        }
	        catch(SQLException e){
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                if(conn != null && !conn.isClosed()) conn.close();
	            } catch(SQLException e){
	                e.printStackTrace();
	            }
	        }
	    }
}

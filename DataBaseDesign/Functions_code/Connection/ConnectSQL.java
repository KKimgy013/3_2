package Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectSQL {
    public static void main(String[] args) {
    	Connection con = null;
        
        String className = "com.mysql.cj.jdbc.Driver";
        //String url = "jdbc:mysql://43.200.203.69:57751/factory?useSSL=false&useUnicode=true&characterEncoding=euckr"; factory전용서버 URL
        String url = "jdbc:mysql://localhost:3306/factory_crucialpoint?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user = "root";
        String passwd = "20206319";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connect Success!");
        } catch(Exception e) {
            System.out.println("Connect Failed!");
            e.printStackTrace();
        } finally {
                try {
                    if(con != null && !con.isClosed()) {
                        con.close();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
        }
    }
}

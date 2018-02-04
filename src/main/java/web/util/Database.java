package web.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    static Connection connection=null;
    public Database() {

    }
    public static Connection getConnection(){
        if(connection==null){
            try {
                Context initial = null;
                initial = new InitialContext();
                DataSource ods = (DataSource) initial.lookup("java:comp/env/jdbc/order");
                connection = ods.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

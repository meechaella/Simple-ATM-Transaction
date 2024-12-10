package atmtransaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    //connection object to handle database connection
    private static Connection connection;
     
    //required for MySQL database connection
    private static final String DbName = "atm.db";
    private static final String DbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String DbUrl = "jdbc:mysql://localhost:3306/" + DbName;
    private static final String DbUsername = "root";
    private static final String DbPassword = "";
   
    //method to get the database connection
    public static Connection getConnection() {
        if(connection == null) { //if connection is null, initialize it
            try {
                Class.forName(DbDriver); //load MySQL driver
                connection = DriverManager.getConnection(DbUrl, DbUsername, DbPassword); //connect to database
                System.out.println("Database Connection Successful");
            }catch (ClassNotFoundException | SQLException e) {
                //if connection fails
                System.out.println("Database Connection Failed: " + e.getMessage());
            }
        }
        return connection;
    }  
}
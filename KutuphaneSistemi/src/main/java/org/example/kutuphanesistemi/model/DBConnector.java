package org.example.kutuphanesistemi.model;
import java.sql.*;


public class DBConnector {
    private static DBConnector instance;
    private Connection connection;

    private DBConnector() throws SQLException {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;database=Kutuphane;encrypt=true;trustServerCertificate=true";
            String username="sa";
            String password="123furkan";
            this.connection = DriverManager.getConnection(url,username,password);

        }catch(Exception e){
            throw new SQLException(e);
        }
    }

    public static DBConnector getInstance() throws SQLException {
        if(instance == null){
            synchronized(DBConnector.class){
                if(instance==null){
                    instance=new DBConnector();
                }
            }
            instance=new DBConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
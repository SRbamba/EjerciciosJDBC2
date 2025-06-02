package Conexion_1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private String url = "jdbc:mysql://localhost:3306/empresa";
    private String user = "root";
    private String password = "1234";

    Connection connection = null;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
        }
        catch (SQLException ex){
            System.out.println("Error de conexion");
            ex.printStackTrace();
        }
        return connection;
    }
}

package dao;

import java.sql.*;

public class InsertarProductoDao {
    public boolean insertarProducto(Connection connection, String nombre, double precio, int stock){
        String query = "INSERT INTO productos (nombre, precio, stock) values (?,?,?) ";

        //Creacion de la consulta query al servidor y luego pasan los datos del producto a la query con sus respectivos indices

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, nombre);
            preparedStatement.setDouble(2, precio);
            preparedStatement.setInt(3, stock);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
            }
            System.out.println("Se inserto un producto correctamente");
            return true;
        } catch (SQLException ex){
            System.out.print("Error, no se pudo insertar el empleado: " + ex.getMessage());
        }
        return false;
    }
}

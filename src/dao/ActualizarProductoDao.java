package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarProductoDao {
    public boolean actualizarPrecioProducto(Connection connection, int id, double precio){
        String query = "UPDATE productos SET precio = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setDouble(1, precio);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Actualizacion del producto exitosa");
            } else {
                System.out.println("No se encontro ningun producto con ese id");
            }

            return true;
        } catch (SQLException ex){
            System.out.println("Error al intentar actualizar el precio del prodcuto: " + ex.getMessage());
        }

        return false;
    }

    public boolean actualizarStockProducto(Connection connection, int id, int stock){
        String query = "UPDATE productos SET stock = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Actualizacion del producto exitosa");
            } else {
                System.out.println("No se encontro ningun producto con ese id");
            }

            return true;
        } catch (SQLException ex){
            System.out.println("Error al intentar actualizar el precio del prodcuto: " + ex.getMessage());
        }

        return false;
    }

    public boolean actualizarNombreProducto(Connection connection, int id, String nombre){
        String query = "UPDATE productos SET nombre = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Actualizacion del producto exitosa");
            } else {
                System.out.println("No se encontro ningun producto con ese id");
            }

            return true;
        } catch (SQLException ex){
            System.out.println("Error al intentar actualizar el precio del prodcuto: " + ex.getMessage());
        }

        return false;
    }
}

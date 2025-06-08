package dao;

import java.sql.*;

public class ProductoDao {
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

    public boolean eliminar(Connection connection, int id){
        String query = "DELETE FROM productos WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                System.out.println("Producto eliminado exitosamente");
                return true;
            }

            else {
                System.out.println("No se pudo eliminar el producto con ese id");
            }

        } catch (SQLException ex){
            System.out.println("Error al eliminar el empleado: " + ex.getMessage());
        }
        return false;
    }

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

    public boolean listarProducto(Connection connection){
        String query = "SELECT * FROM productos";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSets = preparedStatement.executeQuery();
            while (resultSets.next()) {
                System.out.println(
                        resultSets.getInt("id") + " | " +
                                resultSets.getString("nombre") + " | " +
                                resultSets.getDouble("precio") + " | " +
                                resultSets.getInt("stock")
                );
            }
            return true;
        } catch (SQLException e){
            e.getMessage();
        }

        return false;
    }
}

package dao;

import java.sql.*;

public class ClienteDao {
    public boolean insertar(Connection connection, String nombre, String direccion, String telefono, String email){
        String query = "INSERT INTO clientes (nombre, direccion, telefono, email) values (?,?,?,?) ";

        //Creacion de la consulta query al servidor y luego pasan los datos del producto a la query con sus respectivos indices

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, direccion);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, email);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
            }
            System.out.println("Se inserto un cliente correctamente");
            return true;
        } catch (SQLException ex){
            System.out.print("Error, no se pudo insertar el cliente: " + ex.getMessage());
        }
        return false;
    }

    public boolean eliminar(Connection connection, int id){
        String query = "DELETE FROM clientes WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                System.out.println("Producto eliminado exitosamente");
                return true;
            }

            else {
                System.out.println("No se pudo eliminar el cliente con ese id");
            }

        } catch (SQLException ex){
            System.out.println("Error al eliminar el cliente: " + ex.getMessage());
        }
        return false;
    }

    public boolean actualizarDireccion(Connection connection, int id, String direccion){
        String query = "UPDATE clientes SET direccion = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, direccion);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Actualizacion del cliente exitosa");
            } else {
                System.out.println("No se encontro ningun cliente con ese id");
            }

            return true;
        } catch (SQLException ex){
            System.out.println("Error al intentar actualizar el domicilio del cliente: " + ex.getMessage());
        }

        return false;
    }

    public boolean actualizarTelefono(Connection connection, int id, String telefono){
        String query = "UPDATE clientes SET telefono = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, telefono);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Actualizacion del cliente exitosa");
            } else {
                System.out.println("No se encontro ningun cliente con ese id");
            }

            return true;
        } catch (SQLException ex){
            System.out.println("Error al intentar actualizar el telefono del cliente: " + ex.getMessage());
        }

        return false;
    }

    public boolean actualizarEmail(Connection connection, int id, String email){
        String query = "UPDATE clientes SET email = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Actualizacion del cliente exitosa");
            } else {
                System.out.println("No se encontro ningun cliente con ese id");
            }

            return true;
        } catch (SQLException ex){
            System.out.println("Error al intentar actualizar el email del cliente: " + ex.getMessage());
        }

        return false;
    }

    public boolean listar(Connection connection){
        String query = "SELECT * FROM clientes";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSets = preparedStatement.executeQuery();
            while (resultSets.next()) {
                System.out.println(
                        resultSets.getInt("id") + " | " +
                                resultSets.getString("nombre") + " | " +
                                resultSets.getString("direccion") + " | " +
                                resultSets.getString("telefono") + " | " +
                                resultSets.getString("email")
                );
            }
            return true;
        } catch (SQLException e){
            e.getMessage();
        }

        return false;
    }
}

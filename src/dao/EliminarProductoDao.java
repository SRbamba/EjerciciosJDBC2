package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarProductoDao {
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
}

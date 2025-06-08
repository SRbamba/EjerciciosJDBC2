package service.EliminarEmpleado_6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteWorker {
    public boolean eliminar(Connection connection, int idEmpleado){
        String request = "delete from empleados where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)){
            preparedStatement.setInt(1, idEmpleado);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                System.out.println("Empleado eliminado correctamente");
                return true;
            }
            else{
                System.out.println("No se pudo eliminar ningun empleado con ese ID");
            }


        } catch (SQLException e){
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }
        return false;
    }
}

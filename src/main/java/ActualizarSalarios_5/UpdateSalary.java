package ActualizarSalarios_5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateSalary {
    public boolean actualizar(Connection connection, int idEmpleado, double salario){
        String request = "update empleados set salario = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)){
            preparedStatement.setDouble(1, salario);
            preparedStatement.setInt(2, idEmpleado);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                System.out.println("Sueldo actualizado correctamente");
                return true;
            }
            else{
                System.out.println("No se encontro ningun empleado con ese ID");
            }


        } catch (SQLException e){
            System.out.println("Error al actualizar sueldo: " + e.getMessage());
        }
        return false;
    }
}

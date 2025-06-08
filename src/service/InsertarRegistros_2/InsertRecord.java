package service.InsertarRegistros_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertRecord {

    public boolean insertar(Connection connection, int id, String nombre, String apellido, String email, Double salario){
        String request = "insert into empleados (id, nombre, apellido, email, salario) values (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(request)){
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, email);
            preparedStatement.setDouble(5, salario);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e){
            System.out.println("Error al insertar empleado " + e.getMessage());
        }
        return false;
    }
}
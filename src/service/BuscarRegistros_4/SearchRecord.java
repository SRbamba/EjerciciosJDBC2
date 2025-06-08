package service.BuscarRegistros_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchRecord {

    public boolean buscar(Connection connection, String nombreProducto){
        String request = "select * from productos where nombre like ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(request)){
            preparedStatement.setString(1, "%" + nombreProducto + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean encontrado = false;

            while(resultSet.next()){
                System.out.println(
                        resultSet.getInt("id") + " | " +
                        resultSet.getString("nombre") + " | " +
                        resultSet.getDouble("precio") + " | " +
                        resultSet.getInt("stock")
                );
                encontrado = true;
            }

            if (!encontrado){
                System.out.println("No se encontraron productos con ese nombre");
            }

        } catch (SQLException e){
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return false;
    }

}

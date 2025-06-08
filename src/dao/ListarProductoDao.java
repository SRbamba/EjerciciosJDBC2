package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarProductoDao {
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

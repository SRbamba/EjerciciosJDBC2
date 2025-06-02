package MostrarRegistros_3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintRecord {
    public boolean mostrar(Connection connection){
    String request = "select * from productos";
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)){
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

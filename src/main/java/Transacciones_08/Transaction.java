package Transacciones_08;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Transaction {
    public boolean transferirDinero(Connection connection, int idOrigen, int idDestino, double monto) {
        String querySaldo = "select saldo from cuentas where id = ?";
        String updateRestar = "select cuentas set saldo = saldo - ? where id = ?";
        String updateSumar  = "select cuentas set saldo = saldo + ? where id = ?";

        try {
            connection.setAutoCommit(false);

            // Verificar saldo suficiente
            double saldoOrigen = 0;
            try (PreparedStatement psSaldo = connection.prepareStatement(querySaldo)) {
                psSaldo.setInt(1, idOrigen);
                ResultSet rs = psSaldo.executeQuery();
                if (rs.next()) {
                    saldoOrigen = rs.getDouble("saldo");
                } else {
                    System.out.println("La cuenta de origen no existe.");
                    return false;
                }
            }

            if (saldoOrigen < monto) {
                System.out.println("Saldo insuficiente | Saldo actual: " + saldoOrigen);
                return false;
            }

            // Ejecutar transferencia
            try (
                    PreparedStatement psRestar = connection.prepareStatement(updateRestar);
                    PreparedStatement psSumar = connection.prepareStatement(updateSumar)
            ) {
                // Restar al origen
                psRestar.setDouble(1, monto);
                psRestar.setInt(2, idOrigen);
                int afectadasRestar = psRestar.executeUpdate();

                // Sumar al destino
                psSumar.setDouble(1, monto);
                psSumar.setInt(2, idDestino);
                int afectadasSumar = psSumar.executeUpdate();

                if (afectadasRestar == 1 && afectadasSumar == 1) {
                    connection.commit();
                    System.out.println("Transferencia exitosa.");
                    return true;
                } else {
                    connection.rollback();
                    System.out.println("Transacción cancelada: una o ambas cuentas no existen.");
                    return false;
                }
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error durante la transacción: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al iniciar la transacción: " + e.getMessage());
            return false;

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("⚠️ Error al restaurar autoCommit: " + e.getMessage());
            }
        }
    }

}

package org.example;

import Conexion_1.MySQLConnection;
import InsertarRegistros_2.InsertRecord;
import MostrarRegistros_3.PrintRecord;
import BuscarRegistros_4.SearchRecord;
import ActualizarSalarios_5.UpdateSalary;
import EliminarEmpleado_6.DeleteWorker;
import Transacciones_08.Transaction;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MySQLConnection mySQLConnection = new MySQLConnection();
        Connection connection = mySQLConnection.getConnection();

        System.out.println("SELECCIONE LA OPERACION:" +
                "\n1. INSERTAR EMPLEADO\n2. MOSTRAR PRODUCTOS\n3. BUSCAR PRODUCTO\n4. ACTUALIZAR SALARIO\n5. ELIMINAR EMPLEADO\n6.REALIZAR TRANSFERENCIA");
        System.out.print("OPCION: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op) {
            case 1:
                insertarEmpleado(connection, scanner);
                break;
            case 2:
                mostrarProductos(connection);
                break;
            case 3:
                buscarProducto(connection, scanner);
                break;
            case 4:
                actualizarSalario(connection, scanner);
                break;
            case 5:
                eliminarEmpleado(connection, scanner);
                break;
            case 6:
                realizarTransaccion(connection, scanner);
                break;
            default:
                System.out.println("NO HA ELEGIDO NINGUNA OPCIÓN VÁLIDA");
                break;
        }

        scanner.close();
    }

    private static void insertarEmpleado(Connection connection, Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Salario: ");
        double salario = scanner.nextDouble();

        InsertRecord insertRecord = new InsertRecord();
        boolean exito = insertRecord.insertar(connection, id, nombre, apellido, email, salario);

        if (exito == true) {
            System.out.println("Empleado insertado correctamente");
        } else {
            System.out.println("Error al insertar empleado");
        }
    }

    private static void mostrarProductos(Connection connection){
        PrintRecord printRecord = new PrintRecord();
        printRecord.mostrar(connection);
    }

    private static void buscarProducto(Connection connection, Scanner scanner){
        System.out.print("Ingrese el nombre o parte del producto que desea buscar: ");
        String nombreProducto = scanner.next();
        scanner.nextLine();

        SearchRecord searchRecord = new SearchRecord();
        boolean exito = searchRecord.buscar(connection, nombreProducto);
    }

    private static void actualizarSalario(Connection connection, Scanner scanner){
        System.out.print("Ingrese el id del empleado al cual desea actualizarle el sueldo: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el sueldo del nuevo empleado: ");
        double sueldo = scanner.nextDouble();
        scanner.nextLine();

        UpdateSalary updateSalary = new UpdateSalary();
        boolean exito = updateSalary.actualizar(connection, idEmpleado, sueldo);

        if (exito == true){
            System.out.println("El sueldo del empleado ha sido actualizado correctamente");
        }
        else {
            System.out.println("Error al intentar actualizar el sueldo del empleado");
        }
    }

    private static void eliminarEmpleado(Connection connection, Scanner scanner){
        System.out.print("Ingrese el id del empleado al cual desea eliminar: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();

        DeleteWorker deleteWorker = new DeleteWorker();

        boolean exito = deleteWorker.eliminar(connection, idEmpleado);
    }

    private static void realizarTransaccion(Connection connection, Scanner scanner){

        System.out.print("Ingrese el id de la cuenta a la cual desea debitar dinero: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el id de la cuenta a la cual desea acreditar dinero: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        Transaction transaction = new Transaction();

        boolean exito = transaction.transferirDinero(connection, idOrigen, idDestino, monto);

    }
}



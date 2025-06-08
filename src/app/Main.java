import dao.InsertarProductoDao;
import util.MySQLConnection;
import service.InsertarRegistros_2.InsertRecord;
import service.MostrarRegistros_3.PrintRecord;
import service.BuscarRegistros_4.SearchRecord;
import service.ActualizarSalarios_5.UpdateSalary;
import service.EliminarEmpleado_6.DeleteWorker;
import service.Transacciones_08.Transaction;

//METODOS DAO
import dao.InsertarProductoDao;
import dao.EliminarProductoDao;
import dao.ListarProductoDao;
import dao.ActualizarProductoDao;
/////////////

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

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("SELECCIONE LA OPERACION:" + "\n1. INSERTAR PRODUCTO\n2. ELIMINAR PRODUCTOS\n3. ACTUALIZAR PRODUCTO\n4. LISTAR PRODUCTOS");
        System.out.print("OPCION: ");
        op = scanner.nextInt();
        scanner.nextLine();

        switch (op){
            case 1:
                insertarProducto(connection, scanner);
                break;
            case 2:
                eliminarProducto(connection, scanner);
                break;
            case 3:
                actualizarProducto(connection, scanner);
                break;
            case 4:
                listarProductos(connection);
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

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void insertarProducto(Connection connection, Scanner scanner){
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        InsertarProductoDao insertarProductoDao = new InsertarProductoDao();
        boolean exito = insertarProductoDao.insertarProducto(connection, nombre, precio, stock);
    }

    public static void eliminarProducto(Connection connection, Scanner scanner){
        System.out.print("Ingrese el id del producto al cual desea eliminar: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine();

        EliminarProductoDao eliminarProductoDao = new EliminarProductoDao();

        boolean exito = eliminarProductoDao.eliminar(connection, idProducto);
    }

    public static void actualizarProducto(Connection connection, Scanner scanner){
        System.out.print("Ingrese el id del producto que desea modificar: ");
        int idProducto = scanner.nextInt();
        scanner.nextLine();

        ActualizarProductoDao actualizarProductoDao = new ActualizarProductoDao();

        System.out.println("SELECCIONE UNA OPCION:\n1. MODIFICAR NOMBRE\n2. MODIFICAR PRECIO\n3. MODIFICAR STOCK");
        System.out.print("OPCION: ");
        int op = scanner.nextInt();
        scanner.nextLine();

        switch (op){
            case 1:
                System.out.print("Ingrese el nuevo nombre del producto: ");
                String nombreNuevo = scanner.nextLine();
                    actualizarProductoDao.actualizarNombreProducto(connection, idProducto, nombreNuevo);
                break;
            case 2:
                System.out.print("Ingrese el nuevo precio del producto: ");
                double precioNuevo = scanner.nextDouble();
                scanner.nextLine();
                actualizarProductoDao.actualizarPrecioProducto(connection, idProducto, precioNuevo);
                break;
            case 3:
                System.out.print("Ingrese el nuevo stock del producto: ");
                int stockNuevo = scanner.nextInt();
                scanner.nextLine();
                actualizarProductoDao.actualizarStockProducto(connection, idProducto, stockNuevo);
                break;
            default:
                System.out.println("No se ingreso ninguna opcion disponible");
                break;
        }
    }

    public static void listarProductos(Connection connection){
        ListarProductoDao listarProductoDao = new ListarProductoDao();
        listarProductoDao.listarProducto(connection);
    }
}




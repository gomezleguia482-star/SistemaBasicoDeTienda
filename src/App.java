
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import CargarArchivos.CargarClientes;
import CargarArchivos.CargarProductos;
import CargarArchivos.CargarVentas;
import Cliente.Cliente;
import ItemsVenta.Articulos;
import Services.ServicesCliente;
import Services.ServicesProducto;
import Services.ServicesVenta;
import Venta.Venta;
import Producto.*;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();

        CargarProductos cargarProductos = new CargarProductos();
        ArrayList<Producto> productosCargados = cargarProductos.cargarProductos();

        CargarClientes cargarClientes = new CargarClientes();
        ArrayList<Cliente> clientesCargados = cargarClientes.cargarClientes();

        CargarVentas cargarVentas = new CargarVentas();
        ArrayList<Venta> ventasCargadas = cargarVentas.cargarVentas();

        int opcion = 0;

        ServicesProducto servicesProducto = new ServicesProducto();
        servicesProducto.productos = productosCargados;

        ServicesCliente servicesCliente = new ServicesCliente();
        servicesCliente.clientes = clientesCargados;

        ServicesVenta servicesVenta = new ServicesVenta(servicesCliente, servicesProducto);
        servicesVenta.ventas = ventasCargadas;

        do {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Productos");
            System.out.println("3. Buscar Producto por ID");
            System.out.println("4. Agregar Cliente");
            System.out.println("5. Mostrar Clientes");
            System.out.println("6. Realizar Venta");
            System.out.println("7. Mostrar Ventas");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer


            switch(opcion) {
                case 1:
                    servicesProducto.agregarProductos(sc);
                    break;
                case 2:
                    servicesProducto.mostrarProductos();
                    break;
                case 3:
                    servicesProducto.buscarProductos(sc);
                    break;
                case 4:
                    servicesCliente.agregarClientes(sc);
                    break;
                case 5:
                    servicesCliente.mostrarClientes();
                    break;
                case 6:
                    servicesVenta.realizarVenta(sc);
                    break;
                case 7:
                    servicesVenta.mostrarVentas();
                    break;
                case 8:
                    System.out.println("¡Saliendo del programa!");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } while(opcion != 8);

        sc.close();
        }
}

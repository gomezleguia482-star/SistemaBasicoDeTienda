package org.example.CapaDeServicios;

import org.example.CapaDeModelos.CarpetaArticulo.Articulo;
import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaProducto.Producto;
import org.example.CapaDeModelos.CarpetaVenta.Venta;
import org.example.CapaDePersistencia.ClienteDAO;
import org.example.CapaDePersistencia.ManejadorCSV;
import org.example.CapaDePersistencia.ProductoDAO;
import org.example.CapaDePersistencia.VentaDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicioVenta {

    public static void realizarVenta(Scanner sc){
        ArrayList<String> listaVentas = new ArrayList<>();
        ArrayList<Venta> numeroVentas = VentaDAO.CargarVentas();
        ArrayList<Producto> lineasProductos = ProductoDAO.cargarProducto();
        System.out.println("Ingresa el id del cliente");
        int idCliente = sc.nextInt();

        Cliente cliente = ClienteDAO.buscarClienteID(idCliente);

        ArrayList<Articulo> listaArticulos = new ArrayList<>();

        String seguir = "si";
        double totalCompra = 0.0;
        while (seguir.equalsIgnoreCase("si")){
            System.out.println("Ingresa el id del producto");
            int idProducto = sc.nextInt();

            Producto producto = ProductoDAO.buscarProductoID(idProducto);

            System.out.println("Ingresa al cantidad que desea comprar de: " + producto.getNombreProducto());
            int cantidadSell = sc.nextInt();

            if (producto.getStockProducto() < cantidadSell){
                System.out.println("Cantida no disponible");
                return;
            }

            Articulo articulo = new Articulo(producto.getIdProducto(), cantidadSell);
            listaArticulos.add(articulo);


            for (Producto P: lineasProductos){
                if(P.getIdProducto() == producto.getIdProducto()){
                    P.setStockProducto(cantidadSell);
                    if(!P.validarDisponibilidad(P.getStockProducto())){
                        P.setDisponible(false);
                    }
                }
            }

            totalCompra += producto.getPrecio() * cantidadSell;

            sc.nextLine();
            System.out.println("Desea Ingresar otro proucto a la compra (Si / No)");
            seguir = sc.nextLine();

        }

        int idVenta = numeroVentas.size() + 1;

        LocalDate fechaVenta = LocalDate.now();

        Venta nuevaVenta = new Venta(idVenta, cliente.getId(), listaArticulos, totalCompra,fechaVenta);
        listaVentas.add(nuevaVenta.toString());

        ManejadorCSV.guardarCsv("Resource/Ventas.csv", listaVentas);

        ArrayList<String> lineasEscribir = new ArrayList<>();
        for (Producto producto : lineasProductos) {
            lineasEscribir.add(producto.toString());
        }

        ManejadorCSV.sobreescribirCsv("Resource/Productos.csv",lineasEscribir);
    }

    public static void mostrarVentas(){
        ArrayList<Venta> ventas = VentaDAO.CargarVentas();
        for (Venta V: ventas){
            System.out.println(V.toString());
        }
    }
}

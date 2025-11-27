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

        try{
            int idCliente;
            Cliente cliente;

            // Hacer esta validadcion en el punto de producto tambien
            do {
                System.out.println("Ingresa el id del cliente");
                idCliente = sc.nextInt();
                sc.nextLine();

                cliente = ClienteDAO.buscarClienteID(idCliente);

                if(cliente == null){
                    System.out.println("Cliente no encontrado verifica nuevamente su id");
                    System.out.println("¿ Desea cancelar su venta ?");
                     String cancelarVenta = sc.nextLine();

                    if (cancelarVenta.equalsIgnoreCase("si")) {
                        System.out.println("Venta cancelada");
                        return;
                    } else if (cancelarVenta.equalsIgnoreCase("no")) {
                        continue;
                    } else if (!cancelarVenta.equalsIgnoreCase("si")) {
                        do {
                                System.out.println("ERROR. Unicas opciones validas (Si / No)");
                                System.out.println("¿ Desea cancelar su venta ?");
                                cancelarVenta = sc.nextLine();

                                if (cancelarVenta.equalsIgnoreCase("si")) {
                                    System.out.println("Venta cancelada");
                                    return;
                                }

                        }while (!cancelarVenta.equalsIgnoreCase("no"));
                    }
                }
            }while (cliente == null);

            ArrayList<Articulo> listaArticulos = new ArrayList<>();

            String seguir = "si";
            double totalCompra = 0.0;
            Producto producto;

            while (seguir.equalsIgnoreCase("si")){

                do {
                    System.out.println("Ingresa el id del producto");
                    int idProducto = sc.nextInt();
                    sc.nextLine();

                    producto = ProductoDAO.buscarProductoID(idProducto);

                    if(producto == null){
                        System.out.println("Cliente no encontrado verifica nuevamente su id");
                        System.out.println("¿ Desea cancelar su venta ?");
                        String cancelarVenta = sc.nextLine();

                        if (cancelarVenta.equalsIgnoreCase("si")) {
                            System.out.println("Venta cancelada");
                            return;
                        } else if (cancelarVenta.equalsIgnoreCase("no")) {
                           continue;
                        } else if (!cancelarVenta.equalsIgnoreCase("si")) {
                            do {
                                System.out.println("ERROR. Unicas opciones validas (Si / No)");
                                System.out.println("¿ Desea cancelar su venta ?");
                                cancelarVenta = sc.nextLine();

                                if (cancelarVenta.equalsIgnoreCase("si")) {
                                    System.out.println("Venta cancelada");
                                    return;
                                }

                            }while (!cancelarVenta.equalsIgnoreCase("no"));
                        }
                    }
                }while (producto == null);


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

                if(!seguir.equalsIgnoreCase("si") && !seguir.equalsIgnoreCase("no")){
                    System.out.println("ERROR. Unicas opciones validas (Si / No), \n -VENTA CANCELADA");
                    return;
                }

            }

            int idVenta = numeroVentas.size() + 1;

            LocalDate fechaVenta = LocalDate.now();

            Venta nuevaVenta = new Venta(idVenta, cliente.getId(), listaArticulos, totalCompra,fechaVenta);
            listaVentas.add(nuevaVenta.toString());

            ManejadorCSV.guardarCsv("Resource/Ventas.csv", listaVentas);

            ArrayList<String> lineasEscribir = new ArrayList<>();
            for (Producto P : lineasProductos) {
                lineasEscribir.add(P.toString());
            }

            ManejadorCSV.sobreescribirCsv("Resource/Productos.csv",lineasEscribir);
        }catch(java.util.InputMismatchException e){
            System.out.println("ERROR. Debe ingresar datos numericos");
            System.out.println("Reintente nuevamante");
            // **LA LÍNEA CRUCIAL: Limpia el buffer después de la excepción**
            sc.nextLine();
            // Vuelve a llamar a realizarVenta o informa que la operación falló.
            // Si la llamas recursivamente, es para que el usuario pueda reintentar.
            realizarVenta(sc);
        }
    }

    public static void mostrarVentas(){
        ArrayList<Venta> ventas = VentaDAO.CargarVentas();
        for (Venta V: ventas){
            V.mostrarInformacionVenta();
        }
    }
}

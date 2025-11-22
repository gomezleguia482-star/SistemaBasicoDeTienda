package Services;

import CargarArchivos.CargarClientes;
import CargarArchivos.CargarProductos;
import Cliente.Cliente;
import ItemsVenta.Articulos;
import Producto.Producto;
import Venta.Venta;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ServicesVenta {
    public ArrayList<Venta> ventas = new ArrayList<>();
    public ServicesCliente servicesCliente;
    public ServicesProducto servicesProducto;

    public ServicesVenta(ServicesCliente sc, ServicesProducto sp){
        this.servicesCliente = sc;
        this.servicesProducto = sp;
    }

    public void realizarVenta(Scanner sc){
        System.out.print("ID del cliente: ");
        int idCliente = sc.nextInt();

        Cliente cliente = servicesCliente.buscarClienteID(idCliente);

        if(cliente == null){
            System.out.println("Cliente no encontrado");
            return;
        }

        ArrayList<Articulos> listaItems = new ArrayList<>();
        String opcion = "si";

        while (opcion.equalsIgnoreCase("si")) {

            /* Se busca el producto por el id si lo encuentra se devuelve el producto
             * si no se acaba el programa por que devuelve null
             */
            System.out.print("Ingresa el ID del producto: ");
            int idProducto = sc.nextInt();

            Producto producto = servicesProducto.buscarProductoID(idProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado ");
                return;
            }

            System.out.print("Cantidad de " + producto.getNombre() + ": ");
            int cantidad = sc.nextInt();

            if (cantidad <= 0) {
                System.out.println("Cantidad inválida ");
                return;
            }

            if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente ");
                return;
            }


            Articulos item = new Articulos(producto, cantidad);
            listaItems.add(item);

            // Descontamos el STOCK del poroducto
            producto.vender(cantidad);

            System.out.print("¿Desea comprar algo más? (si/no): ");
            opcion = sc.next();  // (si / no)
        }

        int idVenta = ventas.size() + 1;
        String fechaVenta = LocalDate.now().toString();

        double total = 0.0;
        for (Articulos items : listaItems) {
            total += items.getSubTotal();
        }

        try {
            // actualizar archivo de productos
            ArrayList<String> nuevasLineas = new ArrayList<>();
            for (Producto P : servicesProducto.productos){
                nuevasLineas.add(P.toString());
            }
            Files.write(Paths.get("SistemaTienda/BaseDatos/Producto.txt"), nuevasLineas);

        }catch(IOException e){
            e.getMessage();
        }

        Venta nuevaVenta = new Venta(idVenta, cliente, listaItems, total, fechaVenta);

        System.out.println("Total a pagar: $" + total);

        // Guardar temporalmente en el arrayList ventas global
        ventas.add(nuevaVenta);
    }

    /// ///////////////////////////////

    public void mostrarVentas(){
        if(ventas.isEmpty()){
            System.out.println("No se ha realizado ninguna venta");
        }

        for (Venta V : ventas) {
            System.out.println("=====================================");
            System.out.println("ID Venta: " + V.getIdVenta());
            System.out.println("Cliente: " + V.getCliente().getNombre() + " (ID: " + V.getCliente().getId() + ")");
            System.out.println("Productos comprados:");

            // Recorremos el array de los Articulos y toda la informacion del producto
            // Y se mustra la informacion correspondiente a cada producto comprado
            double totalVenta = 0;
            for (Articulos items : V.getItemsVenta()) {
                Producto producto = items.getProducto();
                int cantidad = items.getCantidad();
                double subtotal = items.getSubTotal();
                totalVenta += subtotal;

                System.out.println(" - " + producto.getNombre() +
                        " | Cantidad: " + cantidad +
                        " | Precio unitario: $" + producto.getPrecio() +
                        " | Subtotal: $" + subtotal);
            }

            System.out.println("Total de la venta: $" + totalVenta) ;
            System.out.println("=====================================\n");
        }
    }
}

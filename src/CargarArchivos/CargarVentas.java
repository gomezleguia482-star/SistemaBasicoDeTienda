package CargarArchivos;

import Cliente.Cliente;
import ItemsVenta.Articulos;
import Producto.Producto;
import Services.ServicesCliente;
import Services.ServicesProducto;
import Venta.Venta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class CargarVentas {
    ArrayList<Venta> ventas = new ArrayList<>();

    public ArrayList<Venta> cargarVentas(){
        try{
            List<String> lineas = Files.readAllLines(Paths.get("SistemaTienda/BaseDatos/Venta.txt"));

            for(String L: lineas){
                String[] partes = L.split(",");

                int idVenta = Integer.parseInt(partes[0]);
                int idCliente = Integer.parseInt(partes[1]);

                ServicesCliente servicesCliente = new ServicesCliente();
                Cliente cliente = servicesCliente.buscarClienteID(idCliente);

                if(cliente == null){
                    System.out.println("Cliente no encontrado");
                    continue;
                }

                ArrayList<Articulos> articulos = new ArrayList<>();
                for(int i = 0; i < partes.length -2; i++){
                    String[] productoCantidad = partes[1].split("-");

                    int idProducto = Integer.parseInt(productoCantidad[0]);
                    int cantidadProducto = Integer.parseInt(productoCantidad[1]);

                    ServicesProducto servicesProducto = new ServicesProducto();
                    Producto producto = servicesProducto.buscarProductoID(idProducto);

                    if(producto == null){
                        System.out.println("Producto no encontrado");
                        continue;
                    }

                    articulos.add(new Articulos(producto, cantidadProducto));
                }
                double totalCompra = Double.parseDouble(partes[partes.length - 1]);
                String fechaCompra = partes[partes.length];

                Venta venta = new Venta(idVenta, cliente, articulos, totalCompra, fechaCompra);
                ventas.add(venta);
            }
        }catch(IOException e){
            e.getMessage();
        }
        return ventas;
    }
}

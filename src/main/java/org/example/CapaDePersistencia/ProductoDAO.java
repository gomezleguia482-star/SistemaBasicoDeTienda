package org.example.CapaDePersistencia;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaProducto.Producto;
import org.example.CapaDeModelos.CarpetaProducto.ProductoAlimento;
import org.example.CapaDeModelos.CarpetaProducto.ProductoElectronico;
import org.example.CapaDeModelos.CarpetaProducto.ProductoRopa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductoDAO {
    private static final String NOMBRE_CSV = "Resource/Productos.csv";

    public static ArrayList<Producto> cargarProducto(){
        List<String> lineasProducto = ManejadorCSV.leerCsv(NOMBRE_CSV);

        ArrayList<Producto> listaProductos = new ArrayList<>();
        for (String linea: lineasProducto){
            String[] partes = linea.split(",");

            String tipo = partes[0];
            int idProducto = Integer.parseInt(partes[1]);
            String nombreProducto = partes[2];
            int stockProducto = Integer.parseInt(partes[3]);
            double precioProducto = Double.parseDouble(partes[4]);
            boolean disponible = Boolean.parseBoolean(partes[5]);

            Producto producto = null;
            switch(tipo){
                case "PA":
                    LocalDate fechaVencimiento = LocalDate.parse(partes[6]);
                    producto = new ProductoAlimento(idProducto, nombreProducto, stockProducto,precioProducto,disponible, fechaVencimiento);
                    break;
                case "PE":
                    int garantiaProducto = Integer.parseInt(partes[6]);
                    producto = new ProductoElectronico(idProducto, nombreProducto, stockProducto,precioProducto,disponible, garantiaProducto);
                    break;
                case "PR":
                    int tallaProducto = Integer.parseInt(partes[6]);
                    String colorProducto = partes[7];
                    producto = new ProductoRopa(idProducto, nombreProducto, stockProducto,precioProducto,disponible, tallaProducto, colorProducto);
                    break;
                default:
                    System.out.println("Tipo de producto Invalido");
                    break;
            }
            listaProductos.add(producto);
        }
        return listaProductos;
    }

    public static Producto buscarProductoID(int idProducto){
        ArrayList<Producto> listaProductos = cargarProducto();
        for(Producto P: listaProductos){
            if(P.getIdProducto() == idProducto){
                return P;
            }
        }
        return null;
    }
}

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
    private static final String NOMBRE_CSV = "org.example/Resource/Productos.csv";

    public static ArrayList<Producto> cargarProducto(){
        List<String> lineasProducto = ManejadorCSV.leerCsv(NOMBRE_CSV);

        ArrayList<Producto> listaProductos = new ArrayList<>();
        for (String linea: lineasProducto){
            String[] partes = linea.split(",");

            String tipo = partes[0];
            int idProducto = Integer.parseInt(partes[1]);
            String nombreProducto = partes[2];
            int stockProducto = Integer.parseInt(partes[3]);

            Producto producto = null;
            switch(tipo){
                case "PA":
                    LocalDate fechaVencimiento = LocalDate.parse(partes[4]);
                    producto = new ProductoAlimento(idProducto, nombreProducto, stockProducto, fechaVencimiento);
                    break;
                case "PE":
                    int garantiaProducto = Integer.parseInt(partes[4]);
                    producto = new ProductoElectronico(idProducto, nombreProducto, stockProducto, garantiaProducto);
                    break;
                case "PR":
                    int tallaProducto = Integer.parseInt(partes[4]);
                    String colorProducto = partes[5];
                    producto = new ProductoRopa(idProducto, nombreProducto, stockProducto, tallaProducto, colorProducto);
                    break;
                default:
                    System.out.println("Tipo de producto Invalido");
                    break;
            }
            listaProductos.add(producto);
        }
        return listaProductos;
    }

    public Producto buscarProducto(int idProducto, ArrayList<Producto> listaProductos){
        for(Producto P: listaProductos){
            if(P.getIdProducto() == idProducto){
                return P;
            }
        }
        return null;
    }
}

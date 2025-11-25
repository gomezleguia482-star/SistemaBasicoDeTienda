package org.example.CapaDePersistencia;

import org.example.CapaDeModelos.CarpetaArticulo.Articulo;
import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaVenta.Venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VentaDAO {
    private static final String NOMBRE_CSV = "org.example/Resource/Ventas.csv";

    public static ArrayList<Venta> CargarVentas(){
        List<String> lineas = ManejadorCSV.leerCsv(NOMBRE_CSV);

        ArrayList<Venta> listaVentas = new ArrayList<>();
        for(String linea: lineas){
            String[] partes = linea.split(",");

            int idVenta = Integer.parseInt(partes[0]);
            int idCliente = Integer.parseInt(partes[1]);

            ArrayList<Articulo> listaArticulos = new ArrayList<>();
            for(int i = 2; i < partes.length - 2; i++){
                String[] productoCantidad = partes[i].split("-");
                int idProducto = Integer.parseInt(productoCantidad[0]);
                int cantidadStock = Integer.parseInt(productoCantidad[1]);

                Articulo articulo = new Articulo(idProducto, cantidadStock);
                listaArticulos.add(articulo);
            }

            double totalCompra = Double.parseDouble(partes[partes.length - 2]);

            Venta venta = new Venta(idVenta,idCliente,listaArticulos, totalCompra);
            listaVentas.add(venta);
        }
        return listaVentas;
    }


}

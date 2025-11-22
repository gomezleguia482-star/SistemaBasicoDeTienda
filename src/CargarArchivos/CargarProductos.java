package CargarArchivos;

import Producto.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CargarProductos {
    ArrayList<Producto> productos = new ArrayList<>();

    public ArrayList<Producto> cargarProductos(){
        ArrayList<String> lineas = new ArrayList<>();

        try {
            lineas = (ArrayList<String>) Files.readAllLines(
                    Paths.get("SistemaTienda/BaseDatos/Producto.txt")
            );
        } catch (IOException e) {
            System.out.println("ERROR al leer archivo: " + e.getMessage());
        }

        for(String L: lineas){
            Producto producto = null;
            String[] partes = L.split(",");

            String tipo = partes[0];

            switch(tipo){
                case "PA":
                    producto = new ProductoAlimento(
                            Integer.parseInt(partes[1]),
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4]),
                            Boolean.parseBoolean(partes[5]),
                            partes[6]
                    );
                    break;

                case "PE":
                    producto = new ProductoElectrico(
                            Integer.parseInt(partes[1]),
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4]),
                            Boolean.parseBoolean(partes[5]),
                            Integer.parseInt(partes[6])
                    );
                    break;

                case "PR":
                    producto = new ProductoRopa(
                            Integer.parseInt(partes[1]),
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4]),
                            Boolean.parseBoolean(partes[5]),
                            Integer.parseInt(partes[6]),
                            partes[7]
                    );
                    break;
            }

            productos.add(producto);
        }

        return productos;
    }



}

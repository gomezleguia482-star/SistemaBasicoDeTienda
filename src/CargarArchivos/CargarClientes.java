package CargarArchivos;

import Cliente.Cliente;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CargarClientes {
    ArrayList<Cliente> clientes = new ArrayList<>();

    public ArrayList<Cliente> cargarClientes(){
        try {
            List<String> lineas = Files.readAllLines(Paths.get("SistemaTienda/BaseDatos/Clientes.txt"));

            for(String L: lineas){
                String[] partes = L.split(",");

                Cliente cliente = new Cliente(
                        Integer.parseInt(partes[0]),
                        partes[1],
                        partes[2]
                );
                clientes.add(cliente);
            }
        }catch(IOException e){
            e.getMessage();
        }
        return clientes;
    }
}

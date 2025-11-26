package org.example.CapaDePersistencia;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String NOMBRE_CSV = "Resource/Clientes.csv";

    public static ArrayList<Cliente> cargarClientes(){
        List<String> lineasCliente = ManejadorCSV.leerCsv(NOMBRE_CSV);

        ArrayList<Cliente> listaClientes = new ArrayList<>();
        for (String linea: lineasCliente){
            String[] partes = linea.split(",");

            Cliente cliente = new Cliente(
              Integer.parseInt(partes[0].trim()),
              partes[1].trim(),
              partes[2].trim()
            );
            listaClientes.add(cliente);
        }
        return listaClientes;
    }


    public static Cliente buscarClienteID(int idCliente){
        ArrayList<Cliente> listaClientes = cargarClientes();
        for(Cliente C: listaClientes){
            if(C.getId() == idCliente){
                return C;
            }
        }
        return null;
    }
}

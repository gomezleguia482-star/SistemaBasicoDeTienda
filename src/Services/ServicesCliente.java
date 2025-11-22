package Services;

import Cliente.Cliente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ServicesCliente {
    public ArrayList<Cliente> clientes = new ArrayList<>();

    public void agregarClientes(Scanner sc){
        System.out.println("Ingresa el id del cliente");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresa el nombre completo del cliente");
        String nombre = sc.nextLine();

        System.out.println("Ingresa el email del cliente");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(id, nombre, email);
        String linea = cliente.toString();

        clientes.add(cliente);
        try{
            Files.write(Paths.get("SistemaTienda/BaseDatos/Clientes.txt"), Collections.singleton(linea), StandardOpenOption.APPEND);
        }catch (IOException e){
            e.getMessage();
        }
    }

    /// ///////////////////////////

    public void mostrarClientes() {
        for(Cliente C: clientes){
            C.mostrarInfo();
        }
    }

    /// ////////////////////////////

    public Cliente buscarClienteID(int id){
        for(Cliente C: clientes){
            if(C.getId() == id){
                return C;
            }
        }
        return null;
    }

}

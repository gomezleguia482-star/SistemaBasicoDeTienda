package org.example.CapaDeServicios;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDePersistencia.ClienteDAO;
import org.example.CapaDePersistencia.ManejadorCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioCliente {

    public static void agregarCliente(Scanner sc){
        try{
            List<String> listaCliente = new ArrayList<>();
            System.out.println("Ingresa el id del cliente");
            int idCliente = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingresa el nombre del cliente");
            String nombreCliente = sc.nextLine();

            if(nombreCliente.matches(".*\\d+.*")){
                throw new IllegalArgumentException("Ingresa un nombre valido no debe contener numeros");
            }

            System.out.println("Ingresa el email del cliente");
            String emailCliente = sc.nextLine();

            if(!Cliente.isValidoEmail(emailCliente)){
                do {
                    System.out.println("Ingresa un email valido (Ej: huelvoe@gmail.com)");
                    emailCliente = sc.nextLine();

                }while (!Cliente.isValidoEmail(emailCliente));
            }


            Cliente cliente = new Cliente(idCliente, nombreCliente, emailCliente);
            listaCliente.add(cliente.toString());

            ManejadorCSV.guardarCsv("Resource/Clientes.csv", listaCliente);
        }catch(java.util.InputMismatchException e){
            System.out.println("ERROR. Ingresa numeros (Solo numeros)");
        }catch(IllegalArgumentException e){
            System.out.println("ERROR. " + e.getMessage());
        }
    }

    public static void mostrarClientes(){
        ArrayList<Cliente> listaCliente = ClienteDAO.cargarClientes();
        for (Cliente C: listaCliente){
            System.out.println("Id: " + C.getId() + " | Nombre cliente: " + C.getNombreCliente() +
                    " | Email: " + C.getEmailCliente());
        }
    }

}

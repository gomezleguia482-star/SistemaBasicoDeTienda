package org.example.CapaDeServicios;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDePersistencia.ClienteDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class ServicioCliente {

    public static void agregarCliente(Scanner sc){
        try{

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


            ClienteDAO.agregarDBClientes(nombreCliente, emailCliente);
        }catch(IllegalArgumentException e){
            System.out.println("ERROR. " + e.getMessage());
        }
    }

    public static void mostrarClientes(){
        ArrayList<Cliente> listaCliente = ClienteDAO.leerDBClientes();
        for (Cliente C: listaCliente){
            System.out.println("Id: " + C.getId() + " | Nombre cliente: " + C.getNombreCliente() +
                    " | Email: " + C.getEmailCliente());
        }
    }
}

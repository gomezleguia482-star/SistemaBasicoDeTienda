package org.example.CapaDeModelos.CarpetaCliente;

import java.util.Scanner;

public class Cliente {
    Scanner sc = new Scanner(System.in);
    private int idCliente;
    private  String nombreCliente;
    private String emailCliente;

    public Cliente(int idCliente, String nombreCliente, String emailCliente ){
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
    }

    public static boolean isValidoEmail(String emailCliente){
        int arrobaIndex = emailCliente.indexOf("@");

        return emailCliente.contains("@") && arrobaIndex > 0 &&
                arrobaIndex < emailCliente.length() -1;
    }


    public int getId() {return idCliente;}
    public String getNombreCliente() {return nombreCliente;}
    public String getEmailCliente() {return emailCliente;}



    public String toString(){
        return getId() + "," + getNombreCliente() + "," + getEmailCliente();
    }
}

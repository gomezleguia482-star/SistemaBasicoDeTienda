package Cliente;


import java.util.ArrayList;

import Venta.Venta;

public class Cliente {
    //Atributos
    private int idCliente;
    private String nombre;
    private String email;
    private ArrayList<Venta> historialCompras = new ArrayList<>();

    //Constructor
    public Cliente(int idCliente, String nombre, String email, ArrayList<Venta> historialCompras){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.historialCompras = historialCompras;
    }

    //Getter y setter
    public int getId(){
        return idCliente;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    //Historial de compras del cliente
    public ArrayList<Venta> getHistorialCompras(){
        return historialCompras;
    }

    public String toString(){
        return getId() + "," + getNombre() + "," + getEmail();
    }

    //Mostrar toda la info del cliente
    public void mostrarInfo(){
        System.out.println("Id: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Email: " + getEmail());
        System.out.println("Historial de Compra");
    }

}

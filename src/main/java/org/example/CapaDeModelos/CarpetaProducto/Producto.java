package org.example.CapaDeModelos.CarpetaProducto;

import java.util.Scanner;

public class Producto {
    Scanner sc = new Scanner(System.in);
    private int idProducto;
    private String nombreProducto;
    private int stockProducto;
    private double precio;
    private boolean disponible;

    public Producto(int idProducto, String nombreProducto, int stockProducto,double precio, boolean disponible){
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
        this.precio = precio;
        this.disponible = disponible;
    }

    public static boolean validarDisponibilidad(int stockProducto){
        return stockProducto > 0;
    }

    public static boolean stockValido(int stockInicial){
       return stockInicial > -1;
    }



    public int getIdProducto() {return idProducto;}
    public String getNombreProducto() {return nombreProducto;}
    public int getStockProducto() {return stockProducto;}
    public double getPrecio() {return precio;}
    public boolean getDisponible() {return disponible;}


    public void setStockProducto(int cantidad) {
        this.stockProducto -= cantidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return getIdProducto() + "," + getNombreProducto() + "," + getStockProducto() +  "," + getPrecio() +"," + getDisponible();
    }

    public void mostrarInfoProducto(){
        System.out.println("Id: " + getIdProducto());
        System.out.println("Nombre: " + getNombreProducto());
        System.out.println("Stock: " + getStockProducto());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Disponibilidad: " + getDisponible());
    }
}

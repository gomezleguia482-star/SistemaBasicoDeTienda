package org.example.CapaDeModelos.CarpetaProducto;

import java.util.Scanner;

public class Producto {
    Scanner sc = new Scanner(System.in);
    private String tipo;
    private int idProducto;
    private String nombreProducto;
    private int stockProducto;
    private double precio;

    public Producto(String tipo, int idProducto, String nombreProducto, int stockProducto,double precio){
        this.tipo = tipo;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
        this.precio = precio;
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


    public void vender(int cantidad) {
        this.stockProducto -= cantidad;
    }

    public void reponer(int cantidad){
        stockProducto += cantidad;
    }

    public String toString() {
        return getIdProducto() + "," + getNombreProducto() + "," + getStockProducto() +  "," + getPrecio();
    }

    public void mostrarInfoProducto(){
        System.out.println("Id: " + getIdProducto() + " | Nombre: " + getNombreProducto() +
                " | Stock: " + getStockProducto() + " | Precio: " + getPrecio());
    }
}

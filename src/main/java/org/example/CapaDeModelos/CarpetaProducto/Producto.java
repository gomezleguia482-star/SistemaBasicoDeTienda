package org.example.CapaDeModelos.CarpetaProducto;

import java.util.Scanner;

public class Producto {
    Scanner sc = new Scanner(System.in);
    private int idProducto;
    private String nombreProducto;
    private int stockProducto;
    private boolean disponible;

    public Producto(int idProducto, String nombreProducto, int stockProducto){
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stockProducto = stockProducto;
        this.disponible = validarDisponibilidad(stockProducto);
    }

    public boolean validarDisponibilidad(int stockProducto){
        return stockProducto > 0;
    }

    public static boolean stockValido(int stockInicial){
       return stockInicial > -1;
    }



    public int getIdProducto() {return idProducto;}
    public String getNombreProducto() {return nombreProducto;}
    public int getStockProducto() {return stockProducto;}



    public String toString() {
        return getIdProducto() + "," + getNombreProducto() + "," + getStockProducto() + "," + this.disponible;
    }
}

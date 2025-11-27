package org.example.CapaDeModelos.CarpetaArticulo;

import org.example.CapaDeModelos.CarpetaProducto.Producto;

import java.security.PrivateKey;

public class Articulo {
    private  Producto producto;
    private int idProducto;
    private int cantidadSell;

    public Articulo(int IdProducto, int cantidadSell){
        idProducto = IdProducto;
        this.cantidadSell = cantidadSell;
    }

    public int getIdProducto() {return idProducto;}
    public int getCantidadSell() {return cantidadSell;}

    public static boolean validarCantidadSell(int cantidadSell){
        return cantidadSell > 0;
    }

    public String toString() {
        return getIdProducto() + "-" + getCantidadSell();
    }

    public void moatrarInfomacionItems(){
        System.out.println("Id Producto: " + getIdProducto() + " | Unidades compradas: " + getCantidadSell());
    }
}

package org.example.CapaDeModelos.CarpetaArticulo;

import org.example.CapaDeModelos.CarpetaProducto.Producto;

public class Articulo {
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

    @Override
    public String toString() {
        return getIdProducto() + "-" + getCantidadSell();
    }
}

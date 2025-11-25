package org.example.CapaDeModelos.CarpetaProducto;

public class ProductoRopa extends Producto{
    private int talla;
    private String color;

    public ProductoRopa(int idProducto, String nombreProducto, int stockProducto, int talla, String color){
        super(idProducto,nombreProducto,stockProducto);
        this.talla = talla;
        this.color = color;
    }

    public int getTalla() {return talla;}
    public String getColor() {return color;}


    @Override
    public String toString(){
        return  "PR" + super.toString() + "," + getTalla() + "," + getColor();
    }
}

package org.example.CapaDeModelos.CarpetaProducto;

public class ProductoRopa extends Producto {
    private int talla;
    private String color;

    public ProductoRopa(int idProducto, String nombreProducto, int stockProducto,double precio, boolean diponible, int talla, String color) {
        super(idProducto, nombreProducto, stockProducto,precio, diponible);
        this.talla = talla;
        this.color = color;
    }

    public int getTalla() {
        return talla;
    }

    public String getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "PR" + "," + super.toString() + "," + getTalla() + "," + getColor();
    }

    @Override
    public void mostrarInfoProducto() {
        super.mostrarInfoProducto();
        System.out.println("Talla: " + getTalla());
        System.out.println("Color de la prenda: " + getColor());
        System.out.println("---------------------------------------");
    }
}
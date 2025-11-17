package itemsVenta;


import Producto.Producto;

public class Items {
    //Atributos
    private Producto producto;
    private int cantidad;

    //Constructor
    public Items(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    //Getter y setter
    public Producto getProducto(){
        return producto;
    }

    public int getCantidad(){
        return cantidad;
    }

    //Obtener el total de cada producto
    public double getSubTotal(){
        double subTotal = producto.getPrecio() * cantidad;
        return subTotal;
    }
}

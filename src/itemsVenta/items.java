package itemsVenta;


import Producto.Producto;

public class items {
    //Atributos
    private Producto producto;
    private int cantidad;

    //Constructor
    public items(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    //Getter y setter
    public Producto getProducto(){
        return producto;
    }

    public int cantidad(){
        return cantidad;
    }

    //Obtener el total de cada producto
    public double getSubTotal(){
        double subTotal = producto.getPrecio() * cantidad;
        return subTotal;
    }
}

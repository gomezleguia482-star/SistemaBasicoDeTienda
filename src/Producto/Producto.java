package Producto;

public class Producto {
    //Atributos
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private boolean disponible;

    //Constructor
    public Producto(int id, String nombre, double precio, int stock, boolean disponible){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.disponible = disponible;
    }

    //Metodos Acceso a los atributos getter y setter
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public int getStock(){
        return stock;
    }

    public boolean getDisponible(){
        return disponible;
    }

    //Metodos de disminuir y aumentar el stock
    public int vender(int cantidad){
        
        return this.stock = this.stock - cantidad;
    }

    public int reponer(int cantidad){
        this.stock = this.stock + cantidad;
        return stock + cantidad;
    }

    //Agregar los productos al archivo PRODUCTO.TXT
    public String toString(){
        return getId() + "," + getNombre() + "," + getPrecio() + "," + getStock() + "," + getDisponible();
    }

    //Metodo de mostrar la informacion del producto
    //Que sera sobrescrito en las clases hijas
    public void mostrarInfo(){
        System.out.println();
        System.out.println("Id: " + getId() + " | Nombre: " + getNombre() + " | Precio: " + getPrecio() + " | Stock: " + getStock());
        System.out.println("Disponible: " + getDisponible());
    }

}

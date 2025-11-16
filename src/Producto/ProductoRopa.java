package Producto;


public class ProductoRopa extends Producto {
     //Atributos
    int talla;
    String color;

    //Constructor
    public ProductoRopa(int id, String nombre, double precio, int stock, boolean disponible, int talla, String color){
        super(id, nombre, precio, stock, disponible);
        this.talla = talla;
        this.color = color;
    }

    public String toString(){
        return "PR," + super.toString() + "," + talla + "," + color;
    }

    //Sobrescribir metodo de la clase Producto
    @Override
    public void mostrarInfo(){
        super.mostrarInfo();
        System.out.println("Talla: " + talla);
        System.out.println("Color: " + color);
    }

}

package Producto;


public class ProductoRopa extends Producto {
     //Atributos
    private int talla;
    private String color;

    //Constructor
    public ProductoRopa(int id, String nombre, double precio, int stock, boolean disponible, int talla, String color){
        super(id, nombre, precio, stock, disponible);
        this.talla = talla;
        this.color = color;
    }

    // Metodos getter y setter
    public int getTalla(){
        return talla;
    }

    public String getColor(){
        return color;
    }

    public String toString(){
        return "PR," + super.toString() + "," + getTalla() + "," + getColor();
    }

    //Sobrescribir metodo de la clase Producto
    @Override
    public void mostrarInfo(){
        super.mostrarInfo();
        System.out.println("Talla: " + getTalla());
        System.out.println("Color: " + getColor());
    }

}

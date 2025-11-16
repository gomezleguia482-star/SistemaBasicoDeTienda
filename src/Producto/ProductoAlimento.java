package Producto;


public class ProductoAlimento extends Producto {
    //Atributos
    private String fechaVencimiento;

    //Constructor
    public ProductoAlimento(int id, String nombre, double precio, int stock, boolean disponible, String fechaVencimiento){
        super(id, nombre, precio, stock, disponible);
        this.fechaVencimiento = fechaVencimiento;
    }

    //Metodo getter
    public String getFechaVencimiento(){
        return fechaVencimiento;
    }

    @Override
    public String toString(){
        return "PA," + super.toString() + "," + getFechaVencimiento();
    }

    //Sobrescribir metodo de la clase Producto
    @Override
    public void mostrarInfo(){
        super.mostrarInfo();
        System.out.println("Fecha Vencimiento: " + getFechaVencimiento());
    }

}

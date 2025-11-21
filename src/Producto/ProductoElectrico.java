package Producto;


public class ProductoElectrico extends Producto {
     //Atributos
    private int garantiaMeses;

    //Constructor
    public ProductoElectrico(int id, String nombre, double precio, int stock, boolean disponible, int garantiaMeses){
        super(id, nombre, precio, stock, disponible);
        this.garantiaMeses = garantiaMeses;
    }

    //Metodo getter
    public int getGarantiaMeses(){
        return garantiaMeses;
    }

    public String toString(){
        return "PE," + super.toString() + "," + getGarantiaMeses();
    }

    //Sobrescribir metodo de la clase Producto
    @Override
    public void mostrarInfo(){
        super.mostrarInfo();
        System.out.println("Garantia en mese: " +  getGarantiaMeses());
        System.out.println("--------------------------------------\n");
    }
}

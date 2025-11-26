package org.example.CapaDeModelos.CarpetaProducto;

public class ProductoElectronico extends Producto{
    private int garantiaProducto;

    public ProductoElectronico(int idProducto, String nombreProducto, int stockProducto,double precio, boolean disponible, int garantiaProducto){
        super(idProducto,nombreProducto,stockProducto,precio,disponible);
        this.garantiaProducto = garantiaProducto;
    }

    public int getGarantiaProducto() {return garantiaProducto;}


    @Override
    public String toString(){
        return  "PE" + "," + super.toString() + "," + getGarantiaProducto();
    }

    @Override
    public void mostrarInfoProducto(){
        super.mostrarInfoProducto();
        System.out.println("Meses de garantia: " + getGarantiaProducto());
        System.out.println("---------------------------------------------");
    }
}

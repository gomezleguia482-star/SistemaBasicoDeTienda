package org.example.CapaDeModelos.CarpetaProducto;

import java.time.LocalDate;

public class ProductoElectronico extends Producto{
    private int garantiaProducto;

    public ProductoElectronico(int idProducto, String nombreProducto, int stockProducto, int garantiaProducto){
        super(idProducto,nombreProducto,stockProducto);
        this.garantiaProducto = garantiaProducto;
    }

    public int getGarantiaProducto() {return garantiaProducto;}


    @Override
    public String toString(){
        return  "PE" + super.toString() + "," + getGarantiaProducto();
    }
}

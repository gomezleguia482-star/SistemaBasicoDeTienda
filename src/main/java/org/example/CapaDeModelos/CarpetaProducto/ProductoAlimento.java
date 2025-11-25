package org.example.CapaDeModelos.CarpetaProducto;

import java.time.LocalDate;

public class ProductoAlimento extends  Producto {
    private LocalDate fechaVencimiento;

    public ProductoAlimento(int idProducto, String nombreProducto, int stockProducto, LocalDate fechaVencimiento){
        super(idProducto,nombreProducto,stockProducto);
        this.fechaVencimiento = fechaVencimiento;
    }

    public static boolean isValidarFecha(LocalDate fechaVencimiento){
        LocalDate fechaObjetivo = LocalDate.now();

        return  fechaVencimiento.isBefore(fechaObjetivo);
    }

    public LocalDate getFechaVencimiento() {return fechaVencimiento;}


    @Override
    public String toString(){
        return "PA" + super.toString() + "," + getFechaVencimiento();
    }
}

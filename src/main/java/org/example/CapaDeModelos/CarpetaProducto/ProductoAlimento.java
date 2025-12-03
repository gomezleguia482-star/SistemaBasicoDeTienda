package org.example.CapaDeModelos.CarpetaProducto;

import java.time.LocalDate;

public class ProductoAlimento extends  Producto {
    private LocalDate fechaVencimiento;

    public ProductoAlimento(String tipo, int idProducto, String nombreProducto, int stockProducto,double precio, LocalDate fechaVencimiento){
        super(tipo,idProducto,nombreProducto,stockProducto,precio);
        this.fechaVencimiento = fechaVencimiento;
    }

    public static boolean isValidarFecha(LocalDate fechaVencimiento){
        LocalDate fechaObjetivo = LocalDate.now();

        return  fechaVencimiento.isBefore(fechaObjetivo);
    }

    public LocalDate getFechaVencimiento() {return fechaVencimiento;}


    @Override
    public String toString(){
        return "PA" + "," + super.toString() + "," + getFechaVencimiento();
    }

    @Override
    public void mostrarInfoProducto(){
        super.mostrarInfoProducto();
        System.out.println("Fecha vencimiento: " + getFechaVencimiento());
        System.out.println("---------------------------------------------");
    }
}

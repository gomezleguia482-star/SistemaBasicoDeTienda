package org.example.CapaDeModelos.CarpetaVenta;

import org.example.CapaDeModelos.CarpetaArticulo.Articulo;
import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaProducto.Producto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private int idVenta;
    private int idCliente;
    private ArrayList<Articulo> listaArticulos = new ArrayList<>();
    private double totalVenta;
    private LocalDate fechaVenta;

    public Venta(int idVenta, int idCliente, ArrayList<Articulo> listaArticulos, double totalVenta, LocalDate fechaVenta){
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.listaArticulos = listaArticulos;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
    }

    public int getIdVenta() {return idVenta;}
    public int getIdCliente() {return idCliente;}
    public ArrayList<Articulo> getListaArticulos() {return listaArticulos;}
    public double getTotalVenta() {return totalVenta;}
    public LocalDate getFechaVenta() {return fechaVenta;}



    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getIdVenta()).append(","); // Id Venta
        sb.append(getIdCliente()).append(","); // Id Cliente

        for(Articulo A: listaArticulos){
            sb.append(A.getIdProducto()).append("-"); // Id producto
            sb.append(A.getCantidadSell()).append(","); // Unidades vendidas del producto
        }

        sb.append(getTotalVenta()).append(",");
        sb.append(getFechaVenta());

        return sb.toString();
    }


}

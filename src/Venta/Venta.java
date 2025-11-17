package Venta;


import java.util.ArrayList;

import Cliente.Cliente;
import itemsVenta.items;

public class Venta {
    //Atributos
    int idVenta;
    Cliente cliente;
    ArrayList<items> itemsVenta = new ArrayList<>();
    double totalCompra;
    String fecha;

    //Contructor
    public Venta(int idVenta, Cliente cliente, ArrayList<items> itemsVenta,double totalCompra, String fecha){
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.itemsVenta = itemsVenta;
        this.totalCompra = totalCompra;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<items> getItemsVenta() {
        return itemsVenta;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public String getFecha() {
        return fecha;
    }


    //Agregar Productos comprados a la venta
    public void agregarItems(items itm){
        itemsVenta.add(itm);
    }

    public String toString(){
        
    StringBuilder sb = new StringBuilder();

    sb.append(idVenta).append(",");
    sb.append(cliente.getId()).append(",");

    for (int i = 0; i < itemsVenta.size(); i++) {
        items it = itemsVenta.get(i);

        sb.append(it.getProducto().getId())
        .append("-")
        .append(it.cantidad());  // formato: id-cantidad

        if (i < itemsVenta.size() - 1) {
            sb.append(",");  // separar con comas
        }
    }

    sb.append(",").append(totalCompra).append(",");
    sb.append(fecha);

    return sb.toString();
    }

    //Calcular el total de la venta
    public double calcularTotal(ArrayList<items> itemsVenta){
        double total = 0;

        for(items i: itemsVenta){
            total += i.getSubTotal();
        }

        this.totalCompra = total;
        return total;
    }

    
}

package Venta;


import java.util.ArrayList;

import Cliente.Cliente;
import itemsVenta.Items;

public class Venta {
    //Atributos
    private int idVenta;
    private Cliente cliente;
    private ArrayList<Items> itemsVenta = new ArrayList<>();
    private double totalCompra;

    //Contructor
    public Venta(int idVenta, Cliente cliente, ArrayList<Items> itemsVenta,double totalCompra){
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.itemsVenta = itemsVenta;
        this.totalCompra = totalCompra;
    }

    /*Metodos getter y setter*/
    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Items> getItemsVenta() {
        return itemsVenta;
    }

    public double getTotalCompra() {
        return totalCompra;
    }



    //Agregar Productos comprados a la venta
    public void agregarItems(Items itm){
        itemsVenta.add(itm);
    }

    public String toString(){
        
    StringBuilder sb = new StringBuilder();

    sb.append(idVenta).append(",");
    sb.append(cliente.getId()).append(",");

    for (int i = 0; i < itemsVenta.size(); i++) {
        Items it = itemsVenta.get(i);

        sb.append(it.getProducto().getId())
        .append("-")
        .append(it.getCantidad());  // formato: id-cantidad

        if (i < itemsVenta.size() - 1) {
            sb.append(",");  // separar con comas
        }
    }

    sb.append(",").append(totalCompra);

    return sb.toString();
    }
}

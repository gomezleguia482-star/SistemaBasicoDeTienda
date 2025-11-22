package Venta;


import java.util.ArrayList;

import Cliente.Cliente;
import ItemsVenta.Articulos;

public class Venta {
    //Atributos
    private int idVenta;
    private Cliente cliente;
    private ArrayList<Articulos> itemsVenta = new ArrayList<>();
    private double totalCompra;
    private String fechaVenta;

    //Contructor
    public Venta(int idVenta, Cliente cliente, ArrayList<Articulos> itemsVenta, double totalCompra, String fechaVenta){
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.itemsVenta = itemsVenta;
        this.totalCompra = totalCompra;
        this.fechaVenta = fechaVenta;
    }

    /*Metodos getter y setter*/
    public int getIdVenta() {
        return idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Articulos> getItemsVenta() {
        return itemsVenta;
    }

    public double getTotalCompra() {
        return totalCompra;
    }



    //Agregar Productos comprados a la venta
    public void agregarItems(Articulos itm){
        itemsVenta.add(itm);
    }

    public String toString(){
        
    StringBuilder sb = new StringBuilder();

    sb.append(idVenta).append(",");
    sb.append(cliente.getId()).append(",");

    for (int i = 0; i < itemsVenta.size(); i++) {
        Articulos it = itemsVenta.get(i);

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

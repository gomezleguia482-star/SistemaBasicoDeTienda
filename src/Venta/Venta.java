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
    public Venta(int idVenta, Cliente cliente, ArrayList<items> itemsVenta, String fecha){
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.itemsVenta = itemsVenta;
        this.fecha = fecha;
    }

    //Agregar Productos comprados a la venta
    public void agregarItems(items itm){
        itemsVenta.add(itm);
    }

    //Calcular el total de la venta
    public double calcularTotal(){
        double total = 0;

        for(items i: itemsVenta){
            total += i.getSubTotal();
        }

        this.totalCompra = total;
        return total;
    }

    
}

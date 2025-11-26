package org.example;

import org.example.CapaDeServicios.ServicioCliente;
import org.example.CapaDeServicios.ServicioProducto;
import org.example.CapaDeServicios.ServicioVenta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int option = 0;
        do {
            System.out.println("====MENU====");
            System.out.println("1: Agregar Usuario");
            System.out.println("2: Mostrar Usuarios");
            System.out.println("3: Agregar Producto");
            System.out.println("4: Mostrar Productos");
            System.out.println("5: Realizar venta");
            System.out.println("6: Mostrar ventas");
            System.out.println("7: Salir");
            option = sc.nextInt();
            sc.nextLine();

            switch(option){
                case 1 -> ServicioCliente.agregarCliente(sc);
                case 2 -> ServicioCliente.mostrarClientes();
                case 3 -> ServicioProducto.agregarProducto(sc);
                case 4 -> ServicioProducto.mostrarProductos();
                case 5 -> ServicioVenta.realizarVenta(sc);
                case 6 -> ServicioVenta.mostrarVentas();
                case 7 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Option Invalid");
            }
        }while(option != 7);
    }
}
package org.example;

import org.example.CapaDeServicios.ServicioCliente;
import org.example.CapaDeServicios.ServicioProducto;
import org.example.CapaDeServicios.ServicioVenta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try{
            int option = -1;
            do {
                System.out.println("====MENU====");
                System.out.println("1: Agregar Usuario");
                System.out.println("2: Mostrar Usuarios");
                System.out.println("3: Agregar Producto");
                System.out.println("4: Mostrar Productos");
                System.out.println("5: Reponer stock");
                System.out.println("6: Realizar venta");
                System.out.println("7: Mostrar ventas detalladas");
                System.out.println("8: Salir");
                option = sc.nextInt();
                sc.nextLine();

                switch(option){
                    case 1 -> ServicioCliente.agregarCliente(sc);
                    case 2 -> ServicioCliente.mostrarClientes();
                    case 3 -> ServicioProducto.agregarProducto(sc);
                    case 4 -> ServicioProducto.mostrarProductos();
                    case 5 -> ServicioProducto.reponerStock(sc);
                    case 6 -> ServicioVenta.realizarVenta(sc);
                    case 7 -> ServicioVenta.mostrarVentas();
                    case 8 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Option Invalid");
                }
            }while(option != 7);
        }catch(java.util.InputMismatchException e){
            System.out.println("Ingresa solo numeros validos " + e.getCause());
        }
        sc.close();
    }
    //Mostrar en un opcion solo los del producto y las unidades vendidas
}
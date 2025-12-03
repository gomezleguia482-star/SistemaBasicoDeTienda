package org.example.CapaDeServicios;

import org.example.CapaDeModelos.CarpetaProducto.Producto;
import org.example.CapaDeModelos.CarpetaProducto.ProductoAlimento;
import org.example.CapaDeModelos.CarpetaProducto.ProductoElectronico;
import org.example.CapaDeModelos.CarpetaProducto.ProductoRopa;
import org.example.CapaDePersistencia.ManejadorDB;
import org.example.CapaDePersistencia.ProductoDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioProducto {

    public static void agregarProducto(Scanner sc){
        try{
            System.out.println("Escoge una option");
            System.out.println("1: Productos Alimentario");
            System.out.println("2: Prodcuto Electrico");
            System.out.println("3: Producto Ropa");
            int option = sc.nextInt();
            sc.nextLine();

            if(option < 1 || option > 3){
                System.out.println("Option invalida");
                return;
            }

            System.out.println("Ingresa el nombre del producto");
            String nombreProducto = sc.nextLine();

            if(nombreProducto.matches(".*\\d+.*")){
                throw new IllegalArgumentException("Ingresa un nombre valido no debe contener numeros");
            }

            System.out.println("Ingresa las unidades de stock del producto");
            int stockProducto = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingresa el precio del producto");
            double precioProducto = sc.nextDouble();
            sc.nextLine();

            Producto producto = null;
            switch(option){
                case 1:
                    String tipo = "PA";
                    String fecha;
                    LocalDate fechaVencimiento = null;
                    DateTimeFormatter formatoEsperado = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    do {
                        System.out.println("Ingresa la fecha de vencimiento (Ej: yyyy-MM-dd)");
                        fecha = sc.nextLine();

                        try {
                            // 1. Intenta convertir la cadena a LocalDate
                            fechaVencimiento = LocalDate.parse(fecha, formatoEsperado);

                            // Si la conversión es exitosa, el bucle terminará

                        } catch (java.time.format.DateTimeParseException e) {
                            // 2. Si falla (formato incorrecto), atrapa la excepción
                            System.out.println("ERROR. El formato de fecha ingresado es incorrecto.");
                            System.out.println("Por favor, usa el formato yyyy-MM-dd (Ej: 2025-12-31).");
                            fechaVencimiento = null; // Mantiene la variable null para repetir el bucle
                        }
                    } while (fechaVencimiento == null); // Repite mientras la fecha no haya sido parseada con éxito

                    ProductoDAO.agregarDBProducto(tipo, nombreProducto, stockProducto, precioProducto,
                            Date.valueOf(fechaVencimiento), 0 , null, null);
                    break;
                case 2:
                    tipo = "PE";

                    System.out.println("Ingresa el numero de meses de garantia");
                    int mesesGarantia = sc.nextInt();

                    if(mesesGarantia < 0){
                        System.out.println("Ingresa una ganratia valida");
                        return;
                    }

                    ProductoDAO.agregarDBProducto(tipo, nombreProducto, stockProducto, precioProducto,
                            null, mesesGarantia , null, null);
                    break;
                case 3:
                    tipo = "PR";

                    System.out.println("Ingresa la talla de la prenda");
                    String tallaRopa = sc.nextLine();

                    System.out.println("Ingresa el color de la prenda");
                    String colorRopa = sc.nextLine();

                    if(nombreProducto.matches(".*\\d+.*")){
                        throw new IllegalArgumentException("Ingresa un color valido");
                    }

                    ProductoDAO.agregarDBProducto(tipo, nombreProducto, stockProducto, precioProducto,
                            null, 0 , tallaRopa, colorRopa);
                    break;
                default:
                    System.out.println("Tipo de producto Ivalido");
                    break;
            }

        }catch(java.util.InputMismatchException e){
            System.out.println("ERROR. Ingresa un dato numerico");
            sc.nextLine();
            agregarProducto(sc);
        }catch(IllegalArgumentException e){
            System.out.println("ERROR. " + e.getMessage());
        }

    }

    public static void reponerStock(Scanner sc){
        try{
            System.out.println("Ingresa el id del producto");
            int idProducto = sc.nextInt();

            Producto producto = ProductoDAO.buscarProductoId(idProducto);

            if(producto != null){
                System.out.println("Ingresa la cantidad que desea sumar al producto");
                int cantidad = sc.nextInt();

                ProductoDAO.reponerDBStock(idProducto, cantidad);
            }

        }catch(java.util.InputMismatchException e){
            System.out.println("ERROR. Ingresa numeros " + e.getLocalizedMessage());
        }
    }

    public static void mostrarProductos(){
        ArrayList<Producto> listaProductos = ProductoDAO.leerDBProductos();
        for(Producto P: listaProductos){
            P.mostrarInfoProducto();
        }
    }
}

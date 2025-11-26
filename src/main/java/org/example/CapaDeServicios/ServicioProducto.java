package org.example.CapaDeServicios;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaProducto.Producto;
import org.example.CapaDeModelos.CarpetaProducto.ProductoAlimento;
import org.example.CapaDeModelos.CarpetaProducto.ProductoElectronico;
import org.example.CapaDeModelos.CarpetaProducto.ProductoRopa;
import org.example.CapaDePersistencia.ClienteDAO;
import org.example.CapaDePersistencia.ManejadorCSV;
import org.example.CapaDePersistencia.ProductoDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioProducto {

    public static void agregarProducto(Scanner sc){
        try{
            List<String> listaProducto = new ArrayList<>();
            System.out.println("Escoge una option");
            System.out.println("1: Productos Alimentario");
            System.out.println("2: Prodcuto Electrico");
            System.out.println("3: Producto Ropa");
            int option = sc.nextInt();

            if(option < 1 || option > 3){
                System.out.println("Option invalida");
                return;
            }

            System.out.println("Ingresa el id del producto");
            int idProducto = sc.nextInt();
            sc.nextLine();

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

            boolean disponible = Producto.validarDisponibilidad(stockProducto);
            Producto producto = null;
            switch(option){
                case 1:
                    System.out.println("Ingresa la fecha de vencimiento (Ej: 20-10-2030)");
                    String fecha = sc.nextLine();

                    if(fecha.matches("^-?\\\\d+$")){
                        throw new IllegalArgumentException("Ingresa una fecha adecuada");
                    }
                    DateTimeFormatter formatoEsperado = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaVencimiento = LocalDate.parse(fecha, formatoEsperado);

                    producto = new ProductoAlimento(idProducto, nombreProducto, stockProducto,precioProducto,disponible, fechaVencimiento);
                    break;
                case 2:
                    System.out.println("Ingresa el numero de meses de garantia");
                    int mesesGarantia = sc.nextInt();

                    if(mesesGarantia < 0){
                        System.out.println("Ingresa una ganratia valida");
                        return;
                    }

                    producto = new ProductoElectronico(idProducto, nombreProducto, stockProducto,precioProducto,disponible, mesesGarantia);
                    break;
                case 3:
                    System.out.println("Ingresa la talla de la prenda");
                    int tallaRopa = sc.nextInt();

                    System.out.println("Ingresa el color de la prenda");
                    String colorRopa = sc.nextLine();

                    if(nombreProducto.matches(".*\\d+.*")){
                        throw new IllegalArgumentException("Ingresa un color valido");
                    }

                    producto = new ProductoRopa(idProducto, nombreProducto, stockProducto,precioProducto,disponible, tallaRopa, colorRopa);
                    break;
                default:
                    System.out.println("Tipo de producto Ivalido");
                    break;
            }
            if (producto != null){
                listaProducto.add(producto.toString());
            }

            ManejadorCSV.guardarCsv("Resource/Productos.csv", listaProducto);

        }catch(java.util.InputMismatchException e){
            System.out.println("ERROR. Ingresa un dato numerico");
        }catch(IllegalArgumentException e){
            System.out.println("ERROR. " + e.getMessage());
        }

    }
}

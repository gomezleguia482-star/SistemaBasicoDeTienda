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
            sc.nextLine();

            boolean disponible = Producto.validarDisponibilidad(stockProducto);
            Producto producto = null;
            switch(option){
                case 1:
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
                    sc.nextLine();

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
            sc.nextLine();
            agregarProducto(sc);
        }catch(IllegalArgumentException e){
            System.out.println("ERROR. " + e.getMessage());
        }

    }

    public static void reponerStock(Scanner sc){
        ArrayList<Producto> listaProductos = ProductoDAO.cargarProducto();
        try{
            System.out.println("Ingresa el id del producto");
            int idProducto = sc.nextInt();

            for(Producto P: listaProductos){
                if(P.getIdProducto() == idProducto){
                    System.out.println("Ingresa la cantidad que desea reponer del proucto " + P.getNombreProducto());
                    int aumentoStock = sc.nextInt();

                    if(aumentoStock < 1){
                        System.out.println("Cantidad invalida");
                        return;
                    }

                    P.reponer(aumentoStock);

                    if(P.getStockProducto() > 0){
                        P.setDisponible(true);
                    }
                }
            }

            ArrayList<String> productosString = new ArrayList<>();

            for (Producto P: listaProductos){
                productosString.add(P.toString());
            }

            ManejadorCSV.sobreescribirCsv("Resource/Productos.csv", productosString);
            System.out.println("Stock agregado correctamente");

        }catch(java.util.InputMismatchException e){
            System.out.println("ERROR. Ingresa numeros " + e.getLocalizedMessage());
        }
    }

    public static void mostrarProductos(){
        ArrayList<Producto> listaProductos = ProductoDAO.cargarProducto();
        for(Producto P: listaProductos){
            P.mostrarInfoProducto();
        }
    }

    //Agregar la funcionde buscar productos
}

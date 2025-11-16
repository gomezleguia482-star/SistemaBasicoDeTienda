
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Cliente.Cliente;
import Venta.Venta;
import Producto.*;

public class App {
    //LISTAS PRINCIPALES
    ArrayList<Producto> producto = new ArrayList<>();
    ArrayList<Cliente> clientes =  new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // ----- AGREGAR PRODUCTOS -----
    public void agregarProducto(){
        
        try {
            FileWriter fw = new FileWriter("BaseDatos/Producto.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            System.out.println("Elige el producto que desea ingresar");
            System.out.println("1: Producto de Alimento");
            System.out.println("2: Producto electronico");
            System.out.println("3: Producto de ropa");
            int opcion = sc.nextInt();


            System.out.println("Ingresa el id del producto");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingresa el nombre del producto");
            String nombre = sc.nextLine();

            System.out.println("Ingresa el precio de " + nombre.toUpperCase());
            double precio = sc.nextDouble();

            System.out.println("Ingresa la unidades de STOCK del producto");
            int unidades = sc.nextInt();
            sc.nextLine();

            boolean disponible;
            if(unidades <= 0){
                disponible = false; 
            }else{
                disponible = true;
            }

            Producto producto = null;
            switch (opcion) {
                case 1:
                    System.out.println("Ingresa la fecha de vencimiento");
                    String fechaVencimiento = sc.nextLine();

                    producto = new ProductoAlimento(id, nombre, precio, unidades,disponible, fechaVencimiento);
                    break;
                case 2:
                    System.out.println("Ingresa los meses de garantia del producto");
                    int garantia = sc.nextInt();

                    producto = new ProductoElectrico(id, nombre, precio, unidades,disponible, garantia);
                    break;
                case 3:
                    System.out.println("Ingresa la talla del producto");
                    int talla = sc.nextInt();
                     sc.nextLine(); // limpiar buffer

                    System.out.println("Ingresa el color de la ropa");
                    String color = sc.nextLine();

                    producto = new ProductoRopa(id, nombre, precio, unidades,disponible, talla, color);
                    break;
                default:
                    System.out.println("Opcion invalida de productos");
                    break;
            }

            pw.println(producto);
            pw.close();
            bw.close();
            fw.close();

            
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    //Metodo para cargar de la base de datos a el arrayList de productos
    public void llenarProductos() {
    try {
        File archivo = new File("BaseDatos/Producto.txt");
        BufferedReader br = new BufferedReader(new FileReader(archivo));

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] partes = linea.split(",");
            String tipo = partes[0];

            Producto p = null;

            switch (tipo) {

                case "PA":    // Producto Alimenticio
                    p = new ProductoAlimento(
                            Integer.parseInt(partes[1]),
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4]),
                            Boolean.parseBoolean(partes[5]),
                            partes[6]   // ejemplo: fecha vencimiento
                    );
                    break;

                case "PE":    // Producto Electrónico
                    p = new ProductoElectrico(
                            Integer.parseInt(partes[1]),
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4]),
                            Boolean.parseBoolean(partes[5]),
                            Integer.parseInt(partes[6]) // meses de garantia
                    );
                    break;

                case "PR":    // Producto Ropa
                    p = new ProductoRopa(
                            Integer.parseInt(partes[1]),
                            partes[2],
                            Double.parseDouble(partes[3]),
                            Integer.parseInt(partes[4]),
                            Boolean.parseBoolean(partes[5]),
                            Integer.parseInt(partes[6]),
                            partes[7]
                    );
                    break;

                default:
                    System.out.println("Tipo de producto inválido: " + tipo);
                    break;
            }

            if (p != null) {
                producto.add(p);
            }
        }

        br.close();
        System.out.println("Productos cargados correctamente.");

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    //Moatrar todo los producto existentes
    public void mostrarProductos() {
        llenarProductos();

        for(Producto P: producto){
            P.mostrarInfo();
        }
    }

    //Buscar un producto por su ID
    public void buscarProducto() {
        llenarProductos();

        System.out.println("Ingresa el id del producto");
        int id = sc.nextInt();

        boolean encontrado = false;
        for(Producto P: producto){
            if(P.getId() ==id){
                P.mostrarInfo();
                encontrado = true;
                break;
            }
        }

        if(!encontrado){
            System.out.println("Producto no encontrado");
        }
    }

    // ----- CLIENTES -----
    public void agregarCliente() {
        
    }

    public void mostrarClientes() {
        // recorrer lista
    }


    // ----- VENTAS -----
    public void realizarVenta() {
        // seleccionar cliente
        // seleccionar productos
        // crear items
        // crear venta
        // calcular total
        // guardar en lista ventas
        // agregar al historial del cliente
    }

    public void mostrarVentas() {
        // recorrer lista
    }

    public static void main(String[] args) {
        App sistema = new App();

        sistema.agregarProducto();
    }
}

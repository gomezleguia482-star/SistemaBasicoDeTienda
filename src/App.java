
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Cliente.Cliente;
import Venta.Venta;
import itemsVenta.items;
import Producto.*;

public class App {
    //LISTAS PRINCIPALES
    ArrayList<Producto> producto = new ArrayList<>();
    ArrayList<Cliente> clientes =  new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

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

    //Metodo para cargar de la base de datos al arrayList de Clientes
    public void llenarClientes(){
        try {
            File archivo = new File("BaseDatos/Cliente.txt");
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");

                Cliente C = new Cliente(
                Integer.parseInt(partes[0]),  // id
                partes[1],                   // nombre
                partes[2],                   // email
                new ArrayList<>()            // historial vacío
            );

                clientes.add(C);  // AGREGAR A LA LISTA GLOBAL (importantísimo)
            }
            br.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public void llenarVentas(){ File archivo = new File("BaseDatos/Venta.txt");

    if (!archivo.exists()) {
        System.out.println("Venta.txt no existe, se creará al guardar ventas.");
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

        String linea;

        while ((linea = br.readLine()) != null) {

            if (linea.trim().isEmpty()) continue;

            String[] partes = linea.split(";");

            int idVenta = Integer.parseInt(partes[0]);
            int idCliente = Integer.parseInt(partes[1]);

            Cliente cliente = buscarClienteID(idCliente);
            if (cliente == null) continue;

            ArrayList<items> listaItems = new ArrayList<>();

            // --- productos con cantidades ---
            String[] productosInfo = partes[2].split(",");

            for (String info : productosInfo) {
                String[] prodCant = info.split("-");

                int idProducto = Integer.parseInt(prodCant[0]);
                int cantidad = Integer.parseInt(prodCant[1]);

                Producto p = buscarProductoID(idProducto);
                if (p != null) {
                    items nuevo = new items(p, cantidad);
                    listaItems.add(nuevo);
                }
            }

            double total = Double.parseDouble(partes[3]);
            String fecha = partes[4];

            Venta venta = new Venta(idVenta, cliente, listaItems, total, fecha);

            ventas.add(venta);
        }

        } catch (Exception e) {
            System.out.println("Error cargando ventas: " + e.getMessage());
        }
    }

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

    //Mostrar todo los producto existentes
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
        try {
            FileWriter fw = new FileWriter("BaseDatos/Clientes.txt", true);
            BufferedWriter br = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(br);

            System.out.println("Ingresa el id del cliente");
            int id = sc.nextInt();

            System.out.println("Ingresa el nombre completo del cliente");
            String nombre = sc.nextLine();
            sc.nextLine();

            System.out.println("Ingresa el email del cliente");
            String email = sc.nextLine();

            ArrayList<Venta> historial = new ArrayList<>();

            Cliente cliente = new Cliente(id, nombre, email, historial);
            pw.println(cliente.toString());
            pw.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }


    public void mostrarClientes() {
        llenarClientes();

        for(Cliente C: clientes){
            C.mostrarInfo();
        } 
    }


    // ----- VENTAS -----
    public void realizarVenta() {
        // seleccionar cliente
        System.out.print("ID del cliente: ");
        int idCliente = sc.nextInt();

        Cliente cliente = buscarClienteID(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado ❌");
            return;
        }

        ArrayList<items> listaItems = new ArrayList<>();
        String opcion = "si";

        while (opcion.equalsIgnoreCase("si")) {

            System.out.print("Ingresa el ID del producto: ");
            int idProducto = sc.nextInt();

            Producto producto = buscarProductoID(idProducto);

            if (producto == null) {
                System.out.println("Producto no encontrado ❌");
                return;
            }

            System.out.print("Cantidad de " + producto.getNombre() + ": ");
            int cantidad = sc.nextInt();

            if (cantidad <= 0) {
                System.out.println("Cantidad inválida ❌");
                return;
            }

            if (cantidad > producto.getStock()) {
                System.out.println("Stock insuficiente ❌");
                return;
            }

            // agregar item temporal
            items item = new items(producto, cantidad);
            listaItems.add(item);

            // rebajar stock SOLO después de agregar el item
            producto.vender(cantidad);

            System.out.print("¿Desea comprar algo más? (si/no): ");
            opcion = sc.next();  // <-- CORRECTO
        }

        // crear venta
        int idVenta = ventas.size() + 1;
        String fecha = java.time.LocalDate.now().toString();

        double total = 0;
        for (items it : listaItems) {
            total += it.getSubTotal();
        }

        Venta nuevaVenta = new Venta(idVenta, cliente, listaItems, total, fecha);

        System.out.println("Total a pagar: $" + total);

        // guardar en lista memoria
        ventas.add(nuevaVenta);

        // agregar historial al cliente
        cliente.getHistorialCompras().add(nuevaVenta);

        // guardar en archivo
        try {
            FileWriter fw = new FileWriter("BaseDatos/Venta.txt", true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(nuevaVenta.toString());
            pw.close();

            FileReader fr = new FileReader(new File("BaseDatos/Producto"));
            
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        System.out.println("Venta registrada correctamente");
    }


    public Cliente buscarClienteID(int id){
        for(Cliente C: clientes){
            if(C.getId() == id){
                return C;
            }
        }
        return null;
    }

    public Producto buscarProductoID(int id){
        for(Producto P: producto){
            if(P.getId() == id){
                return P;
            }
        }
        return null;
    }

    public void mostrarVentas() {
        if (ventas.isEmpty()) {
        System.out.println("No hay ventas registradas.");
        return;
        }

        for (Venta V : ventas) {
            System.out.println("=====================================");
            System.out.println("ID Venta: " + V.getIdVenta());
            System.out.println("Cliente: " + V.getCliente().getNombre() + " (ID: " + V.getCliente().getId() + ")");
            System.out.println("Fecha: " + V.getFecha());
            System.out.println("Productos comprados:");

            double subtotalTotal = 0;
            for (items it : V.getItemsVenta()) {
                Producto p = it.getProducto();
                int cant = it.cantidad();
                double subtotal = it.getSubTotal();
                subtotalTotal += subtotal;

                System.out.println(" - " + p.getNombre() + " | Cantidad: " + cant + " | Precio unitario: $" + p.getPrecio() + " | Subtotal: $" + subtotal);
            }

            System.out.println("Total de la venta: $" + subtotalTotal);
            System.out.println("=====================================\n");
        }
    }

    public static void main(String[] args) {
    }
}

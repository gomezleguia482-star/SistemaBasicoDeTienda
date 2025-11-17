
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Cliente.Cliente;
import Venta.Venta;
import itemsVenta.Items;
import Producto.*;

public class App {
    //LISTAS PRINCIPALES
    ArrayList<Producto> producto = new ArrayList<>();
    ArrayList<Cliente> clientes =  new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    /*Metodo que Guarda termporalmente durante la ejecucion los producto en en Array de productos*/ 
    public void cargarProductos() {
        try {
            File archivo = new File("BaseDatos/Producto.txt");
            BufferedReader br = new BufferedReader(new FileReader(archivo));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");
                String tipo = partes[0];

                Producto produc = null;

                switch (tipo) {

                    case "PA":    // Producto Alimenticio
                        produc = new ProductoAlimento(
                                Integer.parseInt(partes[1]),
                                partes[2],
                                Double.parseDouble(partes[3]),
                                Integer.parseInt(partes[4]),
                                Boolean.parseBoolean(partes[5]),
                                partes[6] // fecha de vencimiento
                        );
                        break;

                    case "PE":    // Producto Electrónico
                        produc = new ProductoElectrico(
                                Integer.parseInt(partes[1]),
                                partes[2],
                                Double.parseDouble(partes[3]),
                                Integer.parseInt(partes[4]),
                                Boolean.parseBoolean(partes[5]),
                                Integer.parseInt(partes[6]) // meses de garantia
                        );
                        break;

                    case "PR":    // Producto Ropa
                        produc = new ProductoRopa(
                                Integer.parseInt(partes[1]),
                                partes[2],
                                Double.parseDouble(partes[3]),
                                Integer.parseInt(partes[4]),
                                Boolean.parseBoolean(partes[5]),
                                Integer.parseInt(partes[6]), // talla
                                partes[7] // color
                        );
                        break;

                    default:
                        System.out.println("Tipo de producto inválido: " + tipo);
                        break;
                }

                if (produc != null) {
                    producto.add(produc);
                }
            }

            br.close();
            System.out.println("Productos cargados correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*Metodo que Guarda termporalmente durante la ejecucion los clientes en en Array de clientes*/
    public void cargarClientes(){
        try {
            File archivo = new File("BaseDatos/Cliente.txt");
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");

                Cliente cliente = new Cliente(
                Integer.parseInt(partes[0]),  // id
                partes[1],                   // nombre
                partes[2],                   // email
                new ArrayList<>()            // historial vacío
            );
                clientes.add(cliente);  // AGREGAR A LA LISTA GLOBAL (importantísimo)
            }
            br.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }


    /*Metodo que Guarda termporalmente durante la ejecucion los ventas en en Array de ventas*/
    public void cargarVentas(){ 
    File archivo = new File("BaseDatos/Venta.txt");

    // verificamos que exista el archivo, si no existe se crea nuevamente 
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

            ArrayList<Items> listaItems = new ArrayList<>();

            // --- productos con cantidades ---
            String[] productosInfo = partes[2].split(",");

            for (String informacion : productosInfo) {
                String[] prodCant = informacion.split("-");

                int idProducto = Integer.parseInt(prodCant[0]);
                int cantidad = Integer.parseInt(prodCant[1]);

                Producto producto = buscarProductoID(idProducto);
                if (producto != null) {
                    Items nuevo = new Items(producto, cantidad);
                    listaItems.add(nuevo);
                }
            }

            double total = Double.parseDouble(partes[3]);
            String fecha = partes[4];

            Venta venta = new Venta(idVenta, cliente, listaItems, total, fecha);

            ventas.add(venta);
            cliente.getHistorialCompras().add(venta);
        }

        } catch (Exception e) {
            System.out.println("Error cargando ventas: " + e.getMessage());
        }
    }


    /*Metodo que guarda nuevamente los productos
     * en el archivo producto.txt despues de cada
     * venta, con su correspondiente nuevo Stock
     */
    public void guardarProducto(){
        try {
            File archivo = new File("BaseDatos/Producto.txt");
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for(Producto P: producto){
                pw.println(P.toString());
            }

            pw.close();
            bw.close();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }


        /*Metodo que busca los cliente por su id
     * si lo encuentra devuelve el cliente
     * si no devuelve null 
     */
    public Cliente buscarClienteID(int id){
        for(Cliente C: clientes){
            if(C.getId() == id){
                return C;
            }
        }
        return null;
    }


    /*Metodo que buscar los producto por su id
     * si lo encuentra devuelve el producto 
     * si no cevuelve null
     */
    public Producto buscarProductoID(int id){
        for(Producto P: producto){
            if(P.getId() == id){
                return P;
            }
        }
        return null;
    }


    /*Metodos basados solo en los PRODUCTOS
     * agregarProducto, mostrarProductos, buscarProductosPorID
     * AGREGAR PRODUCTO
     */
    public void agregarProducto(){
        
        try {
            FileWriter fw = new FileWriter("BaseDatos/Producto.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            // Se elige que clase se producto se va a agregar
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

            // De acuerdo a la opcion se crea el objeto de producto correspondiente
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

            // Ingresamos el producto al archivo Producto.txt

            pw.println(producto.toString());
            pw.close();
            bw.close();
            fw.close();

            
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }


    //Mostrar los productos con su inforamcion correspondiente
    public void mostrarProductos() {
        // Se cargan los productos al array global de productos
        // y se muestra la informacion correspondiente a cada producto
        cargarProductos();

        for(Producto P: producto){
            P.mostrarInfo();
        }
    }


    //Buscar producto por su ID
    public void buscarProducto() {
        // Se cargan los productos al array global de productos  
        // si es encontrado se muestra su informacion
        cargarProductos();

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


    /*Metodos basados solo en los CLIENTES
     * agregarCliente, mostrarClientes
     * AGREGAR CLIENTE
     */
    public void agregarCliente() {
        try {
            // Se agrega los clientes al archivo Clientes.txt

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


    //Mostrar los clientes con su informacion correspondiente
    public void mostrarClientes() {
        // Se cargan los clientes al array Global de clientes
        // y se muestran  con su informacion correspondiente
        cargarClientes();

        for(Cliente C: clientes){
            C.mostrarInfo();
        } 
    }


    /*Metodos basados solo en las VENTAS
     *realizarVenta, mostrarVentas
     * REALIZAR VENTA
     */
    public void realizarVenta() {

        /* Se busca el cliente por el id si lo encuentra se devuelve el cliente
         * si no se acaba el programa por que devuelve null
         */
        System.out.print("ID del cliente: ");
        int idCliente = sc.nextInt();

        Cliente cliente = buscarClienteID(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado ❌");
            return;
        }


        // Inicializamos un array donde se van a guardar todos lo items de la compra
        // Temporalmente
        ArrayList<Items> listaItems = new ArrayList<>();
        String opcion = "si";

        while (opcion.equalsIgnoreCase("si")) {

            /* Se busca el producto por el id si lo encuentra se devuelve el producto
             * si no se acaba el programa por que devuelve null
             */
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

            // Se crea y de una vez se agrega temporalmente al arrayList
            // De listaItems
            Items item = new Items(producto, cantidad);
            listaItems.add(item);

            // Descontamos el STOCK del poroducto 
            producto.vender(cantidad);

            System.out.print("¿Desea comprar algo más? (si/no): ");
            opcion = sc.next();  // (si / no)
        }

        // Creamos realmente la venta
        int idVenta = ventas.size() + 1;
        String fecha = java.time.LocalDate.now().toString();

        double total = 0.0;
        for (Items items : listaItems) {
            total += items.getSubTotal();
        }

        Venta nuevaVenta = new Venta(idVenta, cliente, listaItems, total, fecha);

        System.out.println("Total a pagar: $" + total);

        // Guardar temporalmente en el arrayList ventas global
        ventas.add(nuevaVenta);

        // Se agrega la venta al historial de compras al cliente correspondiente
        cliente.getHistorialCompras().add(nuevaVenta);

        // Guarda la venta en el archivo Ventas.txt
        // Guarda nuevamente los productos depues de cada Venta con la nueva cantidad
        try {
            FileWriter fw = new FileWriter("BaseDatos/Venta.txt", true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(nuevaVenta.toString());

            // Guardar nuevamente los productos en el archivo con el metodo de guardar productos
            guardarProducto();
            pw.close();
            
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        System.out.println("Venta registrada correctamente");
    }


    //Muestra las ventas 
    public void mostrarVentas() {

        // Verificamos qu el array no este vacio
        if (ventas.isEmpty()) {
        System.out.println("No hay ventas registradas.");
        return;
        }

        //Recorremos las ventas y mostramos la informacion correspondiente
        for (Venta V : ventas) {
            System.out.println("=====================================");
            System.out.println("ID Venta: " + V.getIdVenta());
            System.out.println("Cliente: " + V.getCliente().getNombre() + " (ID: " + V.getCliente().getId() + ")");
            System.out.println("Fecha: " + V.getFecha());
            System.out.println("Productos comprados:");

            // Recorremos el array de los items y toda la informacion del producto
            // Y se mustra la informacion correspondiente a cada producto comprado
            double totalVenta = 0;
            for (Items items : V.getItemsVenta()) {
                Producto producto = items.getProducto();
                int cantidad = items.getCantidad();
                double subtotal = items.getSubTotal();
                totalVenta += subtotal;

                System.out.println(" - " + producto.getNombre() + 
                " | Cantidad: " + cantidad + 
                " | Precio unitario: $" + producto.getPrecio() + 
                " | Subtotal: $" + subtotal);
            }

            System.out.println("Total de la venta: $" + totalVenta) ;
            System.out.println("=====================================\n");
        }
    }

}

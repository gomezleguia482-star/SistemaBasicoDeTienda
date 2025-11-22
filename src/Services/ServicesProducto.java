    package Services;

    import Producto.*;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.nio.file.StandardOpenOption;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Scanner;

    public class ServicesProducto {
        public ArrayList<Producto> productos = new ArrayList<>();

        public void agregarProductos(Scanner sc){
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
            String linea = producto.toString();
            productos.add(producto);

            try {
                Files.write(Paths.get("SistemaTienda/BaseDatos/Producto.txt"), Collections.singleton(linea), StandardOpenOption.APPEND);
            }catch(IOException e){
                e.getMessage();
            }
        }

        /// ///////////////////////////

        public void mostrarProductos() {
            for(Producto P: productos){
                P.mostrarInfo();
            }
        }

        ///////////////////////////////////

        public void buscarProductos(Scanner sc){
            System.out.println("Ingresa el id del producto");
            int id = sc.nextInt();

            boolean encontrado = false;
            for(Producto P: productos){
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

        ///  ///////////////////////////

        public Producto buscarProductoID(int id){
            for(Producto P: productos){
                if(P.getId() == id){
                    return P;
                }
            }
            return null;
        }
    }

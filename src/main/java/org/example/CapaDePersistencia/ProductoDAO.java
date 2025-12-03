package org.example.CapaDePersistencia;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;
import org.example.CapaDeModelos.CarpetaProducto.Producto;
import org.example.CapaDeModelos.CarpetaProducto.ProductoAlimento;
import org.example.CapaDeModelos.CarpetaProducto.ProductoElectronico;
import org.example.CapaDeModelos.CarpetaProducto.ProductoRopa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {


    public static ArrayList<Producto> leerDBProductos(){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        try(Connection connection = ManejadorDB.leerDB();

            Statement statement = connection.createStatement()

        ){

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM productos"
            );

            while (resultSet.next()){
                Producto producto = null;

                String tipoProducto = resultSet.getString("tipo_producto");
                int id = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre_producto");
                int stock = resultSet.getInt("stock_producto");
                double precio = resultSet.getDouble("precio_producto");

                switch (tipoProducto){
                    case "PA":
                        java.sql.Date fecha = resultSet.getDate("fecha_vencimiento");
                        LocalDate fechaVencimiento = fecha.toLocalDate();

                        producto = new ProductoAlimento(tipoProducto, id, nombre, stock, precio,fechaVencimiento);
                        break;
                    case "PE":
                        int garantia = resultSet.getInt("meses_garantia");

                        producto = new ProductoElectronico(tipoProducto, id, nombre, stock, precio, garantia);
                        break;
                    case "PR":
                        String color = resultSet.getString("color");
                        String talla = resultSet.getString("talla");

                        producto = new ProductoRopa(tipoProducto, id, nombre, stock, precio, talla, color);
                        break;
                    default:
                        System.out.println("Type product Invalid");
                        break;
                }
                listaProductos.add(producto);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaProductos;
    }

    public static void agregarDBProducto(String tipo, String nombre, int stock, double precio, java.sql.Date fecha,
                                         int garantia, String talla, String color){

        try(Connection connection = ManejadorDB.leerDB();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO productos (tipo_producto, nombre_producto, stock_producto, precio_producto," +
                            "fecha_vencimiento, meses_garantia, color, talla) VALUES (?,?,?,?,?,?,?,?)"
            )
        ){
            preparedStatement.setString(1, tipo);
            preparedStatement.setString(2, nombre);
            preparedStatement.setInt(3, stock);
            preparedStatement.setDouble(4, precio);

            // --- MANEJO DE NULL para la FECHA (java.sql.Date es un Objeto, acepta null) ---
            if (fecha != null) {
                preparedStatement.setDate(5, fecha);
            } else {
                preparedStatement.setNull(5, java.sql.Types.DATE); // Tipificamos el NULL como DATE
            }

            // --- MANEJO DE NULL para la GARANTIA (Debe ser Integer, no int) ---
            if (garantia != 0) {
                preparedStatement.setInt(6, garantia);
            } else {
                preparedStatement.setNull(6, java.sql.Types.INTEGER); // Tipificamos el NULL como INTEGER
            }

            // --- MANEJO de NULL para CADENAS (String es un Objeto, acepta null) ---
            // Si talla y color son null, setString ya los maneja bien, pero setNull es más explícito
            if (talla != null) {
                preparedStatement.setString(7, talla);
            } else {
                preparedStatement.setNull(7, java.sql.Types.VARCHAR);
            }

            if (color != null) {
                preparedStatement.setString(8, color);
            } else {
                preparedStatement.setNull(8, java.sql.Types.VARCHAR);
            }


            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                System.out.println("Producto agregado correctamente");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }


    public static Producto buscarProductoId(int idBuscar){

        Producto producto = null;
        try(Connection connection = ManejadorDB.leerDB();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM productos WHERE id_producto = ?"
            )

        ){

            preparedStatement.setInt(1,idBuscar);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){

                    String tipoProducto = resultSet.getString("tipo_producto");
                    int id = resultSet.getInt("id_producto");
                    String nombre = resultSet.getString("nombre_producto");
                    int stock = resultSet.getInt("stock_producto");
                    double precio = resultSet.getDouble("precio_producto");

                    switch (tipoProducto){
                        case "PA":
                            java.sql.Date fecha = resultSet.getDate("fecha_vencimiento");
                            LocalDate fechaVencimiento = fecha.toLocalDate();

                            producto = new ProductoAlimento(tipoProducto, id, nombre, stock, precio,fechaVencimiento);
                            break;
                        case "PE":
                            int garantia = resultSet.getInt("meses_garantia");

                            producto = new ProductoElectronico(tipoProducto, id, nombre, stock, precio, garantia);
                            break;
                        case "PR":
                            String color = resultSet.getString("color");
                            String talla = resultSet.getString("talla");

                            producto = new ProductoRopa(tipoProducto, id, nombre, stock, precio, talla, color);
                            break;
                        default:
                            System.out.println("Type product Invalid");
                            break;
                    }
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return producto;
    }


    public static void reponerDBStock(int id, int stock){

        try (Connection connection = ManejadorDB.leerDB();

             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE productos SET stock_producto = stock_producto + ? WHERE id_producto = ?"
             )
        ){

            preparedStatement.setInt(1,stock);
            preparedStatement.setInt(2,id);


            int filasAfectadas = preparedStatement.executeUpdate();

            if(filasAfectadas > 0){
                System.out.println("Actualizaccion del stock exitosa");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

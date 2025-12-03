package org.example.CapaDePersistencia;

import org.example.CapaDeModelos.CarpetaCliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {


    // Lectura de las filas de la tabla clientes
    public static ArrayList<Cliente> leerDBClientes(){
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try(Connection connection = ManejadorDB.leerDB();

            Statement statement = connection.createStatement()
        ){

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM clientes"
            );

            while (resultSet.next()){

                int id = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre_cliente");
                String email = resultSet.getString("email_cliente");


                Cliente cliente = new Cliente(id, nombre, email);
                listaClientes.add(cliente);
            }

        }catch(SQLException e){
            System.out.println("Error en la lectura de la base de datos");
            e.printStackTrace();
        }
        return listaClientes;
    }


    public static void agregarDBClientes(String nombre, String email){

        try(Connection connection = ManejadorDB.leerDB();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO clientes (nombre_cliente, email_cliente) VALUES (?,?)"
            )
        ){

            preparedStatement.setString(1,nombre);
            preparedStatement.setString(2, email);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                System.out.println("Cliente agregado correctamente");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public static Cliente buscarClienteId(int idBuscar){

        Cliente cliente = null;
        try(Connection connection = ManejadorDB.leerDB();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM clientes WHERE id_cliente = ?"
            )

        ){

            preparedStatement.setInt(1,idBuscar);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

                if (resultSet.next()){

                    int id = resultSet.getInt("id_cliente");
                    String nombre = resultSet.getString("nombre_cliente");
                    String email = resultSet.getString("email_cliente");

                    cliente = new Cliente(id, nombre, email);
                }
            }



        }catch(SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }


}

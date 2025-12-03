package org.example.CapaDePersistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorDB {

    private static final String URL = "jdbc:mysql://localhost:3306/sistematienda";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";


    // Lectura de las filas de la tabla clientes
    public static Connection leerDB(){

        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }



    public static List<String> leerCsv(String nombreArchivo){
        List<String> lineas = new ArrayList<>();

        try{
            lineas = Files.readAllLines(Paths.get(nombreArchivo));
        }catch(IOException e){
            System.out.println("ERROR. Lectura del archivo con nombre:  " + nombreArchivo + e.getLocalizedMessage());
        }
        return lineas;
    }

    public static void guardarCsv(String nombreArchivo, List<String> datos){
        try{
            Files.write(Paths.get(nombreArchivo),datos, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        }catch(IOException e){
            System.out.println("ERROR. Guardar los datos " + e.getMessage());
        }
    }

    public static void sobreescribirCsv(String nombreArchivo, List<String> datos){
        try{
            //Sin StandardOpenOption.APPEND, el archivo se SOBRESCRIBE.
            Files.write(Paths.get(nombreArchivo), datos);

            // También es buena práctica incluir StandardOpenOption.CREATE
            // Files.write(Paths.get(nombreArchivo), datos, StandardOpenOption.CREATE);

        }catch(IOException e){
            System.out.println("ERROR. Al sobreescribir los datos: " + e.getMessage());
        }
    }
}

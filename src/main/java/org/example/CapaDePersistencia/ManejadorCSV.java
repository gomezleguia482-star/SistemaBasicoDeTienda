package org.example.CapaDePersistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ManejadorCSV {

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

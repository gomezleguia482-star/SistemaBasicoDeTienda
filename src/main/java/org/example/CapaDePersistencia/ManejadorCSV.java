package org.example.CapaDePersistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ManejadorCSV {

    public static List<String> leerCsv(String nombreArchivo){
        List<String> lineas = new ArrayList<>();

        try{
            lineas = Files.readAllLines(Paths.get(nombreArchivo));

            if(lineas == null){
                System.out.println("ERROR. Archivo no encontrado");
                return lineas;
            }
        }catch(IOException e){
            System.out.println("ERROR. Lectura del archivo con nombre:  " + nombreArchivo + e.getLocalizedMessage());
        }
        return lineas;
    }

    public static void guardarCsv(String nombreArchivo, List<String> datos){
        try{
            Files.write(Paths.get(nombreArchivo),datos);
        }catch(IOException e){
            System.out.println("ERROR. Guardar los datos");
        }
    }
}

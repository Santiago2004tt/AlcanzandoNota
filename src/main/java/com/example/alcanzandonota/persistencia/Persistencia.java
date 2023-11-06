package com.example.alcanzandonota.persistencia;

import com.example.alcanzandonota.application.AppInstitucion;
import com.example.alcanzandonota.model.Institucion;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Persistencia {
    public static final String RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO = obtenerRuta("Model.dat");
    public static final String RUTA_ARCHIVO_MODELO_PANADERIA_XML = obtenerRuta("Encript.xml");

//    public static final String RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO = ("C:\\td/Model.dat");
//    public static final String RUTA_ARCHIVO_MODELO_PANADERIA_XML = ("C:\\td/Encript.xml");


    private static String obtenerRuta(String ruta) {

        String jarPath = null;
        try {
            jarPath = AppInstitucion.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // Directorio donde se encuentra el JAR
        String jarDirectory = new File(jarPath).getParent();

        // Nombre del archivo que deseas guardar
        String archivoNombre = ruta;

        // Ruta completa del archivo a guardar
        String rutaArchivo = jarDirectory + File.separator + archivoNombre;

        return rutaArchivo;
    }


    public static Institucion cargarRecursoXML() {

        Institucion institucion = null;

        try {
            institucion = (Institucion) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PANADERIA_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return institucion;

    }

    public static void guardarRecursoXML(Institucion institucion) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PANADERIA_XML, institucion);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Institucion cargarRecursoBinario() {

        Institucion institucion = null;

        try {
            institucion = (Institucion) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return institucion;
    }

    public static void guardarRecursoBinario(Institucion institucion) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_PANADERIA_BINARIO, institucion);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

package com.consultorio.util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GestorArchivos {
    private static final String DIR_DB = "db";
    private static final String ARCHIVO_ADMINS = "db/administradores.csv";
    private static final String ARCHIVO_DOCTORES = "db/doctores.csv";
    private static final String ARCHIVO_PACIENTES = "db/pacientes.csv";
    private static final String ARCHIVO_CITAS = "db/citas.csv";

    public static void inicializarArchivos() {
        try {
            File carpeta = new File(DIR_DB);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            verificarYCrearArchivo(ARCHIVO_ADMINS, "admin,admin123\n");
            verificarYCrearArchivo(ARCHIVO_DOCTORES, "");
            verificarYCrearArchivo(ARCHIVO_PACIENTES, "");
            verificarYCrearArchivo(ARCHIVO_CITAS, "");
        } catch (IOException e) {
            System.err.println("Error al inicializar la persistencia: " + e.getMessage());
        }
    }

    private static void verificarYCrearArchivo(String ruta, String contenidoInicial) throws IOException {
        File file = new File(ruta);
        if (!file.exists()) {
            file.createNewFile();
            if (!contenidoInicial.isEmpty()) {
                Files.writeString(Paths.get(ruta), contenidoInicial);
            }
        }
    }

    public static List<String> leerLineas(String ruta) {
        try {
            File file = new File(ruta);
            if (!file.exists()) return new ArrayList<>();
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.err.println("Error leyendo " + ruta + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void agregarLinea(String ruta, String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error escribiendo en " + ruta + ": " + e.getMessage());
        }
    }

    public static String getRutaAdmins() { return ARCHIVO_ADMINS; }
    public static String getRutaDoctores() { return ARCHIVO_DOCTORES; }
    public static String getRutaPacientes() { return ARCHIVO_PACIENTES; }
    public static String getRutaCitas() { return ARCHIVO_CITAS; }
}
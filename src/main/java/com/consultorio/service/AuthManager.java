package com.consultorio.service;

import com.consultorio.util.GestorArchivos;
import java.util.List;

public class AuthManager {
    public static boolean autenticar(String usuario, String contrasena) {
        List<String> lineas = GestorArchivos.leerLineas(GestorArchivos.getRutaAdmins());
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length >= 2) {
                if (partes[0].trim().equals(usuario.trim()) && partes[1].trim().equals(contrasena.trim())) {
                    return true;
                }
            }
        }
        return false;
    }
}
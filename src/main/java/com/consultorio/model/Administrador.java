package com.consultorio.model;

public class Administrador {
    private String usuario;
    private String contrasena;

    public Administrador(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }

    public String toCSV() {
        return usuario + "," + contrasena;
    }

    public static Administrador fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 2) return null;
        return new Administrador(partes[0].trim(), partes[1].trim());
    }
}
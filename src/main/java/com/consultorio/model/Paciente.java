package com.consultorio.model;

public class Paciente {
    private String id;
    private String nombre;

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public String toCSV() {
        return id + "," + nombre;
    }

    public static Paciente fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 2) return null;
        return new Paciente(partes[0].trim(), partes[1].trim());
    }

    @Override
    public String toString() {
        return String.format("[ID: %s] Paciente: %s", id, nombre);
    }
}
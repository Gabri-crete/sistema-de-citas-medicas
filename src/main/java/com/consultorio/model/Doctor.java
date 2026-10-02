package com.consultorio.model;

public class Doctor {
    private String id;
    private String nombre;
    private String especialidad;

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    public String toCSV() {
        return id + "," + nombre + "," + especialidad;
    }

    public static Doctor fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 3) return null;
        return new Doctor(partes[0].trim(), partes[1].trim(), partes[2].trim());
    }

    @Override
    public String toString() {
        return String.format("[ID: %s] Dr(a). %-25s | Especialidad: %s", id, nombre, especialidad);
    }
}
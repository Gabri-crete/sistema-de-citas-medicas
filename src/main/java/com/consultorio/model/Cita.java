package com.consultorio.model;

public class Cita {
    private String id;
    private String fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String id, String fechaHora, String motivo, Doctor doctor, Paciente paciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getId() { return id; }
    public String getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public Doctor getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }

    public String toCSV() {
        return id + "," + fechaHora + "," + motivo + "," + doctor.getId() + "," + paciente.getId();
    }

    @Override
    public String toString() {
        return "========================================================\n" +
               "  CITA ID: " + id + "\n" +
               "  Fecha y Hora: " + fechaHora + "\n" +
               "  Motivo:       " + motivo + "\n" +
               "  Doctor:       " + doctor.getNombre() + " (" + doctor.getEspecialidad() + ")\n" +
               "  Paciente:     " + paciente.getNombre() + "\n" +
               "========================================================";
    }
}
package com.consultorio.service;

import com.consultorio.model.*;
import com.consultorio.util.GestorArchivos;
import java.util.*;

public class CitasManager {
    private Map<String, Doctor> doctores = new HashMap<>();
    private Map<String, Paciente> pacientes = new HashMap<>();
    private List<Cita> citas = new ArrayList<>();

    public CitasManager() {
        cargarDatos();
    }

    public void cargarDatos() {
        doctores.clear();
        pacientes.clear();
        citas.clear();

        for (String linea : GestorArchivos.leerLineas(GestorArchivos.getRutaDoctores())) {
            Doctor doc = Doctor.fromCSV(linea);
            if (doc != null) doctores.put(doc.getId(), doc);
        }

        for (String linea : GestorArchivos.leerLineas(GestorArchivos.getRutaPacientes())) {
            Paciente pac = Paciente.fromCSV(linea);
            if (pac != null) pacientes.put(pac.getId(), pac);
        }

        for (String linea : GestorArchivos.leerLineas(GestorArchivos.getRutaCitas())) {
            String[] p = linea.split(",");
            if (p.length >= 5) {
                Doctor doc = doctores.get(p[3].trim());
                Paciente pac = pacientes.get(p[4].trim());
                if (doc != null && pac != null) {
                    citas.add(new Cita(p[0].trim(), p[1].trim(), p[2].trim(), doc, pac));
                }
            }
        }
    }

    public boolean existeDoctor(String id) {
        return doctores.containsKey(id);
    }

    public void registrarDoctor(Doctor doctor) {
        doctores.put(doctor.getId(), doctor);
        GestorArchivos.agregarLinea(GestorArchivos.getRutaDoctores(), doctor.toCSV());
    }

    public boolean existePaciente(String id) {
        return pacientes.containsKey(id);
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.put(paciente.getId(), paciente);
        GestorArchivos.agregarLinea(GestorArchivos.getRutaPacientes(), paciente.toCSV());
    }

    public boolean agendarCita(String id, String fechaHora, String motivo, String doctorId, String pacienteId) {
        Doctor doc = doctores.get(doctorId);
        Paciente pac = pacientes.get(pacienteId);

        if (doc == null || pac == null) {
            return false;
        }

        Cita nuevaCita = new Cita(id, fechaHora, motivo, doc, pac);
        citas.add(nuevaCita);
        GestorArchivos.agregarLinea(GestorArchivos.getRutaCitas(), nuevaCita.toCSV());
        return true;
    }

    public Collection<Doctor> getDoctores() { return doctores.values(); }
    public Collection<Paciente> getPacientes() { return pacientes.values(); }
    public List<Cita> getCitas() { return citas; }
}
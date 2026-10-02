package com.consultorio;

import com.consultorio.model.*;
import com.consultorio.service.*;
import com.consultorio.util.GestorArchivos;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CitasManager manager;

    public static void main(String[] args) {
        GestorArchivos.inicializarArchivos();
        manager = new CitasManager();

        System.out.println("==================================================");
        System.out.println("   SISTEMA DE ADMINISTRACION DE CITAS CLINICAS    ");
        System.out.println("==================================================");

        if (!iniciarSesion()) {
            System.out.println("\nNumero maximo de intentos alcanzado. Fin del programa.");
            return;
        }

        desplegarMenu();
    }

    private static boolean iniciarSesion() {
        int intentos = 3;
        System.out.println("\n--- CONTROL DE ACCESO (ADMINISTRADOR) ---");
        while (intentos > 0) {
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine().trim();
            System.out.print("Contrasena: ");
            String contrasena = scanner.nextLine().trim();

            if (AuthManager.autenticar(usuario, contrasena)) {
                System.out.println("\n>> Autenticacion satisfactoria. Bienvenido al sistema.");
                return true;
            } else {
                intentos--;
                System.out.println(">> Credenciales no validas. Intentos restantes: " + intentos + "\n");
            }
        }
        return false;
    }

    private static void desplegarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n================ MENU PRINCIPAL ================");
            System.out.println("1. Dar de alta doctor");
            System.out.println("2. Dar de alta paciente");
            System.out.println("3. Crear y asignar cita");
            System.out.println("4. Ver lista de doctores");
            System.out.println("5. Ver lista de pacientes");
            System.out.println("6. Ver citas agendadas");
            System.out.println("0. Cerrar sesion y salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1: altaDoctor(); break;
                case 2: altaPaciente(); break;
                case 3: crearCita(); break;
                case 4: listarDoctores(); break;
                case 5: listarPacientes(); break;
                case 6: listarCitas(); break;
                case 0: System.out.println("\nCerrando sesion de administrador..."); break;
                default: System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
    }

    private static void altaDoctor() {
        System.out.println("\n--- ALTA DE DOCTOR ---");
        System.out.print("ID del Doctor (ej. DOC01): ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("El ID no puede estar vacio.");
            return;
        }

        if (manager.existeDoctor(id)) {
            System.out.println("Ya existe un doctor con ese ID.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine().trim();

        manager.registrarDoctor(new Doctor(id, nombre, especialidad));
        System.out.println(">> Doctor registrado y guardado exitosamente.");
    }

    private static void altaPaciente() {
        System.out.println("\n--- ALTA DE PACIENTE ---");
        System.out.print("ID del Paciente (ej. PAC01): ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("El ID no puede estar vacio.");
            return;
        }

        if (manager.existePaciente(id)) {
            System.out.println("Ya existe un paciente con ese ID.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        manager.registrarPaciente(new Paciente(id, nombre));
        System.out.println(">> Paciente registrado y guardado exitosamente.");
    }

    private static void crearCita() {
        System.out.println("\n--- CREAR Y ASIGNAR CITA ---");
        System.out.print("ID de la Cita (ej. C001): ");
        String id = scanner.nextLine().trim();
        System.out.print("Fecha y Hora (ej. 2026-10-15 11:30): ");
        String fechaHora = scanner.nextLine().trim();
        System.out.print("Motivo de la consulta: ");
        String motivo = scanner.nextLine().trim();
        System.out.print("ID del Doctor a asignar: ");
        String docId = scanner.nextLine().trim();
        System.out.print("ID del Paciente: ");
        String pacId = scanner.nextLine().trim();

        boolean exito = manager.agendarCita(id, fechaHora, motivo, docId, pacId);
        if (exito) {
            System.out.println(">> Cita creada y vinculada correctamente.");
        } else {
            System.out.println(">> Error: Verifica que el ID del Doctor y del Paciente existan previamente en el catalogo.");
        }
    }

    private static void listarDoctores() {
        System.out.println("\n--- CATALOGO DE DOCTORES ---");
        if (manager.getDoctores().isEmpty()) {
            System.out.println("No hay doctores registrados.");
        } else {
            for (Doctor d : manager.getDoctores()) {
                System.out.println(d);
            }
        }
    }

    private static void listarPacientes() {
        System.out.println("\n--- CATALOGO DE PACIENTES ---");
        if (manager.getPacientes().isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            for (Paciente p : manager.getPacientes()) {
                System.out.println(p);
            }
        }
    }

    private static void listarCitas() {
        System.out.println("\n--- REGISTRO DE CITAS ASIGNADAS ---");
        if (manager.getCitas().isEmpty()) {
            System.out.println("No hay citas registradas en el sistema.");
        } else {
            for (Cita c : manager.getCitas()) {
                System.out.println(c);
            }
        }
    }
}
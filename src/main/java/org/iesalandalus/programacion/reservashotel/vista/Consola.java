package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Regimen;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalTime;


public class Consola {


    private Consola() {
    }

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.print("Elige una opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Opcion.values().length);
        return Opcion.values()[opcion];
    }

    public static Huesped leerHuesped() {
        System.out.println("Introduce los datos del huésped:");

        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();

        System.out.print("DNI: ");
        String dni = Entrada.cadena();

        System.out.println("Telefono: ");
        String telefono = Entrada.cadena();

        System.out.println("Correo electronico: ");
        String correo = Entrada.cadena();

        System.out.println("Fecha de nacimiento (formato " + Huesped.FORMATO_FECHA + "): ");
        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());


        // Crear una instancia de Huesped con los datos leídos
        try {
            return new Huesped(nombre, dni, telefono, correo, fechaNacimiento);
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepción
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public static Huesped leerClientePorDni() {
        System.out.println("Introduce el DNI del huésped:");

        try {
            System.out.print("DNI: ");
            String dni = Entrada.cadena();

            // Solicitar y leer el nombre ficticio desde la consola
            System.out.print("Nombre ficticio: ");
            String nombreFicticio = Entrada.cadena();

            Huesped huesped = new Huesped(nombreFicticio, dni, "correo@ejemplo.com", "666666666", LocalDate.of(2000, 1, 1));

            return huesped;
        } catch (IllegalArgumentException e) {
            // En caso de DNI incorrecto, propagar la excepción
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;

        do {
            try {
                System.out.print(mensaje);
                String fechaStr = Entrada.cadena();
                fecha = LocalDate.parse(Huesped.FORMATO_FECHA);
                fechaValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Formato de fecha incorrecto. Introduce la fecha en formato (dd/MM/yyyy)");
            }
        } while (!fechaValida);

        return fecha;
    }


    public static Habitacion leerHabitacion() {
        System.out.println("Introduce los datos de la habitación:");

        System.out.print("Planta: ");
        int planta = Entrada.entero();

        System.out.print("Puerta: ");
        int puerta = Entrada.entero();

        System.out.print("Precio: ");
        double precio = Entrada.real();

        TipoHabitacion tipoHabitacion = leerTipoHabitacion();

        // Crear una instancia de Habitacion con los datos leídos
        try {
            return new Habitacion(planta, puerta, precio, tipoHabitacion);
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepción
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    public static Habitacion leerHabitacionPorIdentificador() {
        System.out.println("Introduce el número de planta y puerta de la habitación:");

        System.out.print("Número de planta: ");
        int planta = Entrada.entero();

        System.out.print("Número de puerta: ");
        int puerta = Entrada.entero();

        // Crear una instancia de Habitacion ficticia con los datos leídos
        try {
            return new Habitacion(planta, puerta, 80.00, TipoHabitacion.SIMPLE);
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepción
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }


    public static TipoHabitacion leerTipoHabitacion() {
        TipoHabitacion tipoHabitacion = null;
        boolean tipoValido = false;

        do {
            System.out.println("Elija el tipo de habitación:");

            // Iterar sobre los valores del Enum TipoHabitacion
            for (TipoHabitacion tipo : TipoHabitacion.values()) {
                System.out.println((tipo.ordinal() + 1) + ". " + tipo);
            }

            int opcion = Entrada.entero();

            // Verificar si la opción seleccionada es válida
            if (opcion > 0 && opcion <= TipoHabitacion.values().length) {
                tipoHabitacion = TipoHabitacion.values()[opcion - 1];
                tipoValido = true;
            } else {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!tipoValido);

        return tipoHabitacion;
    }


    public static Regimen leerRegimen() {
        Regimen regimen = null;
        boolean regimenValido = false;

        do {
            System.out.println("Elija el régimen de la reserva:");

            // Iterar sobre los valores del Enum Regimen
            for (Regimen tipo : Regimen.values()) {
                System.out.println((tipo.ordinal() + 1) + ". " + tipo);
            }

            int opcion = Entrada.entero();

            // Verificar si la opción seleccionada es válida
            if (opcion > 0 && opcion <= Regimen.values().length) {
                regimen = Regimen.values()[opcion - 1];
                regimenValido = true;
            } else {
                System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (!regimenValido);

        return regimen;
    }


    public static Reserva leerReserva() {
        System.out.println("Introduce los datos de la reserva:");

        // Obtener datos para la reserva
        Habitacion habitacion = leerHabitacionPorIdentificador();
        LocalDate fechaInicio = leerFecha("Fecha de inicio (dd/MM/yyyy): ");
        LocalDate fechaFin = leerFecha("Fecha de fin (dd/MM/yyyy): ");
        Huesped huesped = leerHuesped();
        Regimen regimen = leerRegimen();

        // Crear una instancia de Reserva con los datos leídos
        try {
            Reserva reserva = new Reserva(habitacion, huesped, fechaInicio, fechaFin, 0, regimen);

            return reserva;
        } catch (IllegalArgumentException e) {
            // En caso de datos incorrectos, propagar la excepción
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }
}

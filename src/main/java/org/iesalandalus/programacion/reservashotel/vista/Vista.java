package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.reservashotel.modelo.controlador.Controlador;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vista {



    public static final int CAPACIDAD = 100;
    private Huesped[] huespedes;
    private Habitacion[] habitaciones;
    private Reserva[] reservas;
    private int numHuespedes;
    private int numHabitaciones;
    private int numReservas;
    private Controlador controlador;




    public void setControlador(Controlador controlador) {
        if (controlador != null) {
            this.controlador = controlador;
        } else {
            throw new IllegalArgumentException("El controlador no puede ser nulo.");
        }
    }

    public void comenzar() {
        Opcion opcionElegida;
        do {
            Consola.mostrarMenu();
            opcionElegida = Consola.elegirOpcion();
            ejecutarOpcion(opcionElegida);
        } while (opcionElegida != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Gracias por utilizar nuestra aplicación de reservas de hotel.");
    }

    public void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                listarReservasHuesped();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                mostrarReservas();
                break;
            case REALIZAR_CHECKIN:
                realizarCheckin();
                break;
            case REALIZAR_CHECKOUT:
                realizarCheckout();
                break;
            case SALIR:
                System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void insertarHuesped() {
        try {
            Huesped nuevoHuesped = Consola.leerHuesped();

            if (getNumElementosNoNulos(huespedes) < CAPACIDAD) {
                for (int i = 0; i < huespedes.length; i++) {
                    if (huespedes[i] == null) {
                        huespedes[i] = nuevoHuesped;
                        numHuespedes++;
                        System.out.println("Huésped insertado correctamente.");
                        return;
                    }
                }
            } else {
                System.out.println("No hay capacidad para más huéspedes.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void buscarHuesped() {
        System.out.print("Introduce el DNI del huésped a buscar: ");
        String dniBuscar = Entrada.cadena();

        Huesped huespedEncontrado = null;
        for (Huesped huesped : huespedes) {
            if (huesped != null && huesped.getDni().equals(dniBuscar)) {
                huespedEncontrado = huesped;
                break;
            }
        }

        if (huespedEncontrado != null) {
            System.out.println("Huésped encontrado:\n" + huespedEncontrado);
        } else {
            System.out.println("No se encontró un huésped con ese DNI.");
        }
    }


    private void borrarHuesped() {
        System.out.print("Introduce el DNI del huésped a borrar: ");
        String dniBorrar = Entrada.cadena();
        boolean huespedBorrado = false;

        for (int i = 0; i < numHuespedes; i++) {
            if (huespedes[i] != null && huespedes[i].getDni().equals(dniBorrar)) {
                huespedes[i] = null;
                huespedBorrado = true;
                numHuespedes--;
                break;
            }
        }

        if (huespedBorrado) {
            System.out.println("Huésped borrado correctamente.");
        } else {
            System.out.println("No se encontró un huésped con ese DNI.");
        }
    }


    private void mostrarHuespedes() {
        if (getNumElementosNoNulos(huespedes) > 0) {
            System.out.println("Listado de Huéspedes:");
            for (Huesped huesped : huespedes) {
                if (huesped != null) {
                    System.out.println(huesped);
                }
            }
        } else {
            System.out.println("No hay huéspedes registrados.");
        }
    }


    private void insertarHabitacion() {
        Habitacion nuevaHabitacion = Consola.leerHabitacion();

        if (getNumElementosNoNulos(habitaciones) < CAPACIDAD) {
            habitaciones[numHabitaciones] = nuevaHabitacion;
            numHabitaciones++;
            System.out.println("Habitación insertada correctamente.");
        } else {
            System.out.println("No hay capacidad para más habitaciones.");
        }
    }

    private void buscarHabitacion() {
        System.out.print("Introduce el número de puerta de la habitación a buscar: ");
        int puertaBuscar = Entrada.entero();
        System.out.print("Introduce el número de planta de la habitación a buscar: ");
        int plantaBuscar = Entrada.entero();
        Habitacion habitacionEncontrada = null;

        for (int i = 0; i < numHabitaciones; i++) {
            if (habitaciones[i] != null && habitaciones[i].getPuerta() == puertaBuscar
                    && habitaciones[i].getPlanta() == plantaBuscar) {
                habitacionEncontrada = habitaciones[i];
                break;
            }
        }

        if (habitacionEncontrada != null) {
            System.out.println("Habitación encontrada:\n" + habitacionEncontrada);
        } else {
            System.out.println("No se encontró una habitación con ese número de puerta y planta.");
        }
    }

    private void borrarHabitacion() {
        System.out.print("Introduce el número de puerta de la habitación a borrar: ");
        int puertaBorrar = Entrada.entero();
        System.out.print("Introduce el número de planta de la habitación a borrar: ");
        int plantaBorrar = Entrada.entero();
        boolean habitacionBorrada = false;

        for (int i = 0; i < numHabitaciones; i++) {
            if (habitaciones[i] != null && habitaciones[i].getPuerta() == puertaBorrar
                    && habitaciones[i].getPlanta() == plantaBorrar) {
                habitaciones[i] = null;
                habitacionBorrada = true;
                numHabitaciones--;
                break;
            }
        }

        if (habitacionBorrada) {
            System.out.println("Habitación borrada correctamente.");
        } else {
            System.out.println("No se encontró una habitación con ese número de puerta y planta.");
        }
    }

    private void mostrarHabitaciones() {
        if (getNumElementosNoNulos(habitaciones) > 0) {
            System.out.println("Listado de Habitaciones:");
            for (int i = 0; i < numHabitaciones; i++) {
                if (habitaciones[i] != null) {
                    System.out.println(habitaciones[i]);
                }
            }
        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }


    private void insertarReserva() {
        Reserva nuevaReserva = Consola.leerReserva();

        // Verificar disponibilidad antes de insertar
        if (getNumElementosNoNulos(reservas) < CAPACIDAD && consultarDisponibilidad(nuevaReserva)) {
            reservas[numReservas] = nuevaReserva;
            numReservas++;
            System.out.println("Reserva insertada correctamente.");
        } else {
            System.out.println("No hay capacidad para más reservas o no hay disponibilidad en el período indicado.");
        }
    }



    private void listarReservasHuesped() {
        System.out.print("Introduce el DNI del huésped para listar sus reservas: ");
        String dniBuscar = Entrada.cadena();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dniBuscar)) {
                System.out.println(reserva);
            }
        }
    }

    private void listarReservasTipoHabitacion() {
        TipoHabitacion tipoHabitacion = Consola.leerTipoHabitacion();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                System.out.println(reserva);
            }
        }
    }


    private Reserva[] getReservasAnulables(String dni) {
        List<Reserva> anulables = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni) &&
                    reserva.getFechaInicioReserva().isAfter(fechaActual)) {
                anulables.add(reserva);
            }
        }

        return anulables.toArray(new Reserva[0]);
    }

    private void anularReserva() {
        System.out.print("Introduce el DNI del huésped para anular una reserva: ");
        String dniBuscar = Entrada.cadena();
        Reserva[] reservasAnulables = getReservasAnulables(dniBuscar);

        if (reservas.length == 0) {
            System.out.println("No hay reservas anulables para ese huésped.");
        } else if (reservasAnulables.length == 1) {
            System.out.print("¿Desea anular la siguiente reserva? (S/N)\n" + reservas[0]);
            char opcion = Entrada.caracter();
            if (opcion == 'S' || opcion == 's') {
                anularReserva();
                System.out.println("Reserva anulada con éxito.");
            } else {
                System.out.println("Anulación de reserva cancelada.");
            }
        } else {
            System.out.println("Seleccione la reserva a anular (1-" + reservasAnulables.length + "):");
            for (int i = 0; i < reservasAnulables.length; i++) {
                System.out.println((i + 1) + ". " + reservasAnulables[i]);
            }
            int opcion = Entrada.entero();
            if (opcion >= 1 && opcion <= reservasAnulables.length) {
                anularReserva();
                System.out.println("Reserva anulada con éxito.");
            } else {
                System.out.println("Opción no válida. Anulación de reserva cancelada.");
            }
        }
    }

    public void mostrarReservas() {
        System.out.println("Lista de reservas:");

        // Verificar si hay reservas almacenadas
        if (getNumElementosNoNulos(reservas) == 0) {
            System.out.println("No hay reservas almacenadas.");
        } else {
            // Mostrar todas las reservas almacenadas
            for (Reserva reserva : reservas) {
                if (reserva != null) {
                    System.out.println(reserva);
                }
            }
        }
    }

    private int getNumElementosNoNulos(Object[] array) {
        int count = 0;
        for (Object element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }

    private boolean consultarDisponibilidad(Reserva nuevaReserva) {
        Habitacion habitacion = nuevaReserva.getHabitacion();
        LocalDate fechaInicio = nuevaReserva.getFechaInicioReserva();
        LocalDate fechaFin = nuevaReserva.getFechaFinReserva();

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHabitacion().equals(habitacion)) {
                LocalDate inicioReserva = reserva.getFechaInicioReserva();
                LocalDate finReserva = reserva.getFechaFinReserva();
                if (!(fechaInicio.isAfter(finReserva) || fechaFin.isBefore(inicioReserva))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void realizarCheckin() {
        System.out.print("Introduce el DNI del huésped para realizar el check-in: ");
        String dni = Entrada.cadena();

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni)) {
                reservasHuesped.add(reserva);
            }
        }

        if (reservasHuesped.isEmpty()) {
            System.out.println("El huésped no tiene reservas para realizar el check-in.");
            return;
        }

        System.out.println("Reservas del huésped:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }

        System.out.print("Selecciona el número de reserva para realizar el check-in: ");
        int opcion = Entrada.entero();
        if (opcion < 1 || opcion > reservasHuesped.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Reserva reservaSeleccionada = reservasHuesped.get(opcion - 1);

        LocalDateTime fechaActual = LocalDateTime.now();
        if (!reservaSeleccionada.getFechaInicioReserva().equals(fechaActual.toLocalDate())) {
            System.out.println("La reserva no es para el día actual, no se puede realizar el check-in.");
            return;
        }

        reservaSeleccionada.getCheckin();
        controlador.realizarCheckin(reservaSeleccionada, fechaActual);
        System.out.println("Check-in realizado con éxito para la reserva:\n" + reservaSeleccionada);
    }



    private void realizarCheckout() {
        System.out.print("Introduce el DNI del huésped para realizar el check-out: ");
        String dni = Entrada.cadena();

        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getHuesped().getDni().equals(dni)) {
                reservasHuesped.add(reserva);
            }
        }

        if (reservasHuesped.isEmpty()) {
            System.out.println("El huésped no tiene reservas para realizar el check-out.");
            return;
        }

        System.out.println("Reservas del huésped:");
        for (int i = 0; i < reservasHuesped.size(); i++) {
            System.out.println((i + 1) + ". " + reservasHuesped.get(i));
        }

        System.out.print("Selecciona el número de reserva para realizar el check-out: ");
        int opcion = Entrada.entero();
        if (opcion < 1 || opcion > reservasHuesped.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Reserva reservaSeleccionada = reservasHuesped.get(opcion - 1);

        LocalDateTime fechaActual = LocalDateTime.now();
        if (reservaSeleccionada.getFechaFinReserva().isBefore(ChronoLocalDate.from(fechaActual.toLocalDate().atStartOfDay()))) {
            System.out.println("La reserva ya ha pasado su fecha de finalización.");
            return;
        }

        reservaSeleccionada.getCheckout();
        controlador.realizarCheckout(reservaSeleccionada, fechaActual);
        System.out.println("Check-out realizado con éxito para la reserva:\n" + reservaSeleccionada);
    }

}



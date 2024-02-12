package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.Reservas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private static final int CAPACIDAD = 100;
    private List<Huesped> huespedes;
    private List<Habitacion> habitaciones;
    private List<Reserva> reservas;

    public Modelo() {
        huespedes = new ArrayList<>();
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public void comenzar() {
        habitaciones = (List<Habitacion>) new Habitaciones(CAPACIDAD);
        huespedes = (List<Huesped>) new Huespedes(CAPACIDAD);
        reservas = (List<Reserva>) new Reservas(CAPACIDAD);
    }


    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    // Métodos para gestionar huéspedes
    public void insertar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("El huésped no puede ser nulo.");
        }
        if (huespedes.contains(huesped)) {
            throw new IllegalArgumentException("El huésped ya existe en el modelo.");
        }
        huespedes.add(huesped);
    }

    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("El huésped no puede ser nulo.");
        }
        int indice = huespedes.indexOf(huesped);
        return indice != -1 ? huespedes.get(indice) : null;
    }

    public void borrar(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("El huésped no puede ser nulo.");
        }
        huespedes.remove(huesped);
    }

    public List<Huesped> getHuespedes() {
        return new ArrayList<>(huespedes);
    }

    // Métodos para gestionar habitaciones
    public void insertar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("La habitación no puede ser nula.");
        }
        if (habitaciones.contains(habitacion)) {
            throw new IllegalArgumentException("La habitación ya existe en el modelo.");
        }
        habitaciones.add(habitacion);
    }

    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("La habitación no puede ser nula.");
        }
        int indice = habitaciones.indexOf(habitacion);
        return indice != -1 ? habitaciones.get(indice) : null;
    }

    public void borrar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("La habitación no puede ser nula.");
        }
        habitaciones.remove(habitacion);
    }

    public List<Habitacion> getHabitaciones() {
        return new ArrayList<>(habitaciones);
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("El tipo de habitación no puede ser nulo.");
        }
        List<Habitacion> habitacionesFiltradas = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipoHabitacion() == tipoHabitacion) {
                habitacionesFiltradas.add(habitacion);
            }
        }
        return habitacionesFiltradas;
    }

    // Métodos para gestionar reservas
    public void insertar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        if (reservas.contains(reserva)) {
            throw new IllegalArgumentException("La reserva ya existe en el modelo.");
        }
        reservas.add(reserva);
    }

    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        int indice = reservas.indexOf(reserva);
        return indice != -1 ? reservas.get(indice) : null;
    }

    public void borrar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula.");
        }
        reservas.remove(reserva);
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("El huésped no puede ser nulo.");
        }
        List<Reserva> reservasHuesped = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(reserva);
            }
        }
        return reservasHuesped;
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("El tipo de habitación no puede ser nulo.");
        }
        List<Reserva> reservasTipoHabitacion = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().getTipoHabitacion() == tipoHabitacion) {
                reservasTipoHabitacion.add(reserva);
            }
        }
        return reservasTipoHabitacion;
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("La habitación no puede ser nula.");
        }
        List<Reserva> reservasFuturas = new ArrayList<>();
        LocalDateTime ahora = LocalDateTime.now(); // para el if queria poner este atajo pero al utilizarlo me da error
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasFuturas.add(reserva);
            }
        }
        return reservasFuturas;
    }

    // Métodos para realizar check-in y check-out
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new IllegalArgumentException("La reserva y la fecha no pueden ser nulas.");
        }
        reserva.setCheckin(reserva.getCheckin());
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new IllegalArgumentException("La reserva y la fecha no pueden ser nulas.");
        }
        reserva.setCheckout(reserva.getCheckout());
    }
}


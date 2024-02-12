package org.iesalandalus.programacion.reservashotel.negocio;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reservas {


    private static final int CAPACIDAD_RESERVAS = 10;
    private Reserva[] coleccionReservas;
    private int capacidad;
    private int tamano;

    public Reservas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionReservas = new Reserva[capacidad];
    }


    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede insertar una reserva nula.");
        }

        int indice = buscarIndice(reserva);
        if (indice != -1) {
            throw new IllegalArgumentException("No se admiten reservas duplicadas.");
        }

        if (tamanoSuperado()) {
            throw new IllegalStateException("El tamaño de la colección ha sido superado.");
        }

        if (capacidadSuperada()) {
            throw new IllegalStateException("La capacidad de la colección ha sido superada.");
        }

        indice = buscarIndice(reserva);
        coleccionReservas[indice] = reserva;
        tamano++;
    }


    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede buscar una reserva nula.");
        }
        int indice = buscarIndice(reserva);
        return (indice != -1) ? coleccionReservas[indice] : null;
    }

    public void borrar(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede borrar una reserva nula.");
        }
        int indice = buscarIndice(reserva);
        if (indice != -1) {
            coleccionReservas[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }


    private int buscarIndice(Reserva reserva) {
        for (int i = 0; i < tamano; i++) {
            if (reserva.equals(coleccionReservas[i])) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
        }
        coleccionReservas[tamano - 1] = null;
    }

    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano > capacidad;
    }

    private Reserva[] copiaProfundaReservas(Reserva[] coleccion) {
        if (coleccion == null) {
            return null;
        }
        Reserva[] copia = new Reserva[coleccion.length];
        for (int i = 0; i < coleccion.length; i++) {
            if (coleccion[i] != null) {
                copia[i] = new Reserva(coleccion[i]);
            }
        }
        return copia;
    }
    public Reserva[] getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede obtener reservas para un huésped nulo.");
        }

        Reserva[] reservasHuesped = new Reserva[tamano];
        int contador = 0;
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getHuesped().equals(huesped)) {
                reservasHuesped[contador] = reserva;
                contador++;
            }
        }
        return Arrays.copyOf(reservasHuesped, contador);
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("No se puede obtener reservas para un tipo de habitación nulo.");
        }

        Reserva[] reservasTipoHabitacion = new Reserva[tamano];
        int contador = 0;
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion[contador] = reserva;
                contador++;
            }
        }
        return Arrays.copyOf(reservasTipoHabitacion, contador);
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede obtener reservas futuras para una habitación nula.");
        }

        Reserva[] reservasFuturas = new Reserva[tamano];
        int contador = 0;
        LocalDate hoy = LocalDate.now();
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getHabitacion().equals(habitacion) && reserva.getFechaInicioReserva().isAfter(hoy)) {
                reservasFuturas[contador] = reserva;
                contador++;
            }
        }
        return Arrays.copyOf(reservasFuturas, contador);
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede realizar check-in para una reserva nula.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de check-in no puede ser nula.");
        }

        reserva.setCheckin(reserva.getCheckin());
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede realizar check-in para una reserva nula.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de check-in no puede ser nula.");
        }
        reserva.setCheckout(reserva.getCheckout());
    }
}




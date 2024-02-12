package org.iesalandalus.programacion.reservashotel.negocio;

import com.sun.tools.javac.Main;
import org.iesalandalus.programacion.reservashotel.MainApp;
import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Habitaciones {

    private static final int CAPACIDAD_HABITACIONES = 10;
    private Habitacion[] coleccionHabitaciones;
    private int capacidad;
    private int tamano;


    public Habitaciones(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHabitaciones = new Habitacion[capacidad];
    }

    public Habitaciones() {
        this(CAPACIDAD_HABITACIONES);
    }

    public Habitacion[] get() {
        return copiaProfundaHabitaciones(coleccionHabitaciones);
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return MainApp.CAPACIDAD;
    }

    public void insertar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede insertar una habitación nula.");
        }
        if (buscarIndice(habitacion) != -1) {
            throw new IllegalArgumentException("No se admiten habitaciones duplicadas.");
        }
        if (tamanoSuperado()) {
            throw new IllegalStateException("El tamaño de la colección ha sido superado.");
        }
        if (capacidadSuperada()) {
            throw new IllegalStateException("La capacidad de la colección ha sido superada.");
        }

        int indice = buscarIndice();
        coleccionHabitaciones[indice] = habitacion;
        tamano++;
    }

    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede buscar una habitación nula.");
        }
        int indice = buscarIndice(habitacion);
        return (indice != -1) ? coleccionHabitaciones[indice] : null;
    }

    public void borrar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede borrar una habitación nula.");
        }
        int indice = buscarIndice(habitacion);
        if (indice != -1) {
            coleccionHabitaciones[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new IllegalArgumentException("No se puede obtener habitaciones para un tipo de habitación nulo.");
        }

        List<Habitacion> habitacionesTipoHabitacion = new ArrayList<>();
        for (Habitacion habitacion : coleccionHabitaciones) {
            if (habitacion != null && habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                habitacionesTipoHabitacion.add(habitacion);
            }
        }
        return habitacionesTipoHabitacion;
    }

    private int buscarIndice() {
        for (int i = 0; i < coleccionHabitaciones.length; i++) {
            if (coleccionHabitaciones[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int buscarIndice(Habitacion habitacion) {
        for (int i = 0; i < tamano; i++) {
            if (habitacion.equals(coleccionHabitaciones[i])) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];
        }
        coleccionHabitaciones[tamano - 1] = null;
    }

    private boolean tamanoSuperado() {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada() {
        return tamano > capacidad;
    }

    private Habitacion[] copiaProfundaHabitaciones(Habitacion[] habitaciones) {
        return Arrays.copyOf(habitaciones, habitaciones.length);
    }
}

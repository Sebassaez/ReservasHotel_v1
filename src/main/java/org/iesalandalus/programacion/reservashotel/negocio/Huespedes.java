package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Huespedes {

    private static final int CAPACIDAD_HUESPEDES = 10;
    private Huesped[] coleccionHuespedes;
    private int capacidad;
    private int tamano;


    public Huespedes( int capacidad){
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionHuespedes = new Huesped[capacidad];
    }

    public Huesped[] get () {
        return copiaProfundaHuespedes(coleccionHuespedes);
    }

    public int getTamano () {
        return tamano;
    }

    public int getCapacidad () {
        return capacidad;
    }

    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede insertar un huésped nulo.");
        }
        int indice = buscarIndice(huesped);
        if (indice != -1) {
            throw new OperationNotSupportedException("El huésped ya existe en la colección.");
        }
        if (tamanoSuperado() || capacidadSuperada()) {
            throw new OperationNotSupportedException("El tamaño o la capacidad de la colección ha sido superado.");
        }
        coleccionHuespedes[tamano] = huesped;
        tamano++;
    }

    public Huesped buscar (Huesped huesped){
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede buscar un huésped nulo.");
        }
        int indice = buscarIndice(huesped);
        return (indice != -1) ? coleccionHuespedes[indice] : null;
    }

    public void borrar (Huesped huesped){
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede borrar un huésped nulo.");
        }
        int indice = buscarIndice(huesped);
        if (indice != -1) {
            coleccionHuespedes[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        }
    }

    private int buscarIndice (Huesped huesped) {
        for (int i = 0; i < tamano; i++) {
            if (huesped.equals(coleccionHuespedes[i])) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda ( int indice){
        for (int i = indice; i < tamano - 1; i++) {
            coleccionHuespedes[i] = coleccionHuespedes[i + 1];
        }
        coleccionHuespedes[tamano - 1] = null;
    }

    private boolean tamanoSuperado () {
        return tamano >= capacidad;
    }

    private boolean capacidadSuperada () {
        return tamano > capacidad;
    }

    private Huesped[] copiaProfundaHuespedes(Huesped[] coleccion) {
        if (coleccion == null) {
            return null;
        }
        Huesped[] copia = new Huesped[coleccion.length];
        for (int i = 0; i < coleccion.length; i++) {
            if (coleccion[i] != null) {
                copia[i] = new Huesped(coleccion[i]);
            }
        }
        return copia;
    }
}

package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.modelo.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class MainApp {

    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }
}

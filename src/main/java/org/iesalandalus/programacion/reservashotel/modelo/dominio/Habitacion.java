package org.iesalandalus.programacion.reservashotel.modelo.dominio;

public class Habitacion {

    public final static double MIN_PRECIO_HABITACION = 40.00;
    public final static double MAX_PRECIO_HABITACION = 150.00;
    public final static int MIN_NUMERO_PUERTA = 1;
    public final static int MAX_NUMERO_PUERTA = 15;
    public final static int MIN_NUMERO_PLANTA = 1;
    public final static int MAX_NUMERO_PLANTA = 3;

    private String identificador;
    private int planta;
    private int puerta;
    private double precio;
    private TipoHabitacion tipoHabitacion;


    // Constructor con parámetros que hace uso de los métodos de modificación
    public Habitacion(int planta, int puerta, double precio,TipoHabitacion tipoHabitacion) {
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setTipoHabitacion(tipoHabitacion);
        setIdentificador();
    }

    // Métodos de acceso y modificación

    public String getIdentificador() {
        return identificador;
    }

    private void setIdentificador() {
        identificador = String.format("%d%02d", planta, puerta);
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getPlanta() {
        return planta;
    }

    private void setPlanta(int planta) {
        if (planta < MIN_NUMERO_PLANTA || planta > MAX_NUMERO_PLANTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitación un valor menor que 1 ni mayor que 3.");
        }
        this.planta = planta;
    }

    public int getPuerta() {
        return puerta;
    }

    private void setPuerta(int puerta) {
        if (puerta < MIN_NUMERO_PUERTA || puerta > MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitación un valor menor que 1 ni mayor que 16.");
        }
        this.puerta = puerta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitación un valor menor que 40.0 ni mayor que 150.0.");
        }
        this.precio = precio;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("Tipo de habitación no válido");
        }
        this.tipoHabitacion = tipoHabitacion;
    }


    public Habitacion(int planta, int puerta, double precio) {
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
    }
    // Constructor copia
    public Habitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una habitación nula.");
        }
        this.identificador = habitacion.identificador;
        setPlanta(habitacion.planta);
        setPuerta(habitacion.puerta);
        setPrecio(habitacion.precio);
        setTipoHabitacion(habitacion.tipoHabitacion);
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion habitacion = (Habitacion) o;
        return identificador.equals(habitacion.identificador);
    }

    @Override
    public int hashCode() {
        return identificador.hashCode();
    }

    // Método toString
    public String toString() {
        return String.format("Habitación %s - Planta: %d, Puerta: %d, Precio: %.2f?, Tipo: %s",
                identificador, planta, puerta, precio, tipoHabitacion.getCadenaAMostrar());
    }
}

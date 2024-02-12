package org.iesalandalus.programacion.reservashotel.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huesped {

    private static final String ER_TELEFONO = "^[6-9]\\d{8}$";
    private static final String ER_CORREO = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
    private static final String ER_DNI = "(\\d{8})([A-HJ-NP-TV-Z])";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;


    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    public Huesped(Huesped huesped){
        if (huesped == null) {
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        }
        setNombre(huesped.getNombre());
        setDni(huesped.getDni());
        setCorreo(huesped.getCorreo());
        setTelefono(huesped.getTelefono());
        setFechaNacimiento(huesped.getFechaNacimiento());
    }

    private String formateaNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede ser nulo o vacío.");
        }

        nombre = nombre.trim().toLowerCase();
        StringBuilder nombreFormateado = new StringBuilder();

        // Ponemos la primera letra en mayúscula
        nombreFormateado.append(Character.toUpperCase(nombre.charAt(0)));

        for (int i = 1; i < nombre.length(); i++) {
            if (nombre.charAt(i - 1) == ' ') {
                nombreFormateado.append(Character.toUpperCase(nombre.charAt(i)));
            } else {
                nombreFormateado.append(nombre.charAt(i));
            }
        }

        return nombreFormateado.toString();
    }

    private boolean comprobarLetraDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        }
        // Patrón para extraer los números del DNI
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher comparador = patron.matcher(dni);

        if (comparador.matches()) {
            int numerosDni = Integer.parseInt(comparador.group(1));
            int resto = numerosDni % 23;

            // Obtener la letra correspondiente con un switch
            char letraCalculada;
            switch (resto) {
                case 0:
                    letraCalculada = 'T';
                    break;
                case 1:
                    letraCalculada = 'R';
                    break;
                case 2:
                    letraCalculada = 'W';
                    break;
                case 3:
                    letraCalculada = 'A';
                    break;
                case 4:
                    letraCalculada = 'G';
                    break;
                case 5:
                    letraCalculada = 'M';
                    break;
                case 6:
                    letraCalculada = 'Y';
                    break;
                case 7:
                    letraCalculada = 'F';
                    break;
                case 8:
                    letraCalculada = 'P';
                    break;
                case 9:
                    letraCalculada = 'D';
                    break;
                case 10:
                    letraCalculada = 'X';
                    break;
                case 11:
                    letraCalculada = 'B';
                    break;
                case 12:
                    letraCalculada = 'N';
                    break;
                case 13:
                    letraCalculada = 'J';
                    break;
                case 14:
                    letraCalculada = 'Z';
                    break;
                case 15:
                    letraCalculada = 'S';
                    break;
                case 16:
                    letraCalculada = 'Q';
                    break;
                case 17:
                    letraCalculada = 'V';
                    break;
                case 18:
                    letraCalculada = 'H';
                    break;
                case 19:
                    letraCalculada = 'L';
                    break;
                case 20:
                    letraCalculada = 'C';
                    break;
                case 21:
                    letraCalculada = 'K';
                    break;
                case 22:
                    letraCalculada = 'E';
                    break;
                default:
                    letraCalculada = ' ';
                    break;
            }

            // Comparar la letra calculada con la letra proporcionada
            return Character.toUpperCase(letraCalculada) == Character.toUpperCase(comparador.group(2).charAt(0));
        }
        return false;
    }

    private String getIniciales() {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede ser nulo o vacío.");
        }

        nombre = nombre.trim().toLowerCase();
        StringBuilder iniciales = new StringBuilder();

        iniciales.append(Character.toUpperCase(nombre.charAt(0)));

        for (int i = 1; i < nombre.length(); i++) {
            if (nombre.charAt(i - 1) == ' ') {
                iniciales.append(Character.toUpperCase(nombre.charAt(i)));
            }
        }

        return iniciales.toString();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = formateaNombre(nombre);

    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un huésped no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new IllegalArgumentException("El correo no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)){
            throw new IllegalArgumentException("El correo proporcionado no es correcto");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        }
        if (!dni.matches(ER_DNI)){
            throw new IllegalArgumentException("El DNI proporcionado no es correcto");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("La letra del DNI proporcionado no es correcta.");
        }
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un huésped no puede ser nula.");
        }
        LocalDate fechaActual = LocalDate.now();
        if (fechaNacimiento.isAfter(fechaActual)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }

        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return Objects.equals(dni, huesped.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, telefono, correo, dni, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + '\'' +
                ", DNI=" + dni + '\'' +
                ", correo=" + correo + '\'' +
                ", teléfono=" + telefono + '\'' +
                ", fecha nacimiento=" + fechaNacimiento ;
    }
}

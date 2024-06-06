package parcial2;

class Jugador {
    private String nombreJugador;
    private int edad;
    private String linea;
    private Equipo nombreEquipo;

    public Jugador(String nombreJugador, int edad, String linea, Equipo nombreEquipo) {
        this.nombreJugador = nombreJugador;
        this.edad = edad;
        this.linea = linea;
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getEdad() {
        return edad;
    }

    public String getLinea() {
        return linea;
    }

    public Equipo getNombreEquipo() {
        return nombreEquipo;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombreJugador + ", Edad: " + edad + ", Línea: " + linea + ", Equipo: " + nombreEquipo.getNombreEquipo();
    }
}
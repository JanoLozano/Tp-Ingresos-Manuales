package parcial2;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Jugador> listaJugadores = new LinkedList<>();
        LinkedList<Equipo> listaEquipos = new LinkedList<>();
        
        Equipo equipo = new Equipo("Nombre del Equipo", listaJugadores);
        
        // Creacion objeto gestor
        GestorEquipos gestor = new GestorEquipos(listaEquipos);
        // Menú de opciones
        String[] opciones = {
            "Agregar jugador",
            "Eliminar jugador",
            "Buscar jugador por nombre",
            "Obtener cantidad total de jugadores en un equipo",
            "Mostrar lista de jugadores",
            "Agregar equipo",
            "Eliminar equipo",
            "Buscar equipo por nombre",
            "Obtener cantidad total de equipos",
            "Mostrar lista de equipos",
            "Jugar partido",
            "Salir"
        };
        // Esto la verdad no se como funciona pero es que si no no queda bien
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox);
        
        int opcion;
        do {
        	JOptionPane.showMessageDialog(null, panel, "Menú de opciones", JOptionPane.PLAIN_MESSAGE);
            opcion = comboBox.getSelectedIndex();

            switch (opcion) {
	         	// comienzo de funciones de la clase Equipo
                case 0:
                    equipo.agregarJugador(listaEquipos, listaJugadores);
                    break;
                case 1:
                    equipo.eliminarJugador(listaJugadores, listaEquipos);
                    break;
                case 2:
                    equipo.buscarJugadorPorNombre();
                    break;
                case 3:
                    equipo.cantidadTotalDeJugadoresEnEquipo(listaEquipos);
                    break;
                case 4:
                    equipo.mostrarListaJugadores();
                    break;
                // comienzo de funciones de la clase GestorEquipos
                case 5:
                    gestor.agregarEquipo(listaEquipos);
                    break;
                case 6:
                    gestor.eliminarEquipo(listaEquipos, listaJugadores);
                    break;
                case 7:
                    gestor.buscarEquipoPorNombre();
                    break;
                case 8:
                	gestor.cantidadTotalDeEquipos(listaEquipos);
                    break;
                case 9:
                    gestor.mostrarListaDeEquipos();
                    break;
                case 10:
                	equipo.jugarPartida(listaEquipos, listaJugadores);
                    break;
                case 11:
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Inténtelo de nuevo.");
            }
        } while (opcion != 11);
    }
}

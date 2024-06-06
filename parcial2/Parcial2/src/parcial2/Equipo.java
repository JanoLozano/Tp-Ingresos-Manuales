package parcial2;

import java.util.LinkedList;

import javax.swing.JOptionPane;

class Equipo {
    private String nombreEquipo;
    private LinkedList<Jugador> listaJugadores;

    public Equipo(String nombreEquipo, LinkedList<Jugador> listaJugadores) {
        this.nombreEquipo = nombreEquipo;
        this.listaJugadores = listaJugadores;
    }
    
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public LinkedList<Jugador> getListaJugadores() {
        return listaJugadores;
    }
    
    // Metodo agregarJugador---------------
    public void agregarJugador(LinkedList<Equipo> listaEquipos, LinkedList<Jugador> listaJugadores) {
        // Verificar si hay equipos existentes
        if (listaEquipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos existentes. Debe agregar al menos un equipo antes "
            		+ "de agregar jugadores.");
            return;
        }
        
        // Armar el mensaje con los nombres de los equipos
        String mensajeEquipos = "";
        for (Equipo eq : listaEquipos) {
            mensajeEquipos += eq.getNombreEquipo() + "\n";
        }
        
        // Solicitar al usuario que seleccione un equipo existente
        String nombreEquipoSeleccionado = JOptionPane.showInputDialog("Seleccione el equipo al que desea agregar el jugador:\n" 
        + mensajeEquipos);

        // Buscar el equipo seleccionado en la lista de equipos
        Equipo equipoSeleccionado = null;
        for (Equipo eq : listaEquipos) {
            if (eq.getNombreEquipo().equals(nombreEquipoSeleccionado)) {
                equipoSeleccionado = eq;
                break;
            }
        }
        // Verificar si se encontro el equipo seleccionado
        if (equipoSeleccionado != null) {
            // Verificar cantidad de jugadores en el equipo seleccionado
            if (equipoSeleccionado.getListaJugadores().size() >= 5) {
                JOptionPane.showMessageDialog(null, "No pueden ser más de 5 jugadores por equipo.");
                return;
            }
        }
        // Verificar si se encontro el equipo seleccionado
        if (equipoSeleccionado != null) {
            // Solicitar los datos del jugador
        	String nombreJugador;
        	boolean flagNombre;
        	do {
        	    flagNombre = false;
        	    nombreJugador = JOptionPane.showInputDialog("Ingrese el nombre del jugador");
        	    if (nombreJugador.isEmpty()) {
        	        JOptionPane.showMessageDialog(null, "Ingrese un valor");
        	        flagNombre = true;
        	    } else {
        	        for (Jugador jugador : listaJugadores) {
        	            if (jugador.getNombreJugador().equalsIgnoreCase(nombreJugador)) {
        	                JOptionPane.showMessageDialog(null, "Ya existe un jugador con este nombre");
        	                flagNombre = true;
        	                break;
        	            }
        	        }
        	    }
        	} while (flagNombre);
        	
            int edad;
            String linea;
            boolean flagEdad;
            do {
            	flagEdad = false;
            	
                edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del jugador"));
                if (edad < 13) {
                    JOptionPane.showMessageDialog(null, "El jugador debe ser mayor de 13 años");
  
                } else if (edad >= 60) {
                    JOptionPane.showMessageDialog(null, "El jugador debe ser menor a 60 años");
                } else if (edad < 0) {
                	JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
				}else {
					flagEdad = true;
				}
                
                
            
            } while (!flagEdad);
            
            boolean flagLinea;
            do {
                flagLinea = false;
                linea = JOptionPane.showInputDialog("Ingrese la línea en la que juega el jugador");
                if (linea.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor");
                } else {
                    // Verificar que no se repita la misma linea
                    boolean lineaRepetida = false;
                    for (Jugador jugador : equipoSeleccionado.getListaJugadores()) {
                        if (jugador.getLinea().equalsIgnoreCase(linea)) {
                            lineaRepetida = true;
                            break;
                        }
                    }
                    if (lineaRepetida) {
                        JOptionPane.showMessageDialog(null, "Ya existe un jugador en esta línea. Ingrese otra línea.");
                    } else {
                        flagLinea = true;
                    }
                }
            } while (!flagLinea);

            // Crear el jugador y agregarlo al equipo seleccionado
            Jugador jugador = new Jugador(nombreJugador, edad, linea, equipoSeleccionado);
            equipoSeleccionado.getListaJugadores().add(jugador);  // Agregar jugador al equipo
            listaJugadores.add(jugador);  // Agregar jugador a la lista global de jugadores
            JOptionPane.showMessageDialog(null, "Jugador agregado con éxito al equipo " + nombreEquipoSeleccionado);
        } else {
            JOptionPane.showMessageDialog(null, "El equipo seleccionado no fue encontrado.");
        }
    }

    
    // Metodo eliminarJugador---------------
    public void eliminarJugador(LinkedList<Jugador> listaJugadores, LinkedList<Equipo> listaEquipos) {
        // Verificar si hay jugadores en este equipo
        if (listaJugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en este equipo.");
            return;
        }

        // Construir mensaje con los nombres de los jugadores en este equipo
        String mensajeJugadores = "Jugadores en este equipo:\n";
        for (Jugador jugador : this.listaJugadores) {
            mensajeJugadores += jugador.getNombreJugador() + "\n";
        }

        // Solicitar al usuario que seleccione un jugador para eliminar. No se muy bien como funciona este tipo depanel pero es que si no lo uso queda muy desprolijo todo
        String jugadorEliminar = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el jugador que desea eliminar:",
                "Eliminar Jugador",
                JOptionPane.QUESTION_MESSAGE,
                null,
                mensajeJugadores.split("\n"),
                mensajeJugadores.split("\n")[0]
        );
        // Buscar el jugador en la lista de jugadores en este equipo
        Jugador jugadorAEliminar = null;
        for (Jugador jugador : listaJugadores) {
            if (jugador.getNombreJugador().equals(jugadorEliminar)) {
                jugadorAEliminar = jugador;
                break;
            }
        }

        // Verificar si se encontro el jugador
        if (jugadorAEliminar != null) {
            // Eliminar al jugador de la lista de jugadores segun equipo
            this.listaJugadores.remove(jugadorAEliminar);
            // Eliminar al jugador de la lista de jugadores
            listaJugadores.remove(jugadorAEliminar);
            
            // Buscar el equipo al que pertenece el jugador
            for (Equipo equipo : listaEquipos) {
                if (equipo.getListaJugadores().contains(jugadorAEliminar)) {
                    // Eliminar al jugador del equipo correspondiente
                    equipo.getListaJugadores().remove(jugadorAEliminar);
                    break; // Una vez eliminado el jugador del equipo, salir del bucle
                }
            }
            
            JOptionPane.showMessageDialog(null, "Jugador " + jugadorEliminar + " eliminado con éxito.");
        } else {
            // Si no se encontro el jugador, mostrar un mensaje
            JOptionPane.showMessageDialog(null, "El jugador seleccionado no fue encontrado en este equipo.");
        }
}


    // Metodo buscarJugadorPorNombre---------------
    public void buscarJugadorPorNombre() {
        if (listaJugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la lista");
            return;
        }
        // Construir mensaje con los nombres de los jugadores existentes
        String mensajeJugadores = "Jugadores existentes:\n";
        for (Jugador jugador : listaJugadores) {
            mensajeJugadores += "-" +jugador.getNombreJugador() + "\n";
        }

        // Solicitar al usuario que ingrese el nombre del jugador que desea ver los datos
        String nombreJugadorBuscado = JOptionPane.showInputDialog("Ingrese el nombre "
        		+ "del jugador que desea ver los datos\n" + mensajeJugadores);
        for (Jugador jugador : listaJugadores) {
            if (jugador.getNombreJugador().equalsIgnoreCase(nombreJugadorBuscado)) {
                // Mostrar la informacion del jugador
                JOptionPane.showMessageDialog(null,
                        "Nombre: " + jugador.toString());
                return;
            }
        }
        // Si no se encuentra el jugador
        JOptionPane.showMessageDialog(null, "El jugador no fue encontrado.");
       
    }

    // Metodo mostrarListaJugadores---------------
    public void mostrarListaJugadores() {
        if (listaJugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores en la lista");
        } else {
            // Crear un array de String para almacenar la informacion de los jugadores
            String[] jugadoresArray = new String[listaJugadores.size()];

            // Llenar el array con la informacion de cada jugador
            int cont = 0;
            for (Jugador jugador : listaJugadores) {
                jugadoresArray[cont] = jugador.toString();
                cont++;
            }

            // Mostrar la lista de jugadores en un cuadro de dialogo
            JOptionPane.showMessageDialog(null, jugadoresArray);
        }
    }
    // Metodo cantidadTotalDeJugadoresEnEquipo---------------
    public void cantidadTotalDeJugadoresEnEquipo(LinkedList<Equipo> listaEquipos) {
        if (listaEquipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos en la lista");
            return;
        }
        
        String mensajeEquipos = "";
        for (Equipo eq : listaEquipos) {
            mensajeEquipos += eq.getNombreEquipo() + "\n";
        }
        String equipoElegido = JOptionPane.showInputDialog("Ingrese el nombre del equipo del cual desea ver "
        		+ "la cantidad de jugadores:\n" + mensajeEquipos );
        
        for (Equipo equipo : listaEquipos) {
            if (equipo.getNombreEquipo().equalsIgnoreCase(equipoElegido)) {
                int cantidadJugadores = equipo.getListaJugadores().size();
                JOptionPane.showMessageDialog(null, "El equipo " + equipoElegido + " tiene " + cantidadJugadores + " jugadores.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "El equipo " + equipoElegido + " no fue encontrado.");
    }
    
    public void jugarPartida(LinkedList<Equipo> listaEquipos) {
        // Verificar si hay suficientes equipos para jugar
        if (listaEquipos.size() != 2) {
            JOptionPane.showMessageDialog(null, "Deben haber exactamente dos equipos para jugar una partida.");
            return;
        }

        
        Equipo equipo1 = listaEquipos.get(0);
        Equipo equipo2 = listaEquipos.get(1);

        // Iniciar contadores de dragones
        int dragonesEquipo1 = 0;
        int dragonesEquipo2 = 0;

        
        for (int i = 0; i < 10; i++) { // Simular hasta 5 etapas en las que se pueden obtener dragones
            // Generar un dragon con una probabilidad del 50% // o sea asi no funciona el lol pero no se me ocurrio otra cosa dxdx // Esto en realidad simula si algun equipo va a hacerse el dragon
            boolean hayDragon = Math.random() > 0.5;
            if (!hayDragon) {
                break; // Si no hay dragon, continuar a la siguiente etapa
            }

            // Determinar cual equipo obtiene el dragon
            double probabilidadDragonEquipo1 = Math.random();
            double probabilidadDragonEquipo2 = Math.random();
            
            if (probabilidadDragonEquipo1 > probabilidadDragonEquipo2) {
                dragonesEquipo1++;
                JOptionPane.showMessageDialog(null, "El equipo " + equipo1.getNombreEquipo() + " ha obtenido un dragon.");
            } else {
                dragonesEquipo2++;
                JOptionPane.showMessageDialog(null, "El equipo " + equipo2.getNombreEquipo() + " ha obtenido un dragon.");
            }

            // Verificar si algun equipo ha obtenido el Alma del Dragon
            if (dragonesEquipo1 >= 5 || dragonesEquipo2 >= 5) {
                break;
            }
        }

        // Calcular la probabilidad de ganar basada en los dragones obtenidos
        double probabilidadGanarEquipo1 = 0.5 + (dragonesEquipo1 * 0.1); // %50 de ganar mas la cantidad de dragon * 0.1(%10) es decir mientras mas dragones mas probabilidad de ganar
        double probabilidadGanarEquipo2 = 0.5 + (dragonesEquipo2 * 0.1);

        // Ajustar la probabilidad si alguno de los equipos tiene el Alma del Dragon (5 dragones)
        if (dragonesEquipo1 >= 5) {
            probabilidadGanarEquipo1 += 0.2; // Boost adicional por tener Alma del Dragon
        }
        if (dragonesEquipo2 >= 5) {
            probabilidadGanarEquipo2 += 0.2; // Boost adicional por tener Alma del Dragon
        }

        // Determinar el ganador
        double probabilidadTotal = probabilidadGanarEquipo1 + probabilidadGanarEquipo2;
        double probabilidadAleatoria = Math.random() * probabilidadTotal;

        String equipoGanador;
        if (probabilidadAleatoria <= probabilidadGanarEquipo1) {
            equipoGanador = equipo1.getNombreEquipo();
        } else {
            equipoGanador = equipo2.getNombreEquipo();
        }

        // Mostrar resultado
        JOptionPane.showMessageDialog(null, "El equipo ganador es: " + equipoGanador);
    }
}
    	
    



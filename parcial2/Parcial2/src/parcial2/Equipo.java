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
    
    // Metodo jugarPartida
    public void jugarPartida(LinkedList<Equipo> listaEquipos,LinkedList<Jugador> listaJugadores ) {
    	// Verificar que existan equipos
    	if (listaEquipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos en la lista");
            return;
        }
    	// Verificar que no sean menos de dos equipos
    	if (listaEquipos.size() < 2) {
            JOptionPane.showMessageDialog(null, "Debe haber al menos dos equipos para jugar una partida.");
            return;
        }
    	// Verificar que hayan jugadores en la lista de los equipos
    	if (listaJugadores.isEmpty()) {
    		JOptionPane.showMessageDialog(null, "No hay jugadores en la lista de los equipos");
            return;
		}
    	// Verificar que hayan 5 jugadores en cada equipo
    	if (listaJugadores.size() < 10) { // Si el tamaño de la lista es menor a 10 significa que no hay 5 jugadores en cada equipo ya que no se pueden agregar mas de 5 por equipo
    		JOptionPane.showMessageDialog(null, "deben haber 5 jugadores en cada equipo");
            return;
		}
    	// Creacion variable Equipo que contiene la lista de los equipos segun parametro
    	Equipo equipo1 = listaEquipos.get(0);
    	Equipo equipo2 = listaEquipos.get(1);
    	
    	int contVictoriasEquipo1 = 0;
    	int contVictoriasEquipo2 = 0;
    	
    	// Bucle que continua siempre que las victorias sean menor a 3
    	while (contVictoriasEquipo1 < 3 && contVictoriasEquipo2 < 3) {
    		int resultado = (int) (Math.random() * 2); // 0 o 1
			
    		 if (resultado == 0) {
    			 contVictoriasEquipo1++;
             } else {
            	 contVictoriasEquipo2++;
             }
    		 
    		 String mensajeResultado = "Resultado de la partida:\n" +
    				 equipo1.getNombreEquipo() + ": " + contVictoriasEquipo1 + " victorias\n" +
    				 equipo2.getNombreEquipo() + ": " + contVictoriasEquipo2 + " victorias";
    		 
    		 JOptionPane.showMessageDialog(null, mensajeResultado);	
		}
    	
    	String mensajeGanador;
        if (contVictoriasEquipo1 == 3) {
            mensajeGanador = "El ganador es " + equipo1.getNombreEquipo() + " con 3 victorias.";
        } else {
            mensajeGanador = "El ganador es " + equipo2.getNombreEquipo() + " con 3 victorias.";
        }
        JOptionPane.showMessageDialog(null, mensajeGanador);
    }
}
    	
    



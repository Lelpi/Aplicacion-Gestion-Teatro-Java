package GUI.anyadirEvento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.EventoDanza;
import theaterfy.sucesos.EventoMusica;
import theaterfy.sucesos.EventoTeatro;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;

/**
 * Controlador de AnyadirEvento
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlAnyadirEvento implements ActionListener{
	private AnyadirEvento vista;
	private IntentoInterfaz interfaz;
	private ArrayList<Precio> precios;
	private ArrayList<GregorianCalendar> fechas = new ArrayList<GregorianCalendar>();

	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param v vista del panel
	 * @param i interfaz
	 */
	public ControlAnyadirEvento(AnyadirEvento v, IntentoInterfaz i) {
		this.vista = v;
		this.interfaz = i;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		if(botonPulsado.equals("Añadir fecha y hora")) {
			String parts[], partsHora[];
			GregorianCalendar fecha = new GregorianCalendar();
			
			if(vista.getPanelDatos().getDia().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir una fecha\n(dd/mm/aaaa).", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(vista.getPanelDatos().getHora().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir una hora\n(hh:mm)", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
			parts = vista.getPanelDatos().getDia().split("/");
			partsHora = vista.getPanelDatos().getHora().split(":");
			fecha.set(Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0]),
					Integer.parseInt(partsHora[0]), Integer.parseInt(partsHora[1]));
			vista.getPanelDatos().imprimirArrayFechas(fecha);
			this.fechas.add(fecha);
			}catch(java.lang.ArrayIndexOutOfBoundsException | NumberFormatException excepcion) {
				JOptionPane.showMessageDialog(vista, "Formato de la fecha: dd/mm/aaaa\nFormato de la hora: hh:mm", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		if(botonPulsado.equals("Cancelar")) {
			interfaz.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		
		if(botonPulsado.equals("Añadir evento")) {
			if(vista.getPanelDatos().getTitulo().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir un título.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(vista.getPanelDatos().getDuracion().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir una duración.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(vista.getPanelDatos().getAutor().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir un autor.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(vista.getPanelDatos().getDirector().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir un director.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(vista.getPanelDatos().getArrayFechas().size()==0) {
				JOptionPane.showMessageDialog(vista, "Debe introducir al menos una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else if(this.precios == null) {
				JOptionPane.showMessageDialog(vista, "Debe configurar los precios del evento.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else if(vista.getPanelDescripcion().danzaIsSelected()==false && 
					vista.getPanelDescripcion().musicaIsSelected()==false && 
					vista.getPanelDescripcion().teatroIsSelected()==false) {
				JOptionPane.showMessageDialog(vista, "Debe seleccionar un tipo de evento:\nDanza, música o teatro.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			else {
				String titulo = vista.getPanelDatos().getTitulo();
				String descripcion = vista.getPanelDescripcion().getDescripcion();
				String director = vista.getPanelDatos().getDirector();
				String autor = vista.getPanelDatos().getAutor();
				LocalTime duracion = null;
				ArrayList<Representacion> representaciones = new ArrayList<Representacion>();
				try {
					duracion = LocalTime.parse(vista.getPanelDatos().getDuracion());
				}catch(java.time.format.DateTimeParseException excepcion) {
					JOptionPane.showMessageDialog(vista, "Formato de duración: hh:mm.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getPanelDescripcion().danzaIsSelected()) {
					String bailarines = vista.getPanelDanza().getBailarines();
					String orquesta = vista.getPanelDanza().getOrquesta();
					String dirOrquesta = vista.getPanelDanza().getDirector();
					
					EventoDanza eventoDanza = new EventoDanza(titulo, null, descripcion, director,
							autor, duracion, this.precios, bailarines, orquesta, dirOrquesta);
					
					for(GregorianCalendar f : this.fechas) {
						representaciones.add(new Representacion(eventoDanza.getNombre(), f));
					}
					
					for(Suceso eventos : Theaterfy.getTheaterfy().getSucesos()) {
						if(eventos.getClass() != Ciclo.class) {
							if(((Evento)eventos).getNombre().equals(eventoDanza.getNombre())){
								eventoDanza=(EventoDanza)eventos;
							}
						}
					}
					Theaterfy.getTheaterfy().anyadirSuceso(eventoDanza);
					
					for(Representacion r : representaciones) {
						((Evento)eventoDanza).anyadirRepresentacion(r);
					}
				}
				
				else if(vista.getPanelDescripcion().musicaIsSelected()) {
					String piezas = vista.getPanelMusica().getPiezas();
					String orquesta = vista.getPanelDanza().getOrquesta();
					String solista = vista.getPanelMusica().getSolista();
					
					EventoMusica eventoMusica = new EventoMusica(titulo, null, descripcion, director, autor,
							duracion, this.precios, orquesta, solista, piezas);
					
					for(GregorianCalendar f : this.fechas) {
						representaciones.add(new Representacion(eventoMusica.getNombre(), f));
					}
					
					for(Suceso eventos : Theaterfy.getTheaterfy().getSucesos()) {
						if(eventos.getClass() != Ciclo.class) {
							if(((Evento)eventos).getNombre().equals(eventoMusica.getNombre())){
								eventoMusica=(EventoMusica)eventos;
							}
						}
					}
					Theaterfy.getTheaterfy().anyadirSuceso(eventoMusica);
					
					for(Representacion r : representaciones) {
						((Evento)eventoMusica).anyadirRepresentacion(r);
					}
				}
				else if(vista.getPanelDescripcion().teatroIsSelected()) {
					String elenco = vista.getPanelTeatro().getElenco();
					
					EventoTeatro eventoTeatro = new EventoTeatro(titulo, null, descripcion, director, autor, duracion,
							this.precios, elenco);
					
					for(GregorianCalendar f : this.fechas) {
						representaciones.add(new Representacion(eventoTeatro.getNombre(), f));
					}
					
					for(Suceso eventos : Theaterfy.getTheaterfy().getSucesos()) {
						if(eventos.getClass() != Ciclo.class) {
							if(((Evento)eventos).getNombre().equals(eventoTeatro.getNombre())){
								eventoTeatro=(EventoTeatro)eventos;
							}
						}
					}
					Theaterfy.getTheaterfy().anyadirSuceso(eventoTeatro);
					
					for(Representacion r : representaciones) {
						((Evento)eventoTeatro).anyadirRepresentacion(r);
					}
				}
				
			}
			interfaz.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		
		if(botonPulsado.equals("Configurar precios")) {
			vista.getPanelPrecios().setVisible(true);
			vista.getPanelDanza().setVisible(false);
			vista.getPanelMusica().setVisible(false);
			vista.getPanelTeatro().setVisible(false);
		}
		
		if(botonPulsado.equals("Guardar precios")) {
			try {
			this.precios = vista.getPanelPrecios().getPreciosZonas();
			}catch(java.lang.NullPointerException | java.lang.NumberFormatException excepcion){
				JOptionPane.showMessageDialog(vista, "Debe introducir correctamente\nlos precios de todas las zonas", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		
		if(botonPulsado.equals("Danza")) {
			vista.getPanelPrecios().setVisible(false);
			vista.getPanelDanza().setVisible(true);
			vista.getPanelMusica().setVisible(false);
			vista.getPanelTeatro().setVisible(false);
		}
		
		if(botonPulsado.equals("Musica")) {
			vista.getPanelPrecios().setVisible(false);
			vista.getPanelDanza().setVisible(false);
			vista.getPanelMusica().setVisible(true);
			vista.getPanelTeatro().setVisible(false);
		}
		
		if(botonPulsado.equals("Teatro")) {
			vista.getPanelPrecios().setVisible(false);
			vista.getPanelDanza().setVisible(false);
			vista.getPanelMusica().setVisible(false);
			vista.getPanelTeatro().setVisible(true);
		}
		
	}
}

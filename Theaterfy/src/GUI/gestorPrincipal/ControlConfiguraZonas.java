package GUI.gestorPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.zona.Zona;
import theaterfy.zona.ZonaMixta;
import theaterfy.zona.ZonaNoNumerada;
import theaterfy.zona.ZonaNumerada;

/**
 * Controlador del panel ConfiguraZonas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlConfiguraZonas implements ActionListener{
	
	private ConfiguraZonas vista;
	private IntentoInterfaz intento;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param v vista del panel
	 * @param intento interfaz
	 */
	public ControlConfiguraZonas(ConfiguraZonas v, IntentoInterfaz intento) {
		 this.vista = v;
		 this.intento=intento;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		if(botonPulsado.equals("comboBoxChanged")) {
			if(vista.getCrear().getSelected().equals("Numerada")) {
				vista.getCrear().getNumerada().setVisible(true);
				vista.getCrear().getNoNumerada().setVisible(false);
			}
			else {
				vista.getCrear().getNumerada().setVisible(false);
				vista.getCrear().getNoNumerada().setVisible(true);
			}
		}
		
		if(botonPulsado.equals("Añadir subzonas")) {
			if(vista.getSubzonas().getNombreMixta().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir el nombre de la zona.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
				if(z.getNombre().equals(vista.getSubzonas().getNombreMixta())){
					JOptionPane.showMessageDialog(vista, "El nombre de la zona ha de ser único.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			for(Zona z : Theaterfy.getTheaterfy().getZonas()) {
				if(z.getNombre().equals(vista.getSubzonas().getNombreMixta())){
					JOptionPane.showMessageDialog(vista, "El nombre de la zona ha de ser único.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			if(vista.getSubzonas().getArrayZonas().length == 0){
				JOptionPane.showMessageDialog(vista, "Seleccione al menos una zona.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Theaterfy.getTheaterfy().anyadirZona(new ZonaMixta(vista.getSubzonas().getNombreMixta(), 
					vista.getSubzonas().getArrayZonas()));
			
			ArrayList<Zona> zonasBorradas = new ArrayList<>();
			for(int i=0; i<Theaterfy.getTheaterfy().getZonas().size();i++) {
				for(Zona z1 : vista.getSubzonas().getArrayZonas()) {
					if(Theaterfy.getTheaterfy().getZonas().get(i).getNombre().equals(z1.getNombre())) {
						zonasBorradas.add(z1);
					}
				}
			}
			
			Theaterfy.getTheaterfy().getZonas().removeAll(zonasBorradas);
			
			intento.actualizarGestorPrincipal();
			intento.actualizarZonas();
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		
		if(botonPulsado.equals("Crear zona")) {
			Zona z = null;
			
			if(vista.getCrear().getNombreZona().equals("")) {
				JOptionPane.showMessageDialog(vista, "Debe introducir el nombre de la zona.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(vista.getCrear().getPrecioAbonoAnual() == -1) {
				JOptionPane.showMessageDialog(vista, "Debe introducir el precio de la zona.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if(vista.getCrear().getPrecioAbonoAnual() == -1) {
				JOptionPane.showMessageDialog(vista, "Debe introducir el precio del abono anual.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(vista.getCrear().getSelected().equals("Selecciona zona")) {
				JOptionPane.showMessageDialog(vista, "Selecciona la zona.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(vista.getCrear().getSelected().equals("Numerada")) {
				if(vista.getCrear().getNumerada().getFilas() == -1) {
					JOptionPane.showMessageDialog(vista, "Debe introducir el número de filas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getCrear().getNumerada().getColumnas() == -1) {
					JOptionPane.showMessageDialog(vista, "Debe introducir el número de columnas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getCrear().getNumerada().getPrecioZona() == -1) {
					JOptionPane.showMessageDialog(vista, "Debe introducir el precio de la zona.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				z = new ZonaNumerada(vista.getCrear().getNombreZona(), vista.getCrear().getPrecioAbonoAnual(),
						vista.getCrear().getNumerada().getFilas(), vista.getCrear().getNumerada().getColumnas());
			}
			if (vista.getCrear().getSelected().equals("No numerada")){
				if(vista.getCrear().getNoNumerada().getNumButacas() == -1) {
					JOptionPane.showMessageDialog(vista, "Debe introducir el número de butacas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(vista.getCrear().getNoNumerada().getPrecioZona() == -1) {
					JOptionPane.showMessageDialog(vista, "Debe introducir el precio de la zona.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				z = new ZonaNoNumerada(vista.getCrear().getNombreZona(), vista.getCrear().getPrecioAbonoAnual(), vista.getCrear().getNoNumerada().getNumButacas());
			}
			Theaterfy.getTheaterfy().anyadirZona(z);
			intento.actualizarGestorPrincipal();
			intento.actualizarZonas();
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		if(botonPulsado.equals("Inicio")) {
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}
		
	}

}

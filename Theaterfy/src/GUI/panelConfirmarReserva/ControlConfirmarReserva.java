package GUI.panelConfirmarReserva;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;
import theaterfy.Theaterfy;
import theaterfy.compras.Entrada;
import theaterfy.compras.EntradaReservada;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;

/**
 * Controlador del panel ConfirmarReserva
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlConfirmarReserva implements ActionListener{
	private ConfirmarReserva vista;
	private IntentoInterfaz interfaz;
	private Representacion representacion;
	
	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param v vista del panel
	 * @param i interfaz
	 */
	public ControlConfirmarReserva(ConfirmarReserva v, IntentoInterfaz i) {
		this.vista=v;
		this.interfaz=i;
	}
	
	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String botonPulsado=e.getActionCommand();
		String nombreEvento;
		Evento evento;
		DefaultTableModel modeloDatos= vista.getModeloDatos();
		int contador;
		
		if(botonPulsado.equals("Confirmar")) {
			try {
				nombreEvento =(String) modeloDatos.getValueAt(vista.getFila(), 0);
			}
			catch(ArrayIndexOutOfBoundsException exception){
				return;
			}
			evento=null;
			for(Suceso s : Theaterfy.getTheaterfy().getSucesos()) {
				if(s.getNombre().equals(nombreEvento)) {
					evento=(Evento)s;
				}
			}
			if(evento==null) {
				return;
			}
			contador=0;
			for(Entrada entrada : Theaterfy.getTheaterfy().getUsuarioActual().getEntradas()) {
				if(entrada.getClass()==EntradaReservada.class && entrada.getRepresentacion().getNombreEvento().equals(nombreEvento)) {
					contador++;
					representacion=entrada.getRepresentacion();
				}
			}
			
			vista.setPagarVisible(true, contador, nombreEvento);
		} else if(botonPulsado.equals("Pagar")) {
			ArrayList<Entrada> entradas=new ArrayList<>();
			int cantidad;
			
			try {
				cantidad=Integer.parseInt(vista.getNumEntradas());
			} catch (NumberFormatException e1) {
				
				JOptionPane.showMessageDialog(vista, "Error, la cantidad de entradas debe ser un número.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(cantidad>vista.getMaxEntradas() || cantidad<=0) {
				JOptionPane.showMessageDialog(vista, "Error, la cantidad de entradas debe ser <="+vista.getMaxEntradas()+" y >0",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			for(Entrada entrada : Theaterfy.getTheaterfy().getUsuarioActual().getEntradas()) {
				if(entrada.getClass()==EntradaReservada.class && entrada.getRepresentacion().equals(representacion) && cantidad>0) {
					entradas.add(entrada);
					cantidad--;
				}
			}
			
			try {
				
				Theaterfy.getTheaterfy().getUsuarioActual().confirmarReserva(entradas, vista.getTarjeta());
				
			} catch (NonExistentFileException |	UnsupportedImageTypeException |	OrderRejectedException e1) {
				JOptionPane.showMessageDialog(vista, "Fallo en el pago, compruebe el número de la tarjeta y la fecha de la representación",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			vista.setPagarVisible(false, 0, "");
			interfaz.actualizarBienvenidaLogin();
			this.interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
		} else if(botonPulsado.equals("Volver al inicio")) {
			interfaz.actualizarBienvenidaLogin();
			this.interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
		}
		
	}
}

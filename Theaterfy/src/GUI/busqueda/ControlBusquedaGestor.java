package GUI.busqueda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.RestriccionAforo;

/**
 * Controlador del panel BusquedaGestor
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlBusquedaGestor implements ActionListener {

	private BusquedaGestor vista;
	private IntentoInterfaz intento;

	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param busqueda vista del panel
	 * @param intento interfaz
	 */
	public ControlBusquedaGestor(BusquedaGestor busqueda, IntentoInterfaz intento) {
		this.vista = busqueda;
		this.intento = intento;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String botonPulsado = e.getActionCommand();
		if (botonPulsado.equals("Buscar")) {
			String evento = vista.getNombreEvento();
			int f = vista.getFiltro();
			ArrayList<Evento> eventos = Theaterfy.getTheaterfy().buscarEventos(evento, f - 1);

			DefaultTableModel modeloDatos = intento.getPanelBusqueda().getModeloDatos();
			modeloDatos.setRowCount(eventos.size());
			for (int i = 0; i < eventos.size(); i++) {
				modeloDatos.setValueAt(eventos.get(i).getNombre(), i, 0);
				modeloDatos.setValueAt(eventos.get(i).getDirector(), i, 1);
				modeloDatos.setValueAt(eventos.get(i).getAutor(), i, 2);
				modeloDatos.setValueAt(eventos.get(i).getDescripcion(), i, 3);
			}
			intento.mostrarPanel(IntentoInterfaz.BUSQUEDA);
			
		}else if (botonPulsado.equals("Guardar cambios")) {
			Evento ev=vista.getEvento();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

			if(!vista.getRestriccion().equals("")) {
				ev.anyadirRestriccionAforo(new RestriccionAforo((Float.parseFloat(vista.getRestriccion()))/100, null));
			}
			
			if(!vista.getFechaCancelar().equals("Cancelar fecha")) {
				Representacion aux=null;
				for(Representacion r: ev.getRepresentaciones()) {
					if(sdf.format(r.getFecha().getTime()).equals(vista.getFechaCancelar())) {
						aux=r;
					}
				}
				if(aux!=null) {
					ev.cancelarRepresentacion(aux.getFecha());
				}
			}
			
			if(!vista.getFechaPosponer1().equals("Posponer fecha")) {
				for(Representacion r: ev.getRepresentaciones()) {
					if(sdf.format(r.getFecha().getTime()).equals(vista.getFechaPosponer1())) {
						GregorianCalendar cambioFecha = new GregorianCalendar();
						
						String[] fecha=vista.getFechaPosponer2().split("/");
						String[] aux=fecha[2].split(" ");
						String[] hora=aux[1].split(":");
						cambioFecha.set(Integer.parseInt(aux[0]),Integer.parseInt(fecha[1])-1, Integer.parseInt(fecha[0]), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]));
						r.posponer(cambioFecha);
					}
				}
			}
			intento.mostrarPanel(IntentoInterfaz.BUSQUEDAGESTOR);
		}else if (botonPulsado.equals("Cancelar")) {
			intento.mostrarPanel(IntentoInterfaz.GESTOR);
		}
			

	}
}

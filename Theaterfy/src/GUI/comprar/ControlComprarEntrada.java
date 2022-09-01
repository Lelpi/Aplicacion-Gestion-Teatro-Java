package GUI.comprar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import GUI.IntentoInterfaz;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import es.uam.eps.padsof.tickets.NonExistentFileException;
import es.uam.eps.padsof.tickets.UnsupportedImageTypeException;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Evento;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Representacion;
import theaterfy.sucesos.Suceso;
import theaterfy.zona.Butaca;
import theaterfy.zona.Zona;
import theaterfy.zona.ZonaNoNumerada;
import theaterfy.zona.ZonaNumerada;

/**
 * Controlador del panel ControlComprarEntrada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlComprarEntrada implements ActionListener {
	private ComprarEntrada vista;
	private IntentoInterfaz interfaz;

	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param v vista del panel
	 * @param i interfaz
	 */
	public ControlComprarEntrada(ComprarEntrada v, IntentoInterfaz i) {
		this.vista = v;
		this.interfaz = i;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String botonPulsado = e.getActionCommand();
		Evento ev=null;
		Representacion re=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		for(Suceso s: Theaterfy.getTheaterfy().getSucesos()) {
			if(s.getNombre().equals(vista.getDatosCPanel().getTituloEvento())) {
				ev=(Evento)s;
			}
		}
		if(ev!=null) {
			for (Representacion r : ev.getRepresentaciones()) {
				if (sdf.format(r.getFecha().getTime()).equals(vista.getDatosCPanel().getDia())) {
					re=r;
				}
			}
		}
		
		if (botonPulsado.equals("Cancelar")) {
			this.interfaz.mostrarPanel(IntentoInterfaz.BUSQUEDA);
		}

		
		if (botonPulsado.equals("Reservar")) {
			vista.getDatosCPanel().getBanco().setVisible(false);
			vista.getDatosCPanel().getReserva().setVisible(true);
			JComboBox<Integer> combo= vista.getButacas().getComboEntradas();
			combo.removeAllItems();
			for(int i=0; i<Theaterfy.getTheaterfy().getMaxReservaEntradas();i++) {
				combo.addItem(i+1);
			}
		}

		if (botonPulsado.equals("Comprar")) {
			vista.getDatosCPanel().getReserva().setVisible(false);
			vista.getDatosCPanel().getBanco().setVisible(true);
			JComboBox<Integer> combo= vista.getButacas().getComboEntradas();
			combo.removeAllItems();
			for(int i=0; i<Theaterfy.getTheaterfy().getMaxCompraEntradas();i++) {
				combo.addItem(i+1);
			}
		}
		
		if(vista.getDatosCPanel().getDia()==null || vista.getDatosCPanel().getDia().equals("Fechas") || vista.getDatosCPanel().getZona().equals("Zonas")) {
			this.vista.getButacas().setVisible(false);

			return;
		}
		
		if(botonPulsado.equals("comboBoxChanged")) {
			
			for (Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
				if (vista.getDatosCPanel().getZona().equals(z.getNombre())) {
					if (z.getClass() == ZonaNoNumerada.class) {
						vista.getButacas().getNumerada().setVisible(false);
						vista.getButacas().getNoNumerada().setVisible(true);
					} else if (z.getClass() == ZonaNumerada.class) {
						vista.getButacas().getNumerada().setVisible(true);
						vista.getButacas().getNoNumerada().setVisible(false);
						if(re!=null) {
							vista.getButacas().getNumerada().actualizaTabla(re, (ZonaNumerada)z);
						}
					}
					if(ev!=null) {
						for(Precio p : ev.getPrecios()) {
				    		if(p.getZona().getNombre().equals(z.getNombre())) {
				    			vista.setPrecio(p.getPrecio());
				    		}
				    	}
					}
			    }
			}
			this.vista.getButacas().setVisible(true);
		}
		
		
		if (botonPulsado.equals("Confirmar compra")) {
			String tarjeta = vista.getDatosCPanel().getBanco().getTarjeta();
			if (tarjeta.equals("")) {
				JOptionPane.showMessageDialog(vista, "Error, debe introducir una tarjeta.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!TeleChargeAndPaySystem.isValidCardNumber(tarjeta)) {
				JOptionPane.showMessageDialog(vista, "Tarjeta inválida", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int numEntradas = vista.getButacas().getNumEntradas();
			Zona zo=null;
			
			for(Zona z: Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
				if(z.getNombre().equals(vista.getDatosCPanel().getZona())) {
					zo=z;
				}
			}
			
			for (Representacion r : ev.getRepresentaciones()) {
				if (sdf.format(r.getFecha().getTime()).equals(vista.getDatosCPanel().getDia())) {
					re=r;
				}
			}
			if(zo.getClass()==ZonaNoNumerada.class) {
				try {
					Theaterfy.getTheaterfy().getUsuarioActual().comprarEntrada(numEntradas,(ZonaNoNumerada) zo, re, tarjeta);
				} catch (NonExistentFileException | UnsupportedImageTypeException | OrderRejectedException e1) {
					System.out.println(e1.toString());
					return;
				}
			}else {
				try {
					
					int [] indicesButacas=vista.getButacas().getSeleccionadas();
					DefaultTableModel modeloTabla = vista.getButacas().modeloTabla();
					Butaca[] butacas=new Butaca[indicesButacas.length];
					int i=0;
					for(Integer indice: indicesButacas) {
						butacas[i]=((ZonaNumerada)zo).getButaca((Integer)modeloTabla.getValueAt(indice, 1), (Integer)modeloTabla.getValueAt(indice, 0));
						i++;
					}
					
					Theaterfy.getTheaterfy().getUsuarioActual().comprarEntrada(butacas,(ZonaNumerada) zo, re, tarjeta);
				} catch (NonExistentFileException | UnsupportedImageTypeException | OrderRejectedException e1) {
					System.out.println(e1.toString());
					return;
				}
			}
			this.interfaz.actualizarBienvenidaLogin();
			this.interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
		}

		if(botonPulsado.equals("Realizar reserva")) {
			int numEntradas = vista.getButacas().getNumEntradas();
			Zona zo=null;
			
			for(Zona z: Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
				if(z.getNombre().equals(vista.getDatosCPanel().getZona())) {
					zo=z;
				}
			}
			
			if(zo.getClass()==ZonaNoNumerada.class) {
				Theaterfy.getTheaterfy().getUsuarioActual().reservarEntrada(numEntradas, (ZonaNoNumerada)zo, re);
			}else {
				
				int [] indicesButacas=vista.getButacas().getSeleccionadas();
				DefaultTableModel modeloTabla = vista.getButacas().modeloTabla();
				Butaca[] butacas=new Butaca[indicesButacas.length];
				int i=0;
				for(Integer indice: indicesButacas) {
					butacas[i]=((ZonaNumerada)zo).getButaca((Integer)modeloTabla.getValueAt(indice, 1), (Integer)modeloTabla.getValueAt(indice, 0));
					i++;
				}
				
				Theaterfy.getTheaterfy().getUsuarioActual().reservarEntrada(butacas, (ZonaNumerada)zo, re);
			}
			this.interfaz.actualizarBienvenidaLogin();
			this.interfaz.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
		}
		
	}
		
		

}



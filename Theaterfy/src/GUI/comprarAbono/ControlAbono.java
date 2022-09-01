package GUI.comprarAbono;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.IntentoInterfaz;
import theaterfy.Theaterfy;
import theaterfy.sucesos.Ciclo;
import theaterfy.sucesos.Precio;
import theaterfy.sucesos.Suceso;
import theaterfy.zona.Zona;

/**
 * Controlador del panel ComprarAbono
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ControlAbono implements ActionListener{

	private ComprarAbono vista;
	private IntentoInterfaz intento;

	/**
	 * Constructor, asigna las variables a los parámetros correspondientes
	 * @param abono vista del panel
	 * @param intento interfaz
	 */
	public ControlAbono(ComprarAbono abono, IntentoInterfaz intento) {
		this.vista = abono;
		this.intento=intento;
	}

	/**
	 * Salta cuando sucede algun evento, descrito por el ActionEvent
	 * @param e suceso
	 */
	@Override
	public void actionPerformed(ActionEvent e){

		String botonPulsado=e.getActionCommand();
		if(botonPulsado.equals("Comprar")) {
			String nombreZona=vista.getZona();
			String nombreCiclo=vista.getCiclo();
			String tarjeta=vista.getTarjeta();
			Zona zona = null;
			Ciclo ciclo = null;
			
			for(Zona z:Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
				if(z.getNombre().equals(nombreZona)) {
					zona=z;
				}
			}
			
			if(nombreCiclo.equals("Anual")) {
				for(Precio p:Theaterfy.getTheaterfy().getPreciosAbonoAnual()) {
					if(p.getZona().getNombre().equals(nombreZona)) {
						zona=p.getZona();
					}
				}
				try {
					Theaterfy.getTheaterfy().getUsuarioActual().comprarAbono(zona, tarjeta);
				} catch (Exception e1) {
					System.out.println(e1);
					return;
				}
			}else {
				for(Suceso s: Theaterfy.getTheaterfy().getSucesos()) {
					if(s.getNombre().equals(nombreCiclo)) {
						ciclo=(Ciclo)s;
					}
				}
				try {
					Theaterfy.getTheaterfy().getUsuarioActual().comprarAbono(zona, ciclo, tarjeta);
				} catch (Exception e2) {
					System.out.println(e2);
					return;
				}
			}
			intento.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);

		}else if(botonPulsado.equals("Cancelar")) {
			intento.mostrarPanel(IntentoInterfaz.BIENVENIDALOGIN);
		}
		
	}

}

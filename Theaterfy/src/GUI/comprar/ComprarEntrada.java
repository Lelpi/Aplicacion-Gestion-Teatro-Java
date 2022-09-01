package GUI.comprar;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import theaterfy.sucesos.Evento;

/**
 * panel para que el usuario compre una entrada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ComprarEntrada extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private DatosComprar datos;
	private SeleccionButaca butacas;
	private JButton cancelar;
	
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ComprarEntrada() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(1000, 600));
		
		
		datos = new DatosComprar();
		butacas = new SeleccionButaca();
		cancelar = new JButton("Cancelar");
		
		
		layout.putConstraint(SpringLayout.WEST, datos, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, datos, 20, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, butacas, 40, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, butacas, 20, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, cancelar, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, cancelar, -80, SpringLayout.SOUTH, this);
		
		this.add(datos);
		this.add(butacas);
		this.add(cancelar);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		cancelar.addActionListener(c);
		datos.setControladores(c);
	}
	
	/**
	 * @return Panel DatosComprar
	 */
	public DatosComprar getDatosCPanel() {
		return this.datos;
	}
	
	/**
	 * @return Panel SeleccionButaca
	 */
	public SeleccionButaca getButacas() {
		return this.butacas;
	}
	
	
	/**
	 * Actualiza los datos de los paneles de comprar a partir de un evento
	 * 
	 * @param e evento 
	 */
	public void actualizar(Evento e) {
		this.datos.actualizar(e);
		this.butacas.getNumerada().actualizaTabla(null, null);
	}
	
	public void setPrecio(float p) {
		this.datos.setPrecio(p);
	}
	
	public JComboBox<Integer> getComboEntradas() {
		return this.butacas.getComboEntradas();
	}
	
	public int getNumEntradas() {
		return this.butacas.getNumEntradas();
	}
	
}

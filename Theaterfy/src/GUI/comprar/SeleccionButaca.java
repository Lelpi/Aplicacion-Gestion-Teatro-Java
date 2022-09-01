package GUI.comprar;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;


/**
 * panel que mostrara la seleccion de butacas dependiendo si el usuario selecciona numerada o no numerada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class SeleccionButaca extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JLabel encabezado;
	private ComprarNum numerada;
	private ComprarNoNum noNumerada;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public SeleccionButaca() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 400));
		
		encabezado=new JLabel("Selecciona las butacas");
		numerada = new ComprarNum();
		noNumerada = new ComprarNoNum();
		
		encabezado.setFont(new Font("Verdana", Font.BOLD, 14));
		layout.putConstraint(SpringLayout.WEST, encabezado, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, encabezado, 15, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.EAST, numerada, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, numerada, 20, SpringLayout.SOUTH, encabezado);
		layout.putConstraint(SpringLayout.EAST, noNumerada, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, noNumerada, 20, SpringLayout.SOUTH, encabezado);
		
		numerada.setVisible(false);
		noNumerada.setVisible(false);
		
		this.add(encabezado);
		this.add(numerada);
		this.add(noNumerada);
	}
	
	public ComprarNum getNumerada() {
		return this.numerada;
	}
	
	public ComprarNoNum getNoNumerada() {
		return this.noNumerada;
	}
	
	public int[] getSeleccionadas() {
		return this.numerada.getSeleccionadas();
	}
	
	/**
	 * @return la tabla de butacas de una zona numerada
	 */
	public DefaultTableModel modeloTabla() {
		return this.numerada.modeloTabla();
	}
	
	public JComboBox<Integer> getComboEntradas() {
		return this.noNumerada.getComboEntradas();
	}
	
	public int getNumEntradas() {
		return this.noNumerada.getNumEntradas();
	}
}

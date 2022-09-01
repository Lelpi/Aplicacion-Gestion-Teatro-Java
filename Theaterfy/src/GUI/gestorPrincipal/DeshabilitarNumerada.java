package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import theaterfy.zona.Butaca;
import theaterfy.zona.ZonaNumerada;

/**
 * subpanel para deshabilitar butacas numeradas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class DeshabilitarNumerada extends JPanel{
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scroll;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public DeshabilitarNumerada() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 600));
		
		JLabel encabezado=new JLabel("Selecciona las butacas");
		encabezado.setFont(new Font("Verdana", Font.BOLD, 14));
		layout.putConstraint(SpringLayout.WEST, encabezado, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, encabezado, 50, SpringLayout.NORTH, this);
		
		String[] titulo = {"Fila", "Columna"};
		Object[][] filas = {{}};
		
		modeloTabla = new DefaultTableModel(filas, titulo);
		tabla = new JTable(modeloTabla);
		scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(420, 130));
		
		layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, encabezado);
		layout.putConstraint(SpringLayout.NORTH, scroll, 20, SpringLayout.SOUTH, encabezado);
		
		
		this.add(scroll);
		this.add(encabezado);
	}
	/**
	 * actualiza la información de la tabla de butacas, filtrando las que ya estén deshabilitadas
	 * para las fechas seleccionadas
	 */
	public void actualizaTabla(ZonaNumerada z, GregorianCalendar fechaIni, GregorianCalendar fechaFin) {
		ArrayList<Butaca> butacas=new ArrayList<>();
		int i;
		
		for(Butaca[] b : z.getTotalButacas()) {
			for(Butaca butaca : b) {
				if(!butaca.isDeshabilitada(fechaIni, fechaFin)) {
					butacas.add(butaca);
				}
			}
		}
		
		this.modeloTabla.setRowCount(butacas.size());
		i=0;
		for(Butaca b : butacas) {
			this.modeloTabla.setValueAt(b.getY(), i, 0);
			this.modeloTabla.setValueAt(b.getX(), i, 1);
			i++;
		}
	}
	
	/**
	 * @return array con todas las posiciones seleccionadas en la tabla
	 */
	public int[] getSeleccionadas() {
		return this.tabla.getSelectedRows();
	}
	
	public DefaultTableModel modeloTabla() {
		return this.modeloTabla;
	}
}

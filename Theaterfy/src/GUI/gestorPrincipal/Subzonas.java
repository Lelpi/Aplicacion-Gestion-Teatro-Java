package GUI.gestorPrincipal;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import theaterfy.Theaterfy;
import theaterfy.zona.Zona;

/**
 * subpanel para crear las zonas mixtas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Subzonas extends JPanel{
	SpringLayout layout = new SpringLayout();
	private JLabel tit;
	private JLabel sub;
	private JLabel nombreZona;
	private JTextField nombre;
	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scroll;
	private JButton anyade;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public Subzonas() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 550));
		
		tit = new JLabel("Crear zona mixta");
		sub = new JLabel("Seleccione las subzonas a incluir:");
		nombre = new JTextField(12);
		nombreZona = new JLabel("Nombre de la zona mixta");
		anyade = new JButton("Añadir subzonas");
		
		String[] titulo = {"Zona"};
		Object[][] filas= {{}};
		modeloTabla = new DefaultTableModel(filas, titulo);
		
		this.setActualizarTabla();
		
		tabla = new JTable(modeloTabla);
		scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(270, 130));
		
		layout.putConstraint(SpringLayout.WEST, tit, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, tit, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, nombreZona, 40, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nombreZona, 20, SpringLayout.SOUTH, tit);
		layout.putConstraint(SpringLayout.WEST, nombre, 10, SpringLayout.EAST, nombreZona);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, nombreZona);
		layout.putConstraint(SpringLayout.WEST, sub, 0, SpringLayout.WEST, nombreZona);
		layout.putConstraint(SpringLayout.NORTH, sub, 10, SpringLayout.SOUTH, nombreZona);
		layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, sub);
		layout.putConstraint(SpringLayout.NORTH, scroll, 10, SpringLayout.SOUTH, sub);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, anyade, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, anyade, 20, SpringLayout.SOUTH, scroll);
		
		this.add(tit);
		this.add(nombreZona);
		this.add(nombre);
		this.add(sub);
		this.add(scroll);
		this.add(anyade);
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControladores(ActionListener c) {
		this.anyade.addActionListener(c);
	}
	
	public String getNombreMixta() {
		return this.nombre.getText();
	}
	
	/**
	 * @return un array de zonas con todas aquellas 
	 * seleccionadas en la tabla
	 */
	public Zona[] getArrayZonas(){
		int[] rows = tabla.getSelectedRows();
		Zona[] zonas = new Zona[rows.length];
		int j=0;
		for (int i=0; i<rows.length;i++) {
			for(Zona z : Theaterfy.getTheaterfy().getZonas()) {
				if(z.getNombre().equals((String)modeloTabla.getValueAt(rows[i], 0))) {
					zonas[j] = z;
					j++;
				}
			}
		}
		return zonas;
	}
	
	/**
	 * actualiza la información de los componentes de Subzonas
	 */
	public void setActualizarTabla() {
		modeloTabla.setRowCount(0);
		for(Zona z : Theaterfy.getTheaterfy().getZonas()){
			modeloTabla.addRow(new Object[] {z.getNombre()});
		}
	}

}

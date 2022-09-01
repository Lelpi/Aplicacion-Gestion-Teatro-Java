package GUI.comprar;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import theaterfy.Theaterfy;
import theaterfy.sucesos.Representacion;
import theaterfy.zona.Butaca;
import theaterfy.zona.Zona;
import theaterfy.zona.ZonaNumerada;

/**
 * panel que muestra la selección de butacas en el caso de que seleccione una zona numerada
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ComprarNum  extends JPanel{
	private SpringLayout layout = new SpringLayout();
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scroll;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ComprarNum() {
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(500, 400));
		
		String[] titulo = {"Fila", "Columna"};
		Object[][] filas = {{}};
		
		modeloTabla = new DefaultTableModel(filas, titulo);
		tabla = new JTable(modeloTabla);
		scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(420, 130));
		
		layout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, scroll, 10, SpringLayout.NORTH, this);
		
		
		this.add(scroll);
	}
	
	
	/**
	 * Función que actualiza la tabla de butacas cada vez que se selecciona una representacion y una zona
	 * 
	 * @param r
	 * @param zona
	 */
	public void actualizaTabla(Representacion r, ZonaNumerada zona) {
		boolean comprada;
		
		if(r==null && zona==null) {
			this.modeloTabla.setRowCount(0);
			return;
		}
		ArrayList<Butaca> butacas = new ArrayList<>();
		
		for(Zona z : Theaterfy.getTheaterfy().getZonasNoMixtas(Theaterfy.getTheaterfy().getZonas())) {
			if(z.equals(zona)) {	
				for (Butaca[] b : zona.getTotalButacas()) {
					for(Butaca but : b) {
						comprada=false;
						for(Butaca butacaComprada : r.getButacasCompradas()){
							if(but==butacaComprada || but.isDeshabilitada(r.getFecha(), r.getFecha())) {
								comprada=true;
							}
						}
						if(!comprada) {
							butacas.add(but);
						}
					}
				}
			}
		}
		this.modeloTabla.setRowCount(butacas.size());
		int i=0;
		for(Butaca b: butacas) {
			this.modeloTabla.setValueAt(b.getY(), i, 0);
			this.modeloTabla.setValueAt(b.getX(), i, 1);
			i++;
		}
	}
	
	public int[] getSeleccionadas() {
		return this.tabla.getSelectedRows();
	}
	
	public DefaultTableModel modeloTabla() {
		return this.modeloTabla;
	}
}

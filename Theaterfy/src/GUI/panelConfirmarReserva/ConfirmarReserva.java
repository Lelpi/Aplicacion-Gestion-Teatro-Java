package GUI.panelConfirmarReserva;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import theaterfy.Theaterfy;
import theaterfy.compras.Entrada;
import theaterfy.compras.EntradaReservada;
import theaterfy.usuarios.UsuarioRegistrado;

/**
 * JPanel, para confirmar las entradas reservadas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class ConfirmarReserva extends JPanel{
	
	private JTable tabla;
	DefaultTableModel modeloDatos;
	private JButton confirmar;
	private ConfirmarReservaPago confirmarReservaPago;
	private JButton inicio;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public ConfirmarReserva (){
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.setPreferredSize(new Dimension(1000,600));
		
		JLabel text1=new JLabel("Confirmar reservas");
		text1.setFont(new Font("Verdana",Font.BOLD,16));
		JLabel text2=new JLabel("Selecciona un evento para confirmar la/s entrada/s");
		String[] cabecera = {"Nombre", "Fecha", "Precio"};
		Object[][] filas = {{}};
		modeloDatos=new DefaultTableModel(filas, cabecera);
		tabla=new JTable(modeloDatos);
		
		confirmar=new JButton("Confirmar");
		JScrollPane scroll=new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(800, 200));
		
		confirmarReservaPago=new ConfirmarReservaPago();
		
		inicio=new JButton("Volver al inicio");
		
		this.add(inicio);
		this.add(text1);
		this.add(text2);
		this.add(scroll);
		this.add(confirmar);
		this.add(confirmarReservaPago);
		
		confirmarReservaPago.setVisible(false);
		
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.confirmar.addActionListener(c);
		this.confirmarReservaPago.setControlador(c);
		this.inicio.addActionListener(c);
	}
	
	public DefaultTableModel getModeloDatos() {
		return this.modeloDatos;
	}
	
	public int getFila() {
		return this.tabla.getSelectedRow();
	}
	
	/**
	 * realiza las actualizaciones para hacer visible el panel del pago de confirmar reservas
	 * @param flag valor de visibilidad
	 * @param maxEntradas numero maximo de entradas que se pueden confirmar
	 * @param nombreEvento
	 */
	public void setPagarVisible(boolean flag, int maxEntradas, String nombreEvento) {
		this.confirmarReservaPago.setVisible(flag);
		this.confirmarReservaPago.setMaxEntradas(maxEntradas);
		this.confirmarReservaPago.setNombreEvento(nombreEvento);
	}
	
	public int getMaxEntradas() {
		return confirmarReservaPago.getMaxEntradas();
	}
	
	public String getNumEntradas() {
		return this.confirmarReservaPago.getNumEntradas();
	}
	
	public String getTarjeta() {
		return this.confirmarReservaPago.getTarjeta();
	}
	
	/**
	 * actualiza la información de los componentes de ConfirmarReserva
	 */
	public void actualizar() {
		UsuarioRegistrado u=Theaterfy.getTheaterfy().getUsuarioActual();
		ArrayList<ArrayList<String>> matriz=new ArrayList<>();
		ArrayList<String> aux;
		boolean isAlready;
		int i;
		u.limpiarReservas();
		
		for(Entrada e : u.getEntradas()) {
			if(e.getClass()==EntradaReservada.class) {
				isAlready=false;
				if(!matriz.isEmpty()) {
					for(ArrayList<String> array : matriz) {
						if(array.get(0).equals(e.getEventName()) && array.get(1).equals(e.getEventDate())) {
							isAlready=true;
							break;
						}
					}
					
				}
				if(isAlready==false) {
					aux=new ArrayList<String>();
					aux.add(e.getEventName());
					aux.add(e.getEventDate());
					aux.add(e.getPrecio().getPrecio()+" €");
					matriz.add(aux);
				}
			}
		}
		
		this.modeloDatos.setRowCount(matriz.size());
		i=0;
		for(ArrayList<String> array : matriz) {
			modeloDatos.setValueAt(array.get(0), i, 0);
			modeloDatos.setValueAt(array.get(1), i, 1);
			modeloDatos.setValueAt(array.get(2), i, 2);
			
			i++;
		}
		
		confirmarReservaPago.setVisible(false);
	}
	
}

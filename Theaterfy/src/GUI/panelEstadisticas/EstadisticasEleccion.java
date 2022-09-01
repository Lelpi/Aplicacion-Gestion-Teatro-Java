package GUI.panelEstadisticas;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * panel que muestra los botones de las diferentes estad�sticas
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Juli�n oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class EstadisticasEleccion extends JPanel{
	private JButton botones[]=new JButton[4];
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public EstadisticasEleccion() {
		this.setLayout(new GridLayout(0,1,0,10));
		String nombreBotones[]= {"ocupaci�n por representaci�n", "ocupaci�n por zona", "remuneraci�n en cada evento", "remuneraci�n por zona"};
		
		for(int i=0;i<4;i++) {
			botones[i]=new JButton(nombreBotones[i]);
		}
		
		
		
		for(int i=0;i<4;i++) {
			this.add(botones[i]);
		}
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		for(int i=0;i<4;i++) {
			this.botones[i].addActionListener(c);
		}
	}
}

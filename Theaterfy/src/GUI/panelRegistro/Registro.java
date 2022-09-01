package GUI.panelRegistro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * panel para que el usuario registre una cuenta
 * 
 * @author Paula Golpe paula.golpe@estudiante.uam.es
 * @author Oriol Julián oriol.julian@estudiante.uam.es
 * @author Luis Lepore luis.lepore@estudiante.uam.es
 *
 */
public class Registro extends JPanel{
	private JTextField usuario;
	private JPasswordField contrasenia;
	private JButton boton;
	private JButton inicio;
	
	/**
	 * Constructor, construye el JPanel con sus componentes
	 */
	public Registro() {
		SpringLayout l=new SpringLayout();
		this.setLayout(l);
		this.setPreferredSize(new Dimension(1000,600));
		JLabel text1=new JLabel("Crea una cuenta");
		text1.setFont(new Font("Verdana",Font.BOLD,16));
		JLabel text2=new JLabel("Introduce un nombre de usuario");
		text2.setFont(new Font("Dialog",Font.BOLD,12));
		JLabel text3=new JLabel("Introduce una contraseña");
		text3.setFont(new Font("Dialog",Font.BOLD,12));
		usuario=new JTextField(18);
		contrasenia=new JPasswordField(18);
		boton=new JButton("Crear");
		inicio=new JButton("Volver al inicio");
		
		l.putConstraint(SpringLayout.NORTH,text1,166,SpringLayout.NORTH,this);
		l.putConstraint(SpringLayout.NORTH,text2,71,SpringLayout.SOUTH,text1);
		l.putConstraint(SpringLayout.NORTH,usuario,33,SpringLayout.SOUTH,text2);
		l.putConstraint(SpringLayout.NORTH,text3,45,SpringLayout.SOUTH,usuario);
		l.putConstraint(SpringLayout.NORTH,contrasenia,33,SpringLayout.SOUTH,text3);
		l.putConstraint(SpringLayout.NORTH,boton,25,SpringLayout.SOUTH,contrasenia);
		l.putConstraint(SpringLayout.SOUTH, inicio, -50, SpringLayout.SOUTH, this);
		
		l.putConstraint(SpringLayout.WEST,text1,400,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,text2,380,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,usuario,371,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,text3,401,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,contrasenia,371,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST,boton,439,SpringLayout.WEST,this);
		l.putConstraint(SpringLayout.WEST, inicio, 50, SpringLayout.WEST, this);
		
		this.add(text1);
		this.add(text2);
		this.add(text3);
		this.add(usuario);
		this.add(contrasenia);
		this.add(boton);
		this.add(inicio);
	}
	
	public String getNick() {
		return this.usuario.getText();
	}
	public String getContrasenia() {
		String string="";
		for(char c : this.contrasenia.getPassword()) {
			string+=c;
		}
		return string;
	}
	
	/**
	 * asocia a los componentes un controlador
	 * @param c
	 */
	public void setControlador(ActionListener c) {
		this.boton.addActionListener(c);
		this.inicio.addActionListener(c);
	}
}

package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelOpciones extends JPanel implements ActionListener{
	
	/**
	 * Constante que modela el comando de jugar
	 */
	private static final String JUGAR = "Play";
	
	/**
	 * Constante que modela el comando de practicar
	 */
	private static final String PRACTICAR = "Practice";
	
	/**
	 * Constante que modela el comando de leaderboard
	 */
	private static final String LEADERBOARD = "Leaderboard";
	
	/**
	 * Constante que modela el comando de leaderboard
	 */
	private static final String ABOUT = "About";
	
	/**
	 * Atributo que modela el botón para jugar
	 */
	private JButton btnJugar;
	
	/**
	 * Atributo que modela el botón para practicar
	 */
	private JButton btnPracticar;
	
	/**
	 * Atributo que modela el botón para ver el leaderboard
	 */
	private JButton btnLeaderboard;
	
	/**
	 * Atributo que modela el botón para ver más información sobre el juego
	 */
	private JButton btnAbout;
	
	/**
	 * Atributo que modela la relación con la interfaz principal
	 */
	private InterfazWackAMole principal;
	
	/**
	 * Constructor del panel que contiene las opciones del juego
	 * @param interfaz Relación con la interfaz principal
 	 */
	public PanelOpciones(InterfazWackAMole interfaz) {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(4, 1));
		Font fuente = new Font("Impact", Font.PLAIN, 30);
		principal = interfaz;
		
		btnJugar = new JButton(JUGAR);
		btnJugar.setActionCommand(JUGAR);
		btnJugar.addActionListener(this);
		btnJugar.setFont(fuente);
		btnJugar.setBackground(new Color(0,0,0,0xff));
		btnJugar.setFocusable(false);
		btnJugar.setForeground(Color.WHITE);
		add(btnJugar);
		
		btnPracticar = new JButton(PRACTICAR);
		btnPracticar.setActionCommand(PRACTICAR);
		btnPracticar.addActionListener(this);
		btnPracticar.setFont(fuente);
		btnPracticar.setBackground(new Color(0,0,0,0xff));
		btnPracticar.setFocusable(false);
		btnPracticar.setForeground(Color.WHITE);
		add(btnPracticar);
		
		btnLeaderboard = new JButton(LEADERBOARD);
		btnLeaderboard.setActionCommand(LEADERBOARD);
		btnLeaderboard.addActionListener(this);
		btnLeaderboard.setFont(fuente);
		btnLeaderboard.setBackground(new Color(0,0,0,0xff));
		btnLeaderboard.setFocusable(false);
		btnLeaderboard.setForeground(Color.WHITE);
		add(btnLeaderboard);
		
		btnAbout = new JButton(ABOUT);
		btnAbout.setActionCommand(ABOUT);
		btnAbout.addActionListener(this);
		btnAbout.setFont(fuente);
		btnAbout.setBackground(new Color(0,0,0,0xff));
		btnAbout.setFocusable(false);
		btnAbout.setForeground(Color.WHITE);
		add(btnAbout);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		if(comando.equals(JUGAR)){
			principal.jugar();
		}
		else if(comando.equals(PRACTICAR)){
			principal.practicar();
		}
		else if(comando.equals(LEADERBOARD)){
			principal.leaderboard();
		}
		else if(comando.equals(ABOUT)){
			principal.about();
		}
	}

}

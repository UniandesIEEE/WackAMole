package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mundo.Jugador;

public class PanelJuego extends JPanel{
	
	private JLabel lblTiempo;
	
	private JLabel lblTopo;
	
	private JLabel lblScore;
	
	private JLabel lblPlayer;
	
	public PanelJuego() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		Font fuente = new Font("Impact", Font.PLAIN, 60);
		Font fuente1 = new Font("Impact", Font.PLAIN, 40);
		
		lblTiempo = new JLabel();
		lblTiempo.setFont(fuente);
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setVerticalAlignment(SwingConstants.CENTER);
		add(lblTiempo, BorderLayout.NORTH);
		
		lblTopo = new JLabel(new ImageIcon("data/mole.png"));
		add(lblTopo,BorderLayout.WEST);
		
		JPanel pnlScore = new JPanel();
		pnlScore.setLayout(new GridLayout(4, 1));
		pnlScore.setBackground(Color.WHITE);
		add(pnlScore, BorderLayout.CENTER);
		
		JLabel lbl = new JLabel("Player");
		lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setVerticalAlignment(SwingConstants.CENTER);
		lbl.setFont(fuente);
		pnlScore.add(lbl);
		
		lblPlayer = new JLabel();
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setVerticalAlignment(SwingConstants.CENTER);
		lblPlayer.setFont(fuente1);
		lblPlayer.setBorder(BorderFactory.createLoweredBevelBorder());
		pnlScore.add(lblPlayer);
		
		JLabel lbl1 = new JLabel("Score");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setVerticalAlignment(SwingConstants.CENTER);
		lbl1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lbl1.setFont(fuente);
		pnlScore.add(lbl1);
		
		lblScore = new JLabel();
		lblScore.setFont(fuente1);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
		lblScore.setBorder(BorderFactory.createLoweredBevelBorder());
		pnlScore.add(lblScore);
		
	}
	
	/**
	 * Método que actualiza el tiempo en el GUI
	 * @param pTime Tiempo especificado desde el thread
	 */
	public void actualizarTiempo(int pTime){
		String time = "";
		if(pTime<10){
			time = "0"+pTime;
		}else{
			time = ""+pTime;
		}
		lblTiempo.setText(time);
	}

	public synchronized void actualizarPuntaje(String string) {
		lblScore.setText(string);
	}

	public void inicio(Jugador jug) {
		lblScore.setText(""+jug.darPuntaje());
		lblPlayer.setText(jug.darNombre());
	}
}

package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelJuego extends JPanel{
	
	private JLabel lblTiempo;
	
	private JLabel lblTopo;
	
	private JLabel lblScore;
	
	public PanelJuego() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		Font fuente = new Font("Impact", Font.PLAIN, 60);
		
		lblTiempo = new JLabel();
		lblTiempo.setFont(fuente);
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setVerticalAlignment(SwingConstants.CENTER);
		add(lblTiempo, BorderLayout.NORTH);
		
		lblTopo = new JLabel(new ImageIcon("data/mole.png"));
		add(lblTopo,BorderLayout.WEST);
		
		JPanel pnlScore = new JPanel();
		pnlScore.setLayout(new GridLayout(2, 1));
		pnlScore.setBackground(Color.WHITE);
		add(pnlScore, BorderLayout.CENTER);
		
		JLabel lbl = new JLabel("Score");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setVerticalAlignment(SwingConstants.CENTER);
		lbl.setFont(fuente);
		pnlScore.add(lbl);
		
		lblScore = new JLabel();
		lblScore.setFont(fuente);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setVerticalAlignment(SwingConstants.CENTER);
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
}

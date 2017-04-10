package interfaz;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBanner extends JPanel{
	
	/**
	 * Contructor del panel con la imagen del banner
	 */
	public PanelBanner() {
		JLabel lbl = new JLabel(new ImageIcon("data/banner.png"));
		add(lbl);
		setBackground(Color.BLACK);
	}

}

package sk.tsystems.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DrawPanel() {
		
		JPanel panel = new JPanel();
		add(panel);

	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 400, 300);
	}

}

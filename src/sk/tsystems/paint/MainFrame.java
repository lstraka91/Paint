package sk.tsystems.paint;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setUndecorated(true);.
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		 getContentPane().add(new MainPanel());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

}

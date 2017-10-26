package sk.tsystems.paint;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
	
	private void loadImageFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	}

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

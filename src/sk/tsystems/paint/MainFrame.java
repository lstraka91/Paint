package sk.tsystems.paint;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private JPanel navigation_panel;
	private JMenuItem menuSave;
	private JMenuItem menuLoad;
	private JMenuItem menuExit;
	private JScrollPane scrollPane;
	private JPanel drawPanel;
	
	public MainFrame() {
		super("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setUndecorated(true);.
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().add(new MainPanel());

		initPanel();
	}
	
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
	
	private void initPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		navigation_panel = new JPanel();
		contentPane.add(navigation_panel, BorderLayout.NORTH);
		navigation_panel.setLayout(new BorderLayout(0, 0));

		JPanel menu_panel = new JPanel();
		navigation_panel.add(menu_panel, BorderLayout.CENTER);
		menu_panel.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		menu_panel.add(menuBar, BorderLayout.WEST);

		JMenu mnFiles = new JMenu("File");
		menuBar.add(mnFiles);

		menuSave = new JMenuItem("Save");
		mnFiles.add(menuSave);

		menuLoad = new JMenuItem("Load");
		mnFiles.add(menuLoad);

		menuExit = new JMenuItem("Exit");
		mnFiles.add(menuExit);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		drawPanel = new DrawPanel();
		scrollPane.setViewportView(drawPanel);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

}

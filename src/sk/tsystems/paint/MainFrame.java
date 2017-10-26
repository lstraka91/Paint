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
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private JPanel navigation_panel;
	private JMenuItem menuSave;
	private JMenuItem menuLoad;
	private JMenuItem menuExit;
	private JScrollPane scrollPane;
	private JPanel paintPanel;
	private ButtonGroup color_group;
	private ButtonGroup shape_group;
	
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

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		menuSave = new JMenuItem("Save");
		fileMenu.add(menuSave);

		menuLoad = new JMenuItem("Load");
		fileMenu.add(menuLoad);

		menuExit = new JMenuItem("Exit");
		fileMenu.add(menuExit);
		
		JPanel panel = new JPanel();
		
		JButton saveBttn = new JButton("New button");
		panel.add(saveBttn);
		
		JButton undoBttn = new JButton("New button");
		panel.add(undoBttn);
		
		JButton redoBttn = new JButton("New button");
		panel.add(redoBttn);
		
		JLabel fileName = new JLabel("File name");
		GroupLayout gl_menu_panel = new GroupLayout(menu_panel);
		gl_menu_panel.setHorizontalGroup(
			gl_menu_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_panel.createSequentialGroup()
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 852, GroupLayout.PREFERRED_SIZE)
					.addComponent(fileName))
		);
		gl_menu_panel.setVerticalGroup(
			gl_menu_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menu_panel.createSequentialGroup()
					.addGap(17)
					.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_menu_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(fileName))
		);
		menu_panel.setLayout(gl_menu_panel);
		
		JTabbedPane tabbed_panel = new JTabbedPane(JTabbedPane.TOP);
		navigation_panel.add(tabbed_panel, BorderLayout.SOUTH);
		
		JPanel home_tab = new JPanel();
		tabbed_panel.addTab("Home", null, home_tab, null);
		
		JPanel panel_1 = new JPanel();
		home_tab.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JButton paste_bttn = new JButton("Paste");
		panel_1.add(paste_bttn);
		
		JMenu paste_menu = new JMenu("Paste");
		panel_1.add(paste_menu);
		
		JMenuItem paste_item = new JMenuItem("Paste");
		paste_menu.add(paste_item);
		
		JMenuItem paste_from_item = new JMenuItem("Paste from");
		paste_menu.add(paste_from_item);
		
		JPanel panel_2 = new JPanel();
		home_tab.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JButton cut_bttn = new JButton("Cut");
		panel_2.add(cut_bttn);
		
		JButton copy_bttn = new JButton("Copy");
		panel_2.add(copy_bttn);
		
		JPanel panel_5 = new JPanel();
		home_tab.add(panel_5);
		
		JRadioButton shape_radioButton_20 = new JRadioButton("");
		shape_radioButton_20.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_20);
		shape_group.add(shape_radioButton_20);
		
		JRadioButton shape_radioButton_21 = new JRadioButton("");
		shape_radioButton_21.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_21);
		shape_group.add(shape_radioButton_21);
		
		JRadioButton shape_radioButton_22 = new JRadioButton("");
		shape_radioButton_22.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_22);
		shape_group.add(shape_radioButton_22);
		
		JRadioButton shape_radioButton_23 = new JRadioButton("");
		shape_radioButton_23.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_23);
		shape_group.add(shape_radioButton_23);
		
		JRadioButton shape_radioButton_24 = new JRadioButton("");
		shape_radioButton_24.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_24);
		shape_group.add(shape_radioButton_24);
		
		JRadioButton shape_radioButton_25 = new JRadioButton("");
		shape_radioButton_25.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_25);
		shape_group.add(shape_radioButton_25);
		
		JRadioButton shape_radioButton_26 = new JRadioButton("");
		shape_radioButton_26.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_26);
		shape_group.add(shape_radioButton_26);
		
		JRadioButton shape_radioButton_27 = new JRadioButton("");
		shape_radioButton_27.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_27);
		shape_group.add(shape_radioButton_27);
		
		JRadioButton shape_radioButton_28 = new JRadioButton("");
		shape_radioButton_28.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_28);
		shape_group.add(shape_radioButton_28);
		
		JRadioButton shape_radioButton_29 = new JRadioButton("");
		shape_radioButton_29.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_29);
		shape_group.add(shape_radioButton_29);
		
		JPanel panel_3 = new JPanel();
		home_tab.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel color_1_lbl = new JLabel("Color 1");
		color_1_lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.add(color_1_lbl);
		
		JLabel color_2_lbl = new JLabel("Color 2");
		panel_3.add(color_2_lbl);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 10, 0, 0));
		
		color_group = new ButtonGroup();
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBackground(Color.BLACK);
		panel_4.add(radioButton);
		color_group.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(Color.GRAY);
		panel_4.add(radioButton_1);
		color_group.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBackground(new Color(165, 42, 42));
		panel_4.add(radioButton_2);
		color_group.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("");
		radioButton_3.setBackground(Color.RED);
		panel_4.add(radioButton_3);
		color_group.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("");
		radioButton_4.setBackground(new Color(255, 165, 0));
		panel_4.add(radioButton_4);
		color_group.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("");
		radioButton_5.setBackground(Color.YELLOW);
		panel_4.add(radioButton_5);
		color_group.add(radioButton_5);
		
		JRadioButton radioButton_6 = new JRadioButton("");
		radioButton_6.setBackground(new Color(34, 139, 34));
		panel_4.add(radioButton_6);
		color_group.add(radioButton_6);
		
		JRadioButton radioButton_7 = new JRadioButton("");
		radioButton_7.setBackground(new Color(30, 144, 255));
		panel_4.add(radioButton_7);
		color_group.add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("");
		radioButton_8.setBackground(new Color(0, 0, 255));
		panel_4.add(radioButton_8);
		color_group.add(radioButton_8);
		
		JRadioButton radioButton_9 = new JRadioButton("");
		radioButton_9.setBackground(new Color(139, 0, 139));
		panel_4.add(radioButton_9);
		color_group.add(radioButton_9);
		
		JRadioButton radioButton_10 = new JRadioButton("");
		radioButton_10.setBackground(Color.WHITE);
		panel_4.add(radioButton_10);
		color_group.add(radioButton_10);
		
		JRadioButton radioButton_11 = new JRadioButton("");
		radioButton_11.setBackground(Color.LIGHT_GRAY);
		panel_4.add(radioButton_11);
		color_group.add(radioButton_11);
		
		JRadioButton radioButton_12 = new JRadioButton("");
		radioButton_12.setBackground(new Color(184, 134, 11));
		panel_4.add(radioButton_12);
		color_group.add(radioButton_12);
		
		JRadioButton radioButton_13 = new JRadioButton("");
		radioButton_13.setBackground(new Color(255, 192, 203));
		panel_4.add(radioButton_13);
		color_group.add(radioButton_13);
		
		JRadioButton radioButton_14 = new JRadioButton("");
		radioButton_14.setBackground(new Color(255, 218, 185));
		panel_4.add(radioButton_14);
		color_group.add(radioButton_14);
		
		JRadioButton radioButton_15 = new JRadioButton("");
		radioButton_15.setBackground(new Color(250, 240, 230));
		panel_4.add(radioButton_15);
		color_group.add(radioButton_15);
		
		JRadioButton radioButton_16 = new JRadioButton("");
		radioButton_16.setBackground(new Color(152, 251, 152));
		panel_4.add(radioButton_16);
		color_group.add(radioButton_16);
		
		JRadioButton radioButton_17 = new JRadioButton("");
		radioButton_17.setBackground(new Color(135, 206, 250));
		panel_4.add(radioButton_17);
		color_group.add(radioButton_17);
		
		JRadioButton radioButton_18 = new JRadioButton("");
		radioButton_18.setBackground(new Color(70, 130, 180));
		panel_4.add(radioButton_18);
		color_group.add(radioButton_18);
		
		JRadioButton radioButton_19 = new JRadioButton("");
		radioButton_19.setBackground(new Color(221, 160, 221));
		panel_4.add(radioButton_19);
		color_group.add(radioButton_19);
		
		JRadioButton radioButton_20 = new JRadioButton("");
		radioButton_20.setBackground(SystemColor.menu);
		panel_4.add(radioButton_20);
		color_group.add(radioButton_20);
		
		JRadioButton radioButton_21 = new JRadioButton("");
		radioButton_21.setBackground(SystemColor.menu);
		panel_4.add(radioButton_21);
		color_group.add(radioButton_21);
		
		JRadioButton radioButton_22 = new JRadioButton("");
		radioButton_22.setBackground(SystemColor.menu);
		panel_4.add(radioButton_22);
		color_group.add(radioButton_22);
		
		JRadioButton radioButton_23 = new JRadioButton("");
		radioButton_23.setBackground(SystemColor.menu);
		panel_4.add(radioButton_23);
		color_group.add(radioButton_23);
		
		JRadioButton radioButton_24 = new JRadioButton("");
		radioButton_24.setBackground(SystemColor.menu);
		panel_4.add(radioButton_24);
		color_group.add(radioButton_24);
		
		JRadioButton radioButton_25 = new JRadioButton("");
		radioButton_25.setBackground(SystemColor.menu);
		panel_4.add(radioButton_25);
		color_group.add(radioButton_25);
		
		JRadioButton radioButton_26 = new JRadioButton("");
		radioButton_26.setBackground(SystemColor.menu);
		panel_4.add(radioButton_26);
		color_group.add(radioButton_26);
		
		JRadioButton radioButton_27 = new JRadioButton("");
		radioButton_27.setBackground(SystemColor.menu);
		panel_4.add(radioButton_27);
		color_group.add(radioButton_27);
		
		JRadioButton radioButton_28 = new JRadioButton("");
		radioButton_28.setBackground(SystemColor.menu);
		panel_4.add(radioButton_28);
		color_group.add(radioButton_28);
		
		JRadioButton radioButton_29 = new JRadioButton("");
		radioButton_29.setBackground(SystemColor.menu);
		panel_4.add(radioButton_29);
		color_group.add(radioButton_29);
		
		JLabel edit_colors_lbl = new JLabel("Edit colors");
		panel_3.add(edit_colors_lbl);
		
		shape_group = new ButtonGroup();
		
		JPanel view_tab = new JPanel();
		tabbed_panel.addTab("View", null, view_tab, null);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.SOUTH);
		
		paintPanel = new PaintPanel();
		scrollPane.setViewportView(paintPanel);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}
}

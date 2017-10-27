package sk.tsystems.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import sk.tsystems.paint.shapes.CircleShape;
import sk.tsystems.paint.shapes.EllipseShape;
import sk.tsystems.paint.shapes.LineShape;
import sk.tsystems.paint.shapes.RectangleShape;
import sk.tsystems.paint.shapes.SquareShape;
import sk.tsystems.utils.CustomMenuListener;
import sk.tsystems.utils.FileUtils;
import sk.tsystems.utils.JFontChooser;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel navigation_panel;
	private JMenuItem menuSave;
	private JMenuItem menuLoad;
	private JMenuItem menuExit;
	private JScrollPane scrollPane;
	private PaintPanel paintPanel;
	private ButtonGroup color_group;
	private ButtonGroup shape_group;
	private ButtonGroup color_type_group;
	private Color borderColor = Color.BLACK;
	private Color fillColor = null;

	public MainFrame() {
		super("Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setUndecorated(true);.
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		getContentPane().add(new MainPanel());
		
		shape_group = new ButtonGroup();

		color_type_group = new ButtonGroup();

		color_group = new ButtonGroup();

		initPanel();
	}

	private void loadImageFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		}
	}

	private void initPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		CustomMenuListener menuListener = new CustomMenuListener(this);
		menuSave = new JMenuItem("Save");
		menuSave.addActionListener(menuListener);
		fileMenu.add(menuSave);

		menuLoad = new JMenuItem("Load");
		menuLoad.addActionListener(menuListener);
		fileMenu.add(menuLoad);

		menuExit = new JMenuItem("Exit");
		fileMenu.add(menuExit);

		setJMenuBar(menuBar);

		navigation_panel = new JPanel();
		contentPane.add(navigation_panel, BorderLayout.NORTH);
		navigation_panel.setLayout(new BorderLayout(0, 0));

		JPanel menu_panel = new JPanel();
		navigation_panel.add(menu_panel, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		menu_panel.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));

		JButton paint_bttn = new JButton("Paint");
		panel_2.add(paint_bttn);

		paint_bttn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				paintPanel.setEditMode(false);

			}
		});

		JButton edit_bttn = new JButton("Edit");
		panel_2.add(edit_bttn);

		JButton btnSelect = new JButton("Select");
		btnSelect.setEnabled(false);
		panel_2.add(btnSelect);

		JPanel panel_7 = new JPanel();
		menu_panel.add(panel_7);
		panel_7.setLayout(new GridLayout(3, 1, 0, 0));

		JButton btnFotn = new JButton("Font");
		btnFotn.addActionListener(menuListener);
		panel_7.add(btnFotn);

		JButton rotate_btn = new JButton("Rotate");
		panel_7.add(rotate_btn);
		
		JButton btnSize = new JButton("Size");
		panel_7.add(btnSize);

		JPanel panel_5 = new JPanel();
		menu_panel.add(panel_5);
		panel_5.setLayout(new GridLayout(3, 2, 0, 0));

		JRadioButton shape_radioButton_0 = new JRadioButton("Line");
		shape_radioButton_0.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_0);
		shape_group.add(shape_radioButton_0);

		JRadioButton shape_radioButton_1 = new JRadioButton("Circle");
		shape_radioButton_1.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_1);
		shape_group.add(shape_radioButton_1);

		JRadioButton shape_radioButton_2 = new JRadioButton("Ellipse");
		shape_radioButton_2.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_2);
		shape_group.add(shape_radioButton_2);

		JRadioButton shape_radioButton_3 = new JRadioButton("Square");
		shape_radioButton_3.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_3);
		shape_group.add(shape_radioButton_3);

		JRadioButton shape_radioButton_4 = new JRadioButton("Rectangle");
		shape_radioButton_4.setBackground(SystemColor.menu);
		panel_5.add(shape_radioButton_4);
		shape_group.add(shape_radioButton_4);

		JPanel panel_3 = new JPanel();
		menu_panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(3, 1, 0, 0));

		JRadioButton fill__radioButton = new JRadioButton("Fill color");
		fill__radioButton.setBackground(fillColor);
		fill__radioButton.setSelected(true);
		panel_6.add(fill__radioButton);
		color_type_group.add(fill__radioButton);

		JRadioButton border__radioButton = new JRadioButton("Border color");
		border__radioButton.setBackground(borderColor);
		panel_6.add(border__radioButton);
		color_type_group.add(border__radioButton);

		JButton edit_colors_btn = new JButton("Edit colors");
		panel_6.add(edit_colors_btn);
		edit_colors_btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				showColorPickDialog();
				
				
			}
		});
		
		/*rotate_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent component = (JComponent) e.getSource();
				//menu.show(component, 0, component.getHeight());
			}
		});
*/
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 10, 0, 0));

		JRadioButton radioButton_0 = new JRadioButton("");
		radioButton_0.setSelected(true);
		radioButton_0.setBackground(Color.BLACK);
		panel_4.add(radioButton_0);
		color_group.add(radioButton_0);

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
		
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem item = new JMenuItem("Rotate left");
		menu.add(item);
		
		item = new JMenuItem("Rotate right");
		menu.add(item);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		item = new JMenuItem("Rotate up");
		menu.add(item);
		
		item = new JMenuItem("Rotate down");
		menu.add(item);
		
		rotate_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent component = (JComponent) e.getSource();
				menu.show(component, 0, component.getHeight());
			}
		});
		
		JPopupMenu menu2 = new JPopupMenu();
		
		item = new JMenuItem("1");
		item.addActionListener(menuListener);
		menu2.add(item);
		
		item = new JMenuItem("2");
		item.addActionListener(menuListener);
		menu2.add(item);
		
		separator = new JSeparator();
		menu2.add(separator);
	
		item = new JMenuItem("3");
		item.addActionListener(menuListener);
		menu2.add(item);
		
		item = new JMenuItem("4");
		item.addActionListener(menuListener);
		menu2.add(item);
		
		item = new JMenuItem("5");
		item.addActionListener(menuListener);
		menu2.add(item);
				
		
		
		btnSize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent component = (JComponent) e.getSource();
				menu2.show(component, 0, component.getHeight());
			}
		});
		
		Component[] itemsArray = menu2.getComponents();
		for (int i = 0; i < itemsArray.length; i++) {
			Component sizeItem = itemsArray[i];
			sizeItem.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					String width = ((JMenuItem) sizeItem).getText();
					paintPanel.setBorderWidth(Float.valueOf(width));
					System.out.println(">>>>>>>>>>>>>>" + Float.valueOf(width));
				}
			});
		}
		
		

		edit_bttn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				paintPanel.setEditMode(true);

			}
		});

		for (Enumeration<AbstractButton> buttons = shape_group.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			button.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					JRadioButton newSelected = ((JRadioButton) e.getComponent());
					// newSelected.setSelected(true);
					Shape newShape = identifyShape(newSelected);
					paintPanel.setChoosenShape(newShape);
					System.out.println(">>>>>>>>>> new shape: " + newShape.getClass().toString());

				}

			});
		}
		
		for (Enumeration<AbstractButton> buttons = color_group.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			button.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					JRadioButton newSelected = ((JRadioButton) e.getComponent());
					if (fill__radioButton.isSelected()) {
						paintPanel.setChoosenColor(newSelected.getBackground());
					//	fillColor = newSelected.getBackground();
						System.out.println("Fill color choosen: " + newSelected.getBackground().toString());
					} else {
						//borderColor = newSelected.getBackground();
						paintPanel.setChoosenBorderColor(newSelected.getBackground());
						System.out.println("Border color choosen: " + newSelected.getBackground().toString());
					}
					// newSelected.setSelected(true);
					paintPanel.setChoosenColor(newSelected.getBackground());

				}
			});
		}

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		paintPanel = new PaintPanel();
		scrollPane.setViewportView(paintPanel);

	}

	private Shape identifyShape(JRadioButton newSelected) {
		switch (newSelected.getText()) {
		case "Line":
			return new LineShape();
		case "Circle":
			return new CircleShape();
		case "Ellipse":
			return new EllipseShape();
		case "Square":
			return new SquareShape();
		case "Rectangle":
			return new RectangleShape();
		default:
			return null;
		}

	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}
	
	public void showColorPickDialog() {
		JColorChooser jfc = new JColorChooser();
		jfc.showDialog(this, "Random string", Color.BLACK);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	public void openFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
			List list = FileUtils.loadPainting(chooser.getSelectedFile());
			if (list != null) {
				((PaintPanel) paintPanel).setShapesList(list);
				paintPanel.repaint();
			} else {
				System.out.println("Loading of drawing objects failed");
			}
		}
	}

	public void saveFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Enter filename", "drw");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to save this file: " + chooser.getSelectedFile());
			int result = FileUtils.savePainting(((PaintPanel) paintPanel).getShapesList(), chooser.getSelectedFile());

		}
	}

	public void choseFont() {
		JFontChooser jfc = new JFontChooser();
		jfc.showDialog(this);
	}

	public void setWidth(int i) {
		paintPanel.setBorderWidth((float)i);
		System.out.println(">>>>>>" + i);
	}
}

package sk.tsystems.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import sk.tsystems.paint.MainFrame;

public class CustomMenuListener implements ActionListener {

	private MainFrame parentWindow;

	public CustomMenuListener(MainFrame frame) {
		this.parentWindow = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Popup menu item [" + e.getActionCommand() + "] was pressed.");
		if (e.getActionCommand().equalsIgnoreCase("Load")) {
			parentWindow.openFile();
		} else if (e.getActionCommand().equalsIgnoreCase("Save")) {
			parentWindow.saveFile();
		} else if (e.getActionCommand().equalsIgnoreCase("Font")) {
			parentWindow.choseFont();
		} else if (e.getActionCommand().equalsIgnoreCase("1")) {
			parentWindow.setWidth(1);
		} else if (e.getActionCommand().equalsIgnoreCase("2")) {
			parentWindow.setWidth(2);
		} else if (e.getActionCommand().equalsIgnoreCase("3")) {
			parentWindow.setWidth(3);
		} else if (e.getActionCommand().equalsIgnoreCase("4")) {
			parentWindow.setWidth(4);
		} else if (e.getActionCommand().equalsIgnoreCase("5")) {
			parentWindow.setWidth(5);
		}

	}

}

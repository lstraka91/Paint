package sk.tsystems.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import sk.tsystems.paint.MainFrame;

public class CustomMenuListener implements ActionListener{

	private MainFrame parentWindow;
	public  CustomMenuListener(MainFrame frame) {
		this.parentWindow=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Popup menu item ["
	            + e.getActionCommand() + "] was pressed.");
		if(e.getActionCommand().equalsIgnoreCase("Load")){
			parentWindow.openFile();			
		}else if(e.getActionCommand().equalsIgnoreCase("Save")){
			parentWindow.saveFile();
		}
	}
			

}

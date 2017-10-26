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
		// TODO Auto-generated method stub
		parentWindow.openFile();	}

}

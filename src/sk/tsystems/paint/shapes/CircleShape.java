package sk.tsystems.paint.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import sk.tsystems.paint.Shape;

public class CircleShape extends Shape{
	java.awt.Shape shape;
	
	public CircleShape(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		shape=new Ellipse2D.Double(0, 0, width, width);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(getColorShape());
		g2.fill(shape);
		
	}

}

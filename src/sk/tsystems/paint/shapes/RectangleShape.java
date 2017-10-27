package sk.tsystems.paint.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import sk.tsystems.paint.Shape;

public class RectangleShape extends Shape {

	public RectangleShape(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		
	}

	public RectangleShape() {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(getColorShape());
		Rectangle2D.Double shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
		g2.fill(shape);
	}
}

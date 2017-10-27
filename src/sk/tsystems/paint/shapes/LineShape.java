package sk.tsystems.paint.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import sk.tsystems.paint.Shape;

public class LineShape extends Shape {
	java.awt.Shape shape;

	public LineShape() {
		super(0, 0, 0, 0, null);
	}

	public LineShape(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);

	}

	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(getShapeColor());
		g2.setStroke(new BasicStroke(getBorderWidth()));
		System.out.println(" LINE " + getX() + " " + getY() + " " + getWidth() + " " + getHeight());
		shape = new Line2D.Double(getX(), getY(), getWidth(), getHeight());
		g2.draw(shape);
	}

}

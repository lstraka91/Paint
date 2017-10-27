package sk.tsystems.paint.shapes;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import sk.tsystems.paint.Shape2D;

public class CircleShape extends Shape2D {

	public CircleShape(double x, double y, double width, double height, Color color, Color borderColor) {
		super(x, y, width, height, color, borderColor);
		setBorderColor(borderColor);
	}

	public CircleShape() {
		super(0, 0, 0, 0, null, null);
	}

	@Override
	public double getHeight() {
		return getWidth();
	}

	@Override
	public Shape getShape() {

		return new Ellipse2D.Double(0, 0, getWidth(), getHeight());
	}

}

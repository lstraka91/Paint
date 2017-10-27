package sk.tsystems.paint.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import sk.tsystems.paint.Shape;
import sk.tsystems.paint.Shape2D;

public class RectangleShape extends Shape2D {

	public RectangleShape(double x, double y, double width, double height, Color color, Color borderColor) {
		super(x, y, width, height, color, borderColor);
		setBorderColor(borderColor);
	}

	public RectangleShape() {
		super(0, 0, 0, 0, null, null);
	}

	@Override
	public java.awt.Shape getShape() {
		return new Rectangle2D.Double(0, 0, getWidth(), getHeight());
	}

}

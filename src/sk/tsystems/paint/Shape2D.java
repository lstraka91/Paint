package sk.tsystems.paint;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape2D extends Shape {

	private Color borderColor;

	public Shape2D() {
		super();
	}

	public Shape2D(double x, double y, double width, double height, Color color, Color borderColor) {
		super(x, y, width, height, color);
		this.borderColor = borderColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	@Override
	public void paint(Graphics2D g2) {
		java.awt.Shape shape = getShape();
		g2.setColor(getShapeColor());
		g2.fill(shape);
		g2.setColor(getBorderColor());
		g2.draw(shape);
	}

	public abstract java.awt.Shape getShape();

}
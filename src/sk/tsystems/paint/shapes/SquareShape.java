package sk.tsystems.paint.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import sk.tsystems.paint.Shape;

public class SquareShape extends Shape {

	java.awt.Shape shape;

	public SquareShape(double x, double y, double width, double height, Color color) {
		super(x, y, width, height, color);
		
	}
	public SquareShape() {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
@Override
public double getHeight() {
	return getWidth();
}
	@Override
	public void paint(Graphics2D g2) {
		g2.setColor(getColorShape());
		shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
		setHeight(getWidth());
		g2.fill(shape);

	}

}

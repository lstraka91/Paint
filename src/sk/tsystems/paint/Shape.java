package sk.tsystems.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public abstract class Shape implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5939550702727629110L;
	private Point2D.Double position;
	private double width;
	private double height;
	private Color shapeColor;
	private Color borderColor;
	private float borderWidth;

	public Shape() {

	}

	public Shape(double x, double y, double width, double height, Color color) {
		position = new Point2D.Double(x, y);
		this.width = width;
		this.height = height;
		this.shapeColor = color;
		this.borderColor = color;
		this.borderWidth = 1;
	}

	public double getX() {
		return position.x;
	}

	public double getY() {
		return position.y;
	}

	public Point2D.Double getPosition() {
		return position;
	}

	public void setPosition(double x, double y) {
		position.setLocation(x, y);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Color getShapeColor() {
		return shapeColor;
	}

	public void setShapeColor(Color colorShape) {
		this.shapeColor = colorShape;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setPosition(Point2D.Double position) {
		this.position = position;
	}

	public boolean intersects(Shape shape) {
		return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight()).intersects(shape.getX(), shape.getY(),
				shape.getWidth(), shape.getHeight());
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}

	public abstract void paint(Graphics2D g2);
}

package sk.tsystems.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import sk.tsystems.paint.shapes.CircleShape;
import sk.tsystems.paint.shapes.EllipseShape;
import sk.tsystems.paint.shapes.LineShape;
import sk.tsystems.paint.shapes.RectangleShape;
import sk.tsystems.paint.shapes.SquareShape;

public class PaintPanel extends JPanel {

	private final int RESIZABLE_SQUARE = 5;
	private Shape currentShape;
	private Shape choosenShape;
	private Color chosenFillColor;
	private Color chosenBorderColor;
	private List<Shape> shapesList;

	private Point initialPoint;
	private Point endPoint;
	private boolean drawing;
	private boolean isEditMode;
	private Shape selectedShape;
	private Rectangle2D.Double selectRectangle;
	private Rectangle2D.Double resizableRectangle;
	private boolean resizableMode;
	private float strokeBorderWidth;

	public PaintPanel() {
		strokeBorderWidth = 1;
		shapesList = new ArrayList<>();
		chosenFillColor = Color.BLACK;
		chosenBorderColor = Color.BLUE;
		choosenShape = new LineShape();
		drawing = false;
		isEditMode = true;
		resizableMode = false;

		//// current Shape change dynamically
		initShapes();
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (!isEditMode) {
					endPoint = new Point(e.getX(), e.getY());
					drawing = false;
					System.out.println("released " + endPoint);
					double width = endPoint.getX() - initialPoint.getX();
					double height = endPoint.getY() - initialPoint.getY();

					if (width < 0)
						width *= -1;
					if (height < 0)
						height *= -1;

					if (choosenShape instanceof LineShape) {
						currentShape = new LineShape(initialPoint.getX(), initialPoint.getY(), e.getX(), e.getY(),
								chosenFillColor);
					} else if (choosenShape instanceof SquareShape) {
						currentShape = new SquareShape(initialPoint.getX(), initialPoint.getY(), width, height,
								chosenFillColor, chosenBorderColor);

					} else if (choosenShape instanceof RectangleShape) {
						currentShape = new RectangleShape(initialPoint.getX(), initialPoint.getY(), width, height,
								chosenFillColor, chosenBorderColor);

					} else if (choosenShape instanceof CircleShape) {
						currentShape = new CircleShape(initialPoint.getX(), initialPoint.getY(), width, height,
								chosenFillColor, chosenBorderColor);
					} else if (choosenShape instanceof EllipseShape) {
						currentShape = new EllipseShape(initialPoint.getX(), initialPoint.getY(), width, height,
								chosenFillColor, chosenBorderColor);
					}
					currentShape.setBorderWidth(strokeBorderWidth);
					addToShapesList(currentShape);
					currentShape = null;
				}

				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!isEditMode) {

					initialPoint = new Point(e.getX(), e.getY());
					drawing = true;
					System.out.println("pressed " + initialPoint);
				} else {
					for (int i = shapesList.size() - 1; i >= 0; i--) {
						Shape shape = shapesList.get(i);
						selectRectangle = new Rectangle2D.Double(shape.getX(), shape.getY(), shape.getWidth(),
								shape.getHeight());
						resizableRectangle = new Rectangle2D.Double(shape.getX() + shape.getWidth() - RESIZABLE_SQUARE,
								shape.getY() + shape.getHeight() - RESIZABLE_SQUARE, RESIZABLE_SQUARE,
								RESIZABLE_SQUARE);
						resizableMode = false;
						if (resizableRectangle.contains(e.getPoint())) {
							System.out.println("som na resizable");
							resizableMode = true;
							selectedShape = shape;
							repaint();
						}
						if (selectRectangle.contains(e.getPoint())) {
							System.out.println("is Selected ");
							selectedShape = shape;
							initialPoint = new Point(e.getX(), e.getY());
							repaint();
							return;
						}
					}
					selectedShape = null;
					selectRectangle = null;

				}
			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("trimem");
				if (!isEditMode) {
					if (choosenShape instanceof LineShape) {
						currentShape = new LineShape(initialPoint.getX(), initialPoint.getY(), e.getX(), e.getY(),
								chosenFillColor);
					} else {
						double width = e.getX() - initialPoint.getX();
						double height = e.getY() - initialPoint.getY();
						if (width < 0)
							width *= -1;
						if (height < 0)
							height *= -1;

						choosenShape.setShapeColor(chosenFillColor);
						choosenShape.setPosition(initialPoint.getX(), initialPoint.getY());
						choosenShape.setWidth(width);
						choosenShape.setHeight(height);
						currentShape = choosenShape;
					}
				} else {
					if (resizableMode) {
						resizableShape(e.getPoint());
					}

					else if (selectRectangle != null) {
						moveShapeTo(e.getPoint());
					}
				}
				repaint();
			}

			private void resizableShape(Point point) {
				int dx = (int) (point.getX() - initialPoint.getX());
				int dy = (int) (point.getY() - initialPoint.getY());
				selectRectangle.setRect(selectRectangle.getX(), selectRectangle.getY(), selectRectangle.getWidth() + dx,
						selectRectangle.getHeight() + dy);
				initialPoint.setLocation(point.getX(), point.getY());
				selectedShape.setWidth(selectedShape.getWidth() + dx);
				selectedShape.setHeight(selectedShape.getHeight() + dy);
				resizableRectangle.setRect(resizableRectangle.getX() + dx, resizableRectangle.getY() + dy,
						RESIZABLE_SQUARE, RESIZABLE_SQUARE);

			}

			private void moveShapeTo(Point point) {
				int dx = (int) (point.getX() - initialPoint.getX());
				int dy = (int) (point.getY() - initialPoint.getY());
				selectRectangle.setRect(selectRectangle.getX() + dx, selectRectangle.getY() + dy,
						selectRectangle.getWidth(), selectRectangle.getHeight());
				initialPoint.setLocation(point.getX(), point.getY());
				selectedShape.setPosition(selectedShape.getX() + dx, selectedShape.getY() + dy);
				resizableRectangle.setRect(resizableRectangle.getX() + dx, resizableRectangle.getY() + dy,
						RESIZABLE_SQUARE, RESIZABLE_SQUARE);
			}
		});
	}

	public void addToShapesList(Shape shape) {
		shapesList.add(shape);
	}

	public void removeFromShapeList(int index) {
		shapesList.remove(index);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < shapesList.size(); i++) {
			Shape shape = shapesList.get(i);
			if (shape instanceof LineShape) {
				shape.paint(g2);

			} else {

				AffineTransform at = g2.getTransform();
				g2.translate(shape.getX(), shape.getY());
				shape.paint(g2);
				g2.setTransform(at);

			}
		}
		if (drawing) {
			if (currentShape instanceof LineShape) {
				currentShape.paint(g2);
			} else {
				AffineTransform at = g2.getTransform();
				g2.translate(currentShape.getX(), currentShape.getY());
				currentShape.paint(g2);
				g2.setTransform(at);
			}
		}
		if (selectedShape != null) {
			g2.setColor(Color.RED);
			g2.draw(selectRectangle);
			g2.fill(resizableRectangle);
		}

	}

	private void initShapes() {
		addToShapesList(new RectangleShape(15, 20, 350, 49, Color.blue, Color.cyan));
		addToShapesList(new RectangleShape(66, 180, 350, 49, Color.pink, Color.ORANGE));
		addToShapesList(new EllipseShape(88, 380, 30, 49, Color.green, Color.black));
		addToShapesList(new SquareShape(78, 55, 60, 60, Color.black, Color.cyan));
		addToShapesList(new CircleShape(168, 33, 30, 49, Color.darkGray, Color.cyan));
	}

	public void setEditMode(boolean isEditMode) {
		this.isEditMode = isEditMode;
	}

	public void setChoosenShape(Shape choosenShape) {
		this.choosenShape = choosenShape;
	}

	public void setChoosenColor(Color choosenColor) {
		this.chosenFillColor = choosenColor;
	}

	public void setChoosenBorderColor(Color choosenColor) {
		this.chosenBorderColor = choosenColor;
	}

	public List<Shape> getShapesList() {
		return shapesList;
	}

	public void setShapesList(List<Shape> shapesList) {
		this.shapesList = shapesList;
	}

	public void setBorderWidth(float strokeBorderWidth) {
		this.strokeBorderWidth = strokeBorderWidth;
	}

}

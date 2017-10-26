package sk.tsystems.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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

import sk.tsystems.paint.shapes.EllipseShape;
import sk.tsystems.paint.shapes.LineShape;
import sk.tsystems.paint.shapes.RectangleShape;
import sk.tsystems.paint.shapes.SquareShape;

public class PaintPanel extends JPanel {

	private Shape currentShape;
	private Shape choosenShape;
	private Color chosenColor;
	private List<Shape> shapesList;
	private Point initialPoint;
	private Point endPoint;
	private boolean drawing;
	private boolean isEditMode;
	private Shape selectedShape;
	private Rectangle2D.Double selectRectangle;

	public PaintPanel() {
		shapesList = new ArrayList<>();
		chosenColor = Color.BLACK;
		choosenShape = new RectangleShape();
		drawing = false;
		isEditMode = true;
		//// current Shape change dynamically
		initShapes();
		addMouseListener(new MouseListener() {

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
								chosenColor);
					} else {

						currentShape = new SquareShape(initialPoint.getX(), initialPoint.getY(), width, height,
								chosenColor);
					}

					addToShapesList(currentShape);
				} else {

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
					for (int i = 0; i < shapesList.size(); i++) {
						Shape shape = shapesList.get(i);
						selectRectangle = new Rectangle2D.Double(shape.getX(), shape.getY(), shape.getWidth(),
								shape.getHeight());
						if (selectRectangle.contains(e.getPoint())) {
							System.out.println("is Selected ");
							selectedShape = shape;
							repaint();
							return;
						}
					}
					selectedShape = null;
					selectRectangle = null;

				}
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("trimem");
				if (!isEditMode) {
					if (choosenShape instanceof LineShape) {
						currentShape = new LineShape(initialPoint.getX(), initialPoint.getY(), e.getX(), e.getY(),
								chosenColor);
					} else {
						// Point end = changeInitialPoint((int) initialPoint.getX(), (int)
						// initialPoint.getY(), e.getX(), e.getY());
						double width = e.getX() - initialPoint.getX();
						double height = e.getY() - initialPoint.getY();
						if (width < 0)
							width *= -1;
						if (height < 0)
							height *= -1;
						currentShape = new SquareShape(initialPoint.getX(), initialPoint.getY(), width, height,
								chosenColor);
					}
				} else {

				}
				repaint();
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
		if (drawing) {
			if (currentShape instanceof LineShape) {
				currentShape.paint(g2);
			} else {

				AffineTransform at = g2.getTransform();
				g2.translate(currentShape.getX(), currentShape.getY());
				currentShape.paint(g2);
				g2.setTransform(at);
			}
			// currentShape.paint(g2);
		}

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
		System.out.println(selectedShape);
		if (selectedShape != null) {
			g2.setColor(Color.RED);
			g2.draw(selectRectangle);
		}

	}

	private Point changeInitialPoint(int initX, int initY, int endX, int endY) {
		int tempInitX = 0;
		int tempInitY = 0;
		int tempEndY = 0;
		int tempEndX = 0;
		Point tempEnd = new Point(0, 0);
		if (initX < endX) {
			tempInitX = initX;
			tempEndX = endX;
		} else {
			tempInitX = endX;
			tempEndX = initX;
		}
		if (initY < endY) {
			tempInitY = initY;
			tempEndY = endY;
		} else {
			tempInitY = endY;
			tempEndY = initY;
		}
		initialPoint.setLocation(tempInitX, tempInitY);
		tempEnd.setLocation(tempEndX, tempEndY);
		return tempEnd;
	}

	private void initShapes() {
		addToShapesList(new RectangleShape(15, 20, 350, 49, Color.blue));
		addToShapesList(new RectangleShape(66, 180, 350, 49, Color.pink));
		addToShapesList(new EllipseShape(88, 380, 30, 49, Color.green));
	}
}

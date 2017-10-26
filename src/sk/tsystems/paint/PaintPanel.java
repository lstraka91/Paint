package sk.tsystems.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

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

	public PaintPanel() {
		shapesList = new ArrayList<>();
		chosenColor = Color.BLACK;
		choosenShape = new LineShape();
		//// current Shape change dynamically

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				endPoint = new Point(e.getX(), e.getY());
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
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {

				initialPoint = new Point(e.getX(), e.getY());
				System.out.println("pressed " + initialPoint);
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
		addMouseMotionListener(new MouseMotionAdapter() {
			public void MouseDragged(MouseEvent e) {
				System.out.println("dragging");

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
				g2.setTransform(at);
			}
		}

	}
}

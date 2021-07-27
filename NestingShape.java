package bounce;
import java.util.ArrayList;
import java.util.List;

public class NestingShape extends Shape {
	private List<Shape> children = new ArrayList<Shape>();
	public NestingShape () {
		super();
	}
	public NestingShape (int x, int y) {
		super(x, y);
	}
	public NestingShape (int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	public NestingShape (int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	public NestingShape (int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	public void move (int width, int height) {
		super.move(width, height);
		for (int i = 0; i < children.size(); i++) {
			int nextX = children.get(i)._x + children.get(i)._deltaX;
			int nextY = children.get(i)._y + children.get(i)._deltaY;

			if (nextX <= _x) {
				nextX = _x;
				children.get(i)._deltaX = -children.get(i)._deltaX;
			} else if (nextX + children.get(i)._width >= _width + _x) {
				nextX = _width + _x - children.get(i)._width;
				children.get(i)._deltaX = -children.get(i)._deltaX;
			}

			if (nextY <= _y) {
				nextY = _y;
				children.get(i)._deltaY = -children.get(i)._deltaY;
			} else if (nextY + children.get(i)._height >= _height + _y) {
				nextY = _height + _y - children.get(i)._height;
				children.get(i)._deltaY = -children.get(i)._deltaY;
			}

			children.get(i)._x = nextX;
			children.get(i)._y = nextY;
		}
	}
	void add (Shape shape) throws IllegalArgumentException {
		if (shape.x() < (super.x() - shape.width())) {
		    throw new IllegalArgumentException();
		}
		else if (shape.y() < (super.y() - super.height())) {
		    throw new IllegalArgumentException();
		}
		else if (shape.x() > (super.x() + super.width())) {
		    throw new IllegalArgumentException();
		}
		else if (shape.y() > (super.y() + shape.height())) {
		    throw new IllegalArgumentException();
		}
		else if (shape.parent() != null) {
		    throw new IllegalArgumentException();
		} else {		
		    shape.setParent(this);
		    children.add(shape);
		}
	}
	void remove (Shape shape) {
        shape.setParent(null);
        children.remove(shape);
	}
	public Shape shapeAt (int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > shapeCount()-1) {
		    throw new IndexOutOfBoundsException();
		} else {
		    return children.get(index);
		}
	}
	public int shapeCount () {
		List<Shape> listOfChildren = children;
		return listOfChildren.size();
	}
	public int indexOf (Shape shape) {
		List<Shape> listOfChildren = children;
		int index = -1;
		for (int i = shapeCount()-1; i >= 0; i--) {
		    if (listOfChildren.get(i) == shape) {
		        index = i;
		    }
		}
		return index;
	}
	public boolean contains (Shape shape) {
		boolean child = false;
		List<Shape> listOfChildren = children;
		for (int i = 0; i < listOfChildren.size(); i++) {
		    if (listOfChildren.get(i) == shape) {
		        child = true;
		    }
		}
		return child;
	}
	public void doPaint(Painter painter) {
        painter.drawRect(_x,_y,_width,_height);
        if (super.getText() != "") {
			painter.drawCenteredText(super.getText(), (int)(_x+0.5f*_width), (int)(_y+0.5f*_height));
		}
	}
}

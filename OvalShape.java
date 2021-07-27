package bounce;

public class OvalShape extends Shape {
	public OvalShape() {
		super();
	}
	
	/**
	 * Creates an OvalShape object with a specified x and y position.
	 */
	public OvalShape(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Creates an OvalShape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public OvalShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	/**
	 * Creates an OvalShape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	public void doPaint(Painter painter) {
		painter.drawOval(super.x(), super.y(), super.width(), super.height());
		if (super.getText() != "") {
			painter.drawCenteredText(super.getText(), (int)(_x+0.5f*_width), (int)(_y+0.5f*_height));
		}
	}
}

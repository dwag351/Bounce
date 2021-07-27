package bounce;

import java.awt.Color;

public class DynamicRectangleShape extends RectangleShape {
	private Color newColor;
	private boolean regular = false;
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
		this.newColor = Color.red;
	}
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x, y, deltaX, deltaY, width, height);
		this.newColor = color;
	}
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color color) {
		super(x, y, deltaX, deltaY, width, height, text);
		this.newColor = color;
	}
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			regular = true;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			regular = true;
		}
		
		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			regular = false;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			regular = false;
		}

		super.move(width, height);
	}
	public void doPaint(Painter painter) {
		if (regular) {
		    painter.setColor(Color.black);
		    painter.drawRect(_x,_y,_width,_height);
		} else {
		    painter.setColor(newColor);
		    painter.fillRect(_x,_y,_width,_height);
		}
		painter.setColor(Color.black);
		if (super.getText() != "") {
			painter.drawCenteredText(super.getText(), (int)(_x+0.5f*_width), (int)(_y+0.5f*_height));
		}
	}
	public void setColor(Color color) {
		newColor = color;
	}
}
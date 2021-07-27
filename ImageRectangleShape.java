package bounce;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageRectangleShape extends RectangleShape {
    private Image image;
    public ImageRectangleShape(int deltaX, int deltaY, Image newImage) {
    	super(0, 0, deltaX, deltaY, newImage.getWidth(null), newImage.getHeight(null));
    	this.image = newImage;
    }
    public void doPaint(Painter painter) {
    	painter.drawImage(this.image, _x, _y, _width, _height);
	}
    public static Image makeImage(String imageFileName, int shapeWidth) {
    	int sw = shapeWidth;
    	int sh;
    	File f;
    	BufferedImage b = null;
    	BufferedImage b2 = null;
    	int w;
    	int h;
    	double sf;
    	Graphics2D g;
    	f = new File(imageFileName);
    	try {
    	    b = ImageIO.read(f);
    	    b2 = b;
        	w = b.getWidth();
        	h = b.getHeight();
        	if (w > sw) {
        		sf = (double)sw / w;
        		sh = (int)(h * sf);
        		System.out.println(sf);
        		System.out.println(sh);
        		b2 = new BufferedImage (sw, sh, BufferedImage.TYPE_INT_RGB);
        		g = b2.createGraphics();
        		g.drawImage(b, 0, 0, sw, sh, null);
        	}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return b2;
    }
}
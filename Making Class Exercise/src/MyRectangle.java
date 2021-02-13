
import java.util.*;
public class MyRectangle {
	private int left;
	private int bottom;
	private int width;
	private int height;
	private ArrayList <MyRectangle> list = new ArrayList <MyRectangle> ();
	public void CreateRectangle (int l, int b, int w, int h) {
		if (l < 0)
			left = 0;
		else
			left = l;
		
		if (b < 0)
			bottom = 0;
		else
			bottom = b;
		
		if (w < 0)
			width = 0;
		else
			width = w;
		
		if (h < 0)
			height = 0;
		else
			height = h;
		list.add();
	}
	
	public int getWidth () {
		return width;
	}
	public int getHeight () {
		return height;
	}
	
	public int getNumber () {
		return numRectangles;
	}
	
	public void setWidth (int w) {
		width = w;
	}
	public void setHeight (int h) {
		height = h;
	}
	
	public String toString (int l, int b, int w, int h) {
		return "base: (" + l + "," + b + ") w:" + w + " h:" + h;
	}
	
	public int area (int w, int h) {
		return w*h;
	}
	
	public int compareTo (Object rec1, Object rec2) {
		if (rec1 < rec2)
			return -1;
	}
}

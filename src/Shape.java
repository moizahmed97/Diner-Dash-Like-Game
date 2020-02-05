import java.awt.Color;

public class Shape {
	int xPosition, yPosition;
	int width, height;
	Color color;
	
	 public Shape() {
	}
	
	
	public Shape(int x, int y, int width, int hight)
	{
		this.xPosition = x;
		this.yPosition = y;
		this.width = width;
		this.height = hight;
	}
	
	public void setColor(Color c)
	{
		this.color = c;
	}
	
	public void setPosition(int x, int y)
	{
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}

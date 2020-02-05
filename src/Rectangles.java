import java.awt.Color;
import java.awt.Graphics;

public class Rectangles extends Shape{
	private boolean occupied = false;
	private boolean hasGivenOrder = false;
	private int customerOnTable;
	public Rectangles(int x, int y, Color c, int Breadth, int Length) {
		super(x, y, Length, Breadth);
		this.color = c;
	}
	
	
	public Rectangles() {
	}

	void drawRect(Graphics g)
	{
		g.setColor(this.color);
		g.fillRect(this.xPosition, this.yPosition, this.width, this.height);
		g.setColor(Color.BLACK);
		g.drawRect(this.xPosition, this.yPosition, this.width, this.height);

		
	}
	 
	public boolean insideRectangle(int px,int py) {
		if (  (px > this.xPosition && px < this.xPosition + this.width) && (py > this.yPosition && py < this.yPosition + this.height) )  {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void changeOccupation(boolean value) {
		this.occupied = value;
	}
	
	public boolean isOccupied() {
		return this.occupied;
	}
	public Color getColor() {
		return color;
	}
	
	public void setHasGivenOrder(boolean value) {
		this.hasGivenOrder = value;
	}
	
	public boolean getHasGivenOrder() {
		return hasGivenOrder;
	}

	public void setCustomerOnTable(int customerNumber) {
		this.customerOnTable = customerNumber;
	}
	
	public int getCustomerOnTable() {
		return customerOnTable;
	}
}

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Waitress extends Shape {
	 private boolean selected = false;
	 private boolean hasOrder = false;
	public Waitress(int x, int y, int radius , Color c) {
		super(x, y, radius, radius);
		setColor(c);
	}
	
	public Waitress() {
		
	}	

	public void drawWaitress(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.xPosition, this.yPosition, this.width, this.height);
		g.setColor(Color.BLACK);
		g.drawOval(this.xPosition, this.yPosition, this.width, this.height);
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public boolean isClicked(double x, double y) {
		if( (x >= this.getXPosition() && x <= this.getXPosition() + this.getWidth())
				&& (y >= this.getYPosition() && y <= this.getYPosition() + this.getWidth())) 
				return true;
		else
			return false;
	}
	
    public boolean insideCircle(int px,int py)
    {
    	
    	if (px > 600 && px > 350 ) {
    		return true;
    	}
    	else 
    		return false;
    }
	
	public void selected(boolean value) {
		
		if(this.selected && value) {  
			selected = false;
			setColor(Color.GRAY);
	     		
		}
			
		else { 
			selected = true;
			setColor(Color.PINK);
		}
			
	}
	

	public void setSelected(boolean value) {
		this.selected = value;
	}
	
	public Color getColor() {
		return super.color;
	}
	
	public void setOrder(boolean value) {
		hasOrder = value;
	}
	
	public boolean getOrder() {
		return hasOrder;
	}


	
}

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape{
	
	private boolean selected = false;
	private boolean sitting = false;
	private boolean drawn = false;
	private boolean deleted = false ;
	private int c1x,c2x;
	
	public Circle(int x, int y) {
		super(0, y, Constants.circleRad, Constants.circleRad);
		this.color = Color.green;
		this.c1x = x;
		this.c2x = x + 100;
	}
	
	public Circle(int x, int y, int rad){
		super(x, y, rad, rad);
		this.color = Color.GRAY;
	}
	public Circle() {	}
	
	
	public void drawCustomer(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.c1x, this.yPosition, this.width, this.height);
		g.fillOval(this.c2x, this.yPosition, this.width, this.height);

		g.setColor(Color.BLACK);
		g.drawOval(this.c1x, this.yPosition, this.width, this.height);
		g.drawOval(this.c2x, this.yPosition, this.width, this.height);

	}

	public void removeCustomer(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.c1x, this.yPosition, this.width, this.height);
		g.fillOval(this.c2x, this.yPosition, this.width, this.height);

		g.drawOval(this.c1x, this.yPosition, this.width, this.height);
		g.drawOval(this.c2x, this.yPosition, this.width, this.height);

	}

	public void drawWaitress(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.xPosition, this.yPosition, this.width, this.height);
		g.setColor(Color.BLACK);
		g.drawOval(this.xPosition, this.yPosition, this.width, this.height);
	}
	
	public void setPosition(int x , int y) {
		this.c1x = x;
		this.c2x = x + 160; 
		this.yPosition = y;
	}
	
	public void setPositionw(int x , int y) {
		this.c1x = x;
		this.c2x = x + 100; 
		this.yPosition = y;
	}

    
    public boolean insideCircle(int px,int py)
    {
       if (  (px > this.xPosition && px < this.xPosition + 200) && (py > this.yPosition && py < this.yPosition + 65) )  {
    	   return true;
       }
       else {
    	   return false;
       }
    }
    
    public boolean isCustomerDrawn() {
		return drawn;
	}

	public void setDrawn(boolean value) {
		this.drawn = value;
	}

	public void setSitting(boolean value) {
		this.sitting = value;
		this.selected = false;
	}

	public boolean isSitting() {
		return sitting;
	}

	public boolean isSelected() {
		return selected;
	}

	public void selected(boolean value) {
		if(!sitting) {
			if(!(this.selected) && value) {  
				selected = true;
				setColor(Color.CYAN);
			}

			else { 
				this.selected = false;
				setColor(Color.GREEN);
			}
		}
	}
	
	public void setColor(Color c)
	{
		this.color = c;
	}
	
	public void setDeleted(boolean value) {
		deleted = value;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
    

}



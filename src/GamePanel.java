import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener {
	Waitress  waitress;
	static int colorCounter = 0;
	 static Circle[] customers = 
					  {	  
						  new Circle(15,  105), 
	
						  new Circle(15,  195), 
						  
						  new Circle(15,  285),	
	
						  new Circle(15,  375),	
						  
						  new Circle(15,  465),	
	
						  new Circle(15,  555)	
					   };
	
	
	Rectangles carpet, entrance, chefTable;
	
	static Rectangles[] meals= 
						{ 	
							new Rectangles(445, 60, Color.GRAY, 60, 60), 
							new Rectangles(595, 60, Color.GRAY, 60, 60), 
							new Rectangles(745, 60, Color.GRAY, 60, 60), 
							new Rectangles(895, 60, Color.GRAY, 60, 60)
						}; 
	
	static Rectangles[] tables= 
						{	
							new Rectangles(427, 280, Color.GRAY, 100, 80),     
							new Rectangles(854, 280, Color.GRAY, 100, 80),
							new Rectangles(427, 500, Color.GRAY, 100, 80),
							new Rectangles(854, 500, Color.GRAY, 100, 80)
						 };
	
	public GamePanel() {
		addMouseListener(this);
		initializeVariables();
	}
	public GamePanel(int nothing) {
		
	}
	
	private void initializeVariables() {
		
		entrance = new Rectangles(0, 0, Color.black, 100, 290);
		chefTable = new Rectangles(400, 0, Color.white, 180, 600);
		carpet = new Rectangles(0, 75, new Color(184,134,11), 580, 200);       // peach color is 255,218,185 ------ gold color is 184,134,11
		waitress = new Waitress(640, 390, Constants.circleRad , Color.GRAY);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		carpet.drawRect(g);
		entrance.drawRect(g);
		
		chefTable.drawRect(g);
		
		for(int i=0;i<4;++i)
			meals[i].drawRect(g);
		
		for(int i=0;i<4;++i)
			tables[i].drawRect(g);

		for(int i=0 ; i < customers.length ; ++i)
			if (customers[i].getDeleted() == false)
				customers[i].drawCustomer(g);
			else
				customers[i].removeCustomer(g);
		
		waitress.drawWaitress(g);
	}
	
	public Rectangles getRectangle(int index) {
		return tables[index];
	}
	
	public Circle getCircle(int index) {
		return customers[index];
	}

	public Circle[] getCircleArray() {
		return customers;
	}

	public static Rectangles[] getRectangles() {
		return tables;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		new Timers();
		for (int i = 0 ; i < customers.length ; ++i) {							// check whether customer has been selected
			if (customers[i].insideCircle(e.getX() , e.getY())){
				System.out.println("Customer " + i + " selected");
				customers[i].selected(true);
			}													

		}
		for (int i = 0 ; i < tables.length ; ++i) {       
			
			if (tables[i].insideRectangle( e.getX(), e.getY())) {
				tables[i].changeOccupation(true);
				System.out.println("Table " + i +" selected");
				for (int k = 0 ;  k < customers.length ; ++k) {           // if a customer is selected then check if a table is clicked on 
					if (customers[k].isSelected() == true  && customers[k].isSitting() == false) {
						customers[k].setPosition(tables[i].getXPosition() - 70, tables[i].getYPosition() + 15);
						customers[k].setSitting(true);
						tables[i].setCustomerOnTable(k);
						tables[i].setColor(Constants.colors[colorCounter++]);  
						final Color bgr = this.getBackground();
						CustomerSitting(k , i , bgr);
					}									// if table is clicked on then move customers
				}
			}
		}
		if (waitress.isClicked(e.getX(), e.getY())) {
			waitress.setColor(Color.magenta);
			waitress.selected(true);
			System.out.println("Waitress Selected");
		}
		if (waitress.isSelected() == true) {
			for (int i = 0 ; i < tables.length ; ++i) {		// taking the order from the customer 
				if (tables[i].insideRectangle(e.getX(), e.getY())) {
					waitress.setColor(tables[i].getColor());
					waitress.setPosition(tables[i].getXPosition() + 10, tables[i].getYPosition() - 80);
					tables[i].setHasGivenOrder(true);
				}
			}
		}
		if (waitress.isSelected() == true) {						// Giving order to the Chef here 
			if (clickedOnChefTable(e.getX() , e.getY()) == true && waitress.getOrder() == false) {
				System.out.println("Clicked on Chef table");
				waitress.setPosition(650, 180);
				Color order = waitress.getColor();
				if (waitress.getColor() != Color.CYAN) {
				startOrder(order);
				waitress.setSelected(false);
				waitress.setColor(Color.GRAY);
				waitress.setOrder(true);
				}
				}
		}
		if ((clickedOnChefTable(e.getX() , e.getY())) && (waitress.isSelected() == true)) 	// Taking the meal from the chef
			for (int i = 0 ; i < meals.length ; ++i) {
			if (meals[i].insideRectangle(e.getX(), e.getY()));
				if (meals[i].getColor() != Color.GRAY) {
					waitress.setPosition(650, 180);
					waitress.setColor(meals[i].getColor());
					waitress.setSelected(false);
					meals[i].setColor(Color.GRAY);
				}
		}
		
		for (int i = 0 ; i < tables.length ; ++i) {    // Giving the customer their order back    

			if (tables[i].insideRectangle( e.getX(), e.getY()) && tables[i].getHasGivenOrder() == true) {
				int customerOnthisTable = tables[i].getCustomerOnTable();
				final Color bgrd = this.getBackground();
				waitress.setOrder(false);
				startEating(i , customerOnthisTable, bgrd);
				waitress.setPosition(tables[i].getXPosition() + 10, tables[i].getYPosition() - 80);
				Game.points += 150;
			}
		}
		GenerateNewCustomers(); 
		
		repaint();
	}
	
	private void GenerateNewCustomers() {
		new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						for (int u = 0 ; u < customers.length ; ++u) {
							if (customers[u].getDeleted() == true) {
								customers[u].setDeleted(false);
								customers[u].setColor(Color.green);
								customers[u].setSitting(false);
								customers[u].setSitting(false);
								setToOriginalPos(u);
							}
						}
					}
				}, 
				40000 
				);		
	}
	private void CustomerLeaving(int k , int i , Color bgrd) {
		new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						tables[i].setHasGivenOrder(false);
						tables[i].changeOccupation(false);
						tables[i].setColor(Color.gray);
						customers[k].setColor(bgrd);
						customers[k].setDeleted(true);
						Game.points = Game.points - 100;
						repaint();
					}
				}, 
				10000 
				);
	}

	private void CustomerStillSitting(int k , int i,  Color bgrd ) {
		new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						if (customers[k].isSitting() == true)
							customers[k].setColor(Color.red);
						repaint();
						CustomerLeaving(k , i , bgrd);
					}
				}, 
				15000 
				);		
	}
	
	
	private void CustomerSitting(int k ,int i, Color bgrd) {
		new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						if (customers[k].isSitting() == true)
							customers[k].setColor(Color.YELLOW);
						repaint();
						CustomerStillSitting(k , i , bgrd);
					}
				}, 
				25000 
				);		
	}


	private void startEating(int tablePos , int customerPos , Color bgrd ) {
		new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						tables[tablePos].setHasGivenOrder(false);
						tables[tablePos].changeOccupation(false);
						tables[tablePos].setColor(Color.gray);
						customers[customerPos].setColor(bgrd);
						customers[customerPos].setDeleted(true);
						customers[customerPos].setSitting(false);
						repaint();
					}
				}, 
				20000 
				);
	}

	private void startOrder(Color order) {
		System.out.println("In the order method");

		new java.util.Timer().schedule( 
				new java.util.TimerTask() {
					@Override
					public void run() {
						System.out.println("In run method");
						for (int r = 0 ; r < meals.length ; ++r)
							if (meals[r].getColor() == Color.gray) {
								meals[r].setColor(order);
								break;
							}
						repaint();
					}
				}, 
				5000 
				);
			
	}

	private boolean clickedOnChefTable(double x , double y) {
		if ((x > 400 && x < 1000) && (y > 0 && y < 180)  )
			return true;
		else 
			return false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	public static GamePanel getGamePanel() {
		GamePanel g = new GamePanel(1);
		return g;
	}
	
	public void setToOriginalPos(int w) {
		if (w == 0)  
			customers[w].setPositionw(15, 105);
			else if( w == 1)	
				customers[w].setPositionw(15, 195);

		  else if (w ==2)
				customers[w].setPositionw(15, 285);

		  else if (w ==3)
				customers[w].setPositionw(15, 375);

		  else if (w ==4)
				customers[w].setPositionw(15, 465);

		  else if (w ==5)
				customers[w].setPositionw(15, 555);

		  
		 
	}
	
	
	
	
	
	
}

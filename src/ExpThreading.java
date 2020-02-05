import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class ExpThreading extends GamePanel {
	
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        // Other painting stuff
	    }
	
	 	Graphics g1=super.getGraphics();
		GamePanel g = new GamePanel();
		public ExpThreading() {
			Game.getGameArea().addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					for (int i = 0 ; i < customers.length ; ++i) {
						if (customers[i].insideCircle(e.getX() , e.getY())){
							if(i%2==0){
								customers[i+1].selected(true);
								customers[i].selected(true);
								System.out.println(" Customer Selected"+i);

							}
							else{
								customers[i].selected(true);
								customers[i-1].selected(true);
								System.out.println(" Customer Selected"+i+", "+i);

							}
						}
					}
					for (int i = 0 ; i < tables.length ; ++i) {
						if (tables[i].insideRectangle( e.getX(), e.getY())) {
							tables[i].changeOccupation(true);
						}
					}
					for (int i = 0 ; i < customers.length ; ++i) {
						if (customers[i].isSelected() == true) {
							customers[i].setPosition(0, 0);
							System.out.println("Position of Customer set: "+i);
						}
					}
					paintComponent(g1);
					//g.repaint();		// option 1
					g1.repaint();		// option 2
					//Graphics q = g.getGraphics(); 
					//g.paint(q);			// option 3
//					GamePanel.repaint();		// option 4 not working 
					//new Game().setVisible(true);
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
				
			});
			
		}
		
}







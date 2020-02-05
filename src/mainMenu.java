import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



@SuppressWarnings("serial")
public class mainMenu extends JFrame implements ActionListener{
	
	private JButton playButton, leaderboardButton, exitButton;
	private JPanel screen;
	private static JLabel gameName;
	
	

	public static Game g;
	
	
	public mainMenu() {
		g = new Game();

		Constants.newGameTimer.timerFlag=false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true); 
		setSize(Constants.windowWidth, Constants.windowHieght);
		setLayout(new GridBagLayout());
		
		screen = new JPanel( new GridBagLayout());
		screen.setPreferredSize(new Dimension(Constants.windowWidth, Constants.windowHieght));
		screen.setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(screen);
		
		
		GridBagConstraints positions= new GridBagConstraints();
		positions.fill = GridBagConstraints.NONE;
		positions.anchor = GridBagConstraints.CENTER;
		positions.weightx = 1;
		positions.weighty = 1; 
		
		gameName = new JLabel("Diner Dash RIPoff");
		gameName.setFont(new Font("Rockwell", Font.PLAIN, 80));
		screen.add(gameName, positions);
		
		
		positions.gridy = 1;
		playButton = new JButton("PLAY");
		playButton.setPreferredSize(new Dimension(300, 150));
		playButton.setBackground(Color.WHITE);
		playButton.setFont(new Font("Rockwell", Font.PLAIN, 48));
		playButton.setBorder(BorderFactory.createLineBorder(Color.black));
		playButton.addActionListener(this);

		screen.add(playButton, positions);
		
	
		positions.gridy = 2;
		leaderboardButton = new JButton("LEADERBOARD");
		leaderboardButton.setPreferredSize(new Dimension(300, 150));
		leaderboardButton.setBackground(Color.WHITE);
		leaderboardButton.setFont(new Font("Rockwell", Font.PLAIN, 40));
		leaderboardButton.setBorder(BorderFactory.createLineBorder(Color.black));
		leaderboardButton.addActionListener(this);
		
		screen.add(leaderboardButton, positions);
        
	
		positions.gridy = 3;
		exitButton = new JButton("EXIT");
		exitButton.setPreferredSize(new Dimension(300, 150));
		exitButton.setBackground(Color.WHITE);
		exitButton.setFont(new Font("Rockwell", Font.PLAIN, 48));
		exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
		exitButton.addActionListener(this);
		
		screen.add(exitButton, positions);
		
		setSize(Constants.windowWidth, Constants.windowHieght);
		setVisible(true);	
		//add(screen);
		int i=0;
		while(i<10000){
			FirstThread ft = new FirstThread();
			ft.start();
			++i;
			mainMenu.doNothing(200);
			}
		setTitle("FOOD WARS");
		setLocationRelativeTo(null);
	}

	private class FirstThread extends Thread {
		public void run(){
			
		
			for(int i=0;i<10000;++i){
				
				gameName.setForeground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
				exitButton.setForeground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
				leaderboardButton.setForeground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
				playButton.setForeground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
				mainMenu.doNothing(1000);
			
			}
		}
	}
	
	public static void doNothing(int millisecond){
		try {
			Thread.sleep(millisecond);
		}
		catch(InterruptedException e) {
			System.out.println("Unexpected interrupt");
			System.exit(0);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==playButton){
			
		    Thread thread = new Thread() {
		        public void run() {
		            dispose();
		            g.setVisible(true);
		           // Constants.gameTimer.timerFlag=true;
		            Constants.newGameTimer.timerFlag= true;

		        }
		    };
		    thread.start();

		}



		else if(e.getSource() == leaderboardButton) {
			Thread thread = new Thread() {
		        public void run() {
		            new Leaderboard().setVisible(true);

		            dispose();

		        }
		    };
		    thread.start();
		}
		
		else if(e.getSource() == exitButton) {
			System.exit(0);
		}

	}
	
	public static void main(String[] args) {
		
		
		mainMenu mainM= new mainMenu();
	}

}

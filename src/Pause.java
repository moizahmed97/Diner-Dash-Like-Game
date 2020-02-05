import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pause extends JFrame implements ActionListener{
	
	private JButton resumeButton, exitButton;
	private JPanel screen;
	private JLabel message;
	private boolean flag=false;
	
	//Timers t= new Timers(0);
	
	public Pause() {
		// TODO Auto-generated constructor stub
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true); 
		setSize(Constants.windowWidth/2, Constants.windowHieght/2);
		setLayout(new GridBagLayout());
		//setPreferredSize(new Dimension(Constants.windowWidth/4, Constants.windowHieght/4));
		screen = new JPanel(new GridBagLayout());
		screen.setPreferredSize(new Dimension(Constants.windowWidth/2, Constants.windowHieght/2));
		screen.setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(screen);
		
		
		GridBagConstraints positions= new GridBagConstraints();
		positions.fill = GridBagConstraints.NONE;
		positions.anchor = GridBagConstraints.CENTER;
		positions.weightx = 1;
		positions.weighty = 1; 
		
		message = new JLabel("Game Paused: What Do You Want To Do?");
		message.setFont(new Font("Rockwell", Font.PLAIN, 26));
		screen.add(message, positions);
		
		positions.gridx = 0;
		positions.gridy = 1;
		resumeButton = new JButton("RESUME");
		resumeButton.setPreferredSize(new Dimension(100, 60));
		resumeButton.setBackground(Color.WHITE);
		resumeButton.setFont(new Font("Rockwell", Font.PLAIN, 18));
		resumeButton.setBorder(BorderFactory.createLineBorder(Color.black));
		resumeButton.addActionListener(this);

		screen.add(resumeButton, positions);
		
		positions.gridx = 0;
		positions.gridy = 2;
		exitButton = new JButton("EXIT TO MAIN MENU");
		exitButton.setPreferredSize(new Dimension(200, 60));
		exitButton.setBackground(Color.WHITE);
		exitButton.setFont(new Font("Rockwell", Font.PLAIN, 18));
		exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
		exitButton.addActionListener(this);
		
		screen.add(exitButton, positions);
		
		setLocationRelativeTo(null);
        
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==resumeButton){
			dispose();
			Constants.newGameTimer.timerFlag=true;
		}
		else {
			
			Thread thread = new Thread() {
		        public void run() {
		        	//flag=true;
		        	System.out.println();
		            Game.exit=true;
		            dispose();
		            mainMenu.g=null;
		            new mainMenu();
		            Constants.newGameTimer.timerFlag=false;
		            
		       

		        }
		    };
		    thread.start();
		}
	}
	
	public boolean getExit(){
		System.out.println(flag);

		return flag;
		
	}

}

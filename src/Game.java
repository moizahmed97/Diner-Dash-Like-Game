import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JDialog implements ActionListener{
	public static int points=0;
	public static boolean check = false;
	static JPanel topMenu;
	private static GamePanel GameArea; 
	private JButton pauseButton;
	Pause pause=new Pause();


	
	static boolean exit=false;
	
	static JLabel timer;
	private JLabel score;
	
	public Game() {	
		this.setEnabled(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setUndecorated(true);
		setLayout(new BorderLayout());
		
		GameArea = new GamePanel();
		GameArea.setPreferredSize(new Dimension(Constants.windowWidth, Constants.windowHieght));
		GameArea.setBorder(BorderFactory.createLineBorder(Color.black));
		add(GameArea);
		
		
		JLabel pause = new JLabel("Pause");
		pause.setFont(new Font("Rockwell", Font.PLAIN, 32));    
		
		score = new JLabel("Score:      ");
		score.setHorizontalAlignment(JTextField.CENTER);                      //for score to display at the center
		score.setFont(new Font("Rockwell", Font.PLAIN, 32));
		timer = new JLabel("Time:            ");
		timer.setFont(new Font("Rockwell", Font.PLAIN, 32));
		
		pauseButton = new JButton();
		pauseButton.setBorder(null);
		pauseButton.setBackground(Color.WHITE);
		pauseButton.setForeground(Color.white);
		pauseButton.setLayout(new GridBagLayout());
		pauseButton.add(pause);
		pauseButton.setPreferredSize(new Dimension(150, 70));
		pauseButton.setFont(new Font("Rockwell", Font.PLAIN, 32));           	       //setting up jpanel as a button
		pauseButton.setBorder(BorderFactory.createLineBorder(Color.black));
		pauseButton.addActionListener(this);
	
		topMenu = new JPanel();
		topMenu.setPreferredSize(new Dimension(Constants.windowWidth, 70));
		topMenu.setLayout(new BorderLayout());
		topMenu.add(pauseButton, BorderLayout.LINE_START);
		topMenu.add(score);
		topMenu.add(timer, BorderLayout.LINE_END);								//adding everything into menu
		topMenu.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		add(topMenu, BorderLayout.PAGE_START);
		
		
		setSize(Constants.windowWidth, Constants.windowHieght);
		setVisible(true);	
		setTitle("FOOD WARS");
	}

	
	@Override
	public void actionPerformed(ActionEvent event) {
		Thread thread = new Thread() {
	        public void run() {
	        	Constants.newGameTimer.timerFlag=false;
	    		pause.setVisible(true);
	    		
	    		}

	    		
	    };
	    thread.start();
	    
		}

	

	public static GamePanel getGameArea() {
		return GameArea;
	}
	
	public static JLabel getTimeLabel() {
		return timer;
	}
	
	public static void setTimeLabel(JLabel newTime) {
		timer = newTime;
	}

	
}
	



		

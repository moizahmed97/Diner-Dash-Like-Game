import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Leaderboard extends JFrame implements ActionListener {
	private JPanel topPanel;
	private JButton backButton;
	private JLabel frameName;
	private ArrayList<Gamers> playersList;
	private JTable scoreTable;
	private JScrollPane scrollPane;
	private String[] columnNames;
	private Object[][] data;

	private JPanel buttonPanel;
	private JLabel sortLabel;
	private JRadioButton namesRadio;
	private JRadioButton scoresRadio;
	private ButtonGroup a1;

	private JRadioButton forAsc;
	private JRadioButton forDesc;
	private ButtonGroup a2;

	private JButton sortButton;

	public Leaderboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true); 
		setSize(Constants.windowWidth, Constants.windowHieght);
		setLayout(new BorderLayout());

		Font font = new Font("Rockwell", Font.BOLD, 20);

		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(topPanel, BorderLayout.NORTH);

		backButton = new JButton("BACK");
		backButton.setPreferredSize(new Dimension(150, 90));
		backButton.setFont(font);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainMenu();
				setVisible(false);
			} 
		});
		topPanel.add(backButton, BorderLayout.LINE_START);

		frameName = new JLabel("LeaderBoard");
		frameName.setFont(font);
		frameName.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(frameName, BorderLayout.CENTER);

		buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setPreferredSize(new Dimension(400, 300));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(buttonPanel, BorderLayout.WEST);

		namesRadio = new JRadioButton("Names");
		namesRadio.setActionCommand("name");
		namesRadio.setFont(font);
		scoresRadio = new JRadioButton("Scores");
		scoresRadio.setSelected(true);
		scoresRadio.setFont(font);
		a1 = new ButtonGroup();
		a1.add(namesRadio);
		a1.add(scoresRadio);

		forAsc = new JRadioButton("Ascending");
		forAsc.setActionCommand("asc");
		forAsc.setFont(font);
		forDesc = new JRadioButton("Descending");
		forDesc.setSelected(true);
		forDesc.setFont(font);
		a2 = new ButtonGroup();
		a2.add(forAsc);
		a2.add(forDesc);

		sortButton = new JButton("Sort");
		sortButton.setFont(font);
		sortButton.addActionListener(this);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.5;
		c.anchor = GridBagConstraints.SOUTH;
		sortLabel = new JLabel("Sort By: ");
		sortLabel.setFont(font);
		buttonPanel.add(sortLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.SOUTH;
		buttonPanel.add(namesRadio, c);

		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		buttonPanel.add(scoresRadio, c);

		c.gridy = 3;
		c.anchor = GridBagConstraints.SOUTH;
		buttonPanel.add(forAsc, c);

		c.gridy = 4;
		c.anchor = GridBagConstraints.NORTH;
		buttonPanel.add(forDesc, c);

		c.gridy = 5;
		c.anchor = GridBagConstraints.NORTH;
		buttonPanel.add(sortButton, c);

		String fileName = "Gamers.txt";
		Scanner file = null;
		try {
			file = new Scanner(new File(fileName));
		} catch (FileNotFoundException e1) {
			System.out.println("File not found!");
			System.exit(0);
		}

		playersList = new ArrayList<Gamers>();

		while (file.hasNextLine()) {
			playersList.add(new Gamers(file.next(), file.nextInt()));
		}

		columnNames = new String[2];
		columnNames[0] = "Names";
		columnNames[1] = "Scores";
		data = new Object[playersList.size()][2];
		for(int i = 0; i < data.length; i++) {
			data[i][0] = playersList.get(i).getName();
			data[i][1] = playersList.get(i).getScore();
		}

		scoreTable = new JTable(data, columnNames);
		scoreTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scoreTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
		scoreTable.setFillsViewportHeight(true);
		scoreTable.setEnabled(false);

		scrollPane = new JScrollPane(scoreTable);
		add(scrollPane);
		
		sortButton.doClick();
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		if(event.getSource()==backButton){
			
		    Thread thread = new Thread() {
		        public void run() {
		            dispose();

		        	new mainMenu();
		        }
		    };
		    thread.start();
		}
		
		
		
		
		if(a1.getSelection().getActionCommand() == "name") {
			Collections.sort(playersList , new Comparator<Gamers>() {
				public int compare(Gamers p1, Gamers p2) {
					if(a2.getSelection().getActionCommand() == "asc")
						return p1.getName().compareTo(p2.getName());

					return p2.getName().compareTo(p1.getName()); 
				}
			});

		}
		
		
		
		
		else {
			Collections.sort(playersList , new Comparator<Gamers>() {
				public int compare(Gamers p1, Gamers p2) {
					if(a2.getSelection().getActionCommand() == "asc")
						return p1.getScore() - p2.getScore();

					return p2.getScore() - p1.getScore();
				}
			});

			
			
			
		}

		scoreTable = new JTable(getData(playersList), columnNames);
		scoreTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scoreTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
		scoreTable.setFillsViewportHeight(true);
		scoreTable.setEnabled(false);

		scrollPane = new JScrollPane(scoreTable);
		add(scrollPane);
		add(scrollPane);
		repaint();


	}

	public Object[][] getData(ArrayList<Gamers> list) {
		for(int i = 0; i < data.length; i++) {
			data[i][0] = playersList.get(i).getName();
			data[i][1] = playersList.get(i).getScore();
		}
		return data;

	}

}
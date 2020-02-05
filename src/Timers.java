import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Timers {
	private JLabel time;
	//boolean pause=false;
	int secondsRemaining=600;
	//Timer timer=new Timer();
	String newTime="";
	
	boolean timerFlag=false;
	Timers(int n){
		
	}
	
/*	TimerTask task = new TimerTask(){
		public void run(){
			System.out.println("in task");
			//Constants.gameTimer.timerFlag=true;
			if(timerFlag){
				
			System.out.println("inside");
			if(secondsRemaining>0)
				secondsRemaining--;
			
			time = new JLabel();
			time = Game.getTimeLabel();
			if(secondsRemaining%60<10)
				newTime = "Time: " + Long.toString(secondsRemaining/60) + ":0" + Long.toString(secondsRemaining%60)+"      ";
			else
				newTime = "Time: " + Long.toString(secondsRemaining/60) + ":" + Long.toString(secondsRemaining%60)+"      ";
			time.setText(newTime);
			Game.setTimeLabel(time);
			System.out.println(newTime);
			
			
			}
		}
	};*/
	
	
	
	
	Timers(){
		//start();
		
		Thread t = new Thread(){
			public void run(){
				
				System.out.println("in task");
				while(true){
					//System.out.println("outside");
					System.out.println(timerFlag);
					
					if(timerFlag)
					{
					//System.out.println("timerFlag=true");
					if(secondsRemaining>0)
						secondsRemaining--;
					else {
						JOptionPane.showMessageDialog(GamePanel.getGamePanel(),"Time is up your score is " + Game.points);
						new Gamers(" " , Game.points);
						
					}
					
					time = new JLabel();
					time = Game.getTimeLabel();
					if(secondsRemaining%60<10)
						newTime = "Time: " + Long.toString(secondsRemaining/60) + ":0" + Long.toString(secondsRemaining%60)+"      ";
					else
						newTime = "Time: " + Long.toString(secondsRemaining/60) + ":" + Long.toString(secondsRemaining%60)+"      ";
					time.setText(newTime);
					Game.setTimeLabel(time);
					System.out.println(newTime);
					
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}


			
}

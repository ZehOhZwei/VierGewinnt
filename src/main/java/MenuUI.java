import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MenuUI{
	
	//Settings
	
	boolean withBot=false;
	
	Bot botType;
	
	int gameMode=0;
	int _height;
	int _width;
				
	JFrame window;
	JPanel gameSelection;
	JPanel choiceContainer;

	Choice botSelection;

	Choice modeSelection;
	
	Choice botDifficultySelection;
	
	JButton start;
	
	public MenuUI()
	{
		window=new JFrame("Vier Gewinnt");
		gameSelection = new JPanel();
		gameSelection.setLayout(new BorderLayout());
		choiceContainer = new JPanel();
		gameSelection.add(choiceContainer, BorderLayout.CENTER);
		window.add(gameSelection);
		window.setSize(500, 400);
		window.setVisible(true);
		
		botSelection = new Choice();
		botSelection.add("Yes");
		botSelection.add("No");
		choiceContainer.add(botSelection);
		
		modeSelection = new Choice();
		modeSelection.add("7x6");
		modeSelection.add("10x10");
		choiceContainer.add(modeSelection);

		botDifficultySelection= new Choice();
		botDifficultySelection.add("Simple");
		choiceContainer.add(botDifficultySelection);

		window.setVisible(true);
		window.setSize(400, 250);
		
		start = new JButton("Start");
		choiceContainer.add(start);
		start.addActionListener(e -> { startGame();});
	}
	
	public void startGame()
	{
		Bot bot = null;
		if (botSelection.getSelectedItem().equals("Yes")) {
			bot = new HardBot(2);
		}
		
		if(modeSelection.getSelectedIndex() == 0) {
			GameController.startGame(7, 6, bot);
		}
		if(modeSelection.getSelectedIndex() == 1)
		{
			GameController.startGame(10, 10, bot);
		}
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

	}
}

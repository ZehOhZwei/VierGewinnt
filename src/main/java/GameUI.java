import java.awt.BorderLayout;
import java.awt.Choice;

import javax.swing.*;

public class GameUI{
	
	//Settings
	boolean withBot=false;
	Bot botType;
	int gameMode=0;
	int _height;
	int _width;
			
	GameAPI game;
	
	JFrame window;
	JPanel gameSelection;
	JPanel choiceContainer;

	Choice botSelection;

	Choice modeSelection;
	
	Choice botDifficultySelection;
	
	JButton start;
	
	public GameUI()
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
		modeSelection.add("Standard");
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
		GameAPI gameapi=new GameAPI(6,5);
		new IngameUI(gameapi);
	}
}

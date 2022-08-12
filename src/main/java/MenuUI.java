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

	JLabel botSelectionLabel;
	Choice botSelection;

	JLabel modeSelectionLabel;
	Choice modeSelection;
	
	JLabel botDifficultySelectionLabel;
	Choice botDifficultySelection;
	
	JButton start;
	/*
	 * Baut das UI des Hauptmenüs auf.
	 */
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
		
		botSelectionLabel = new JLabel("Bot:");
		botSelection = new Choice();
		botSelection.add("Yes");
		botSelection.add("No");
		choiceContainer.add(botSelectionLabel);
		choiceContainer.add(botSelection);
		
		
		modeSelectionLabel = new JLabel("Modus:");
		modeSelection = new Choice();
		modeSelection.add("7x7");
		modeSelection.add("10x10");
		choiceContainer.add(modeSelectionLabel);
		choiceContainer.add(modeSelection);

		botDifficultySelectionLabel = new JLabel("Schwierigkeit:");
		botDifficultySelection= new Choice();
		botDifficultySelection.add("Normal");		
		choiceContainer.add(botDifficultySelectionLabel);
		choiceContainer.add(botDifficultySelection);

		window.setVisible(true);
		window.setSize(400, 250);
		
		start = new JButton("Start");
		choiceContainer.add(start);
		start.addActionListener(e -> { startGame();});
	}
	
	/*
	 * Richtet die Funktion des Start-Buttons ein.
	 */
	public void startGame()
	{
		Bot bot = null;
		if (botSelection.getSelectedItem().equals("Yes")) {
			bot = new HardBot(2);
		}
		
		if(modeSelection.getSelectedIndex() == 0) {
			GameController.startGame(7, 7, bot);
		}
		if(modeSelection.getSelectedIndex() == 1)
		{
			GameController.startGame(10, 10, bot);
		}
		window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

	}
}

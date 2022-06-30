import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IngameUI {

	GameAPI _gameapi;
	public JFrame window;
	JPanel gamePanel;
	JPanel buttonPanel;
	JPanel fieldPanel;
	JButton[] columnButtons;
	
	public IngameUI(GameAPI gameapi, int w, int h)
	{
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		columnButtons= new JButton[w];
		buttonPanel = new JPanel();
		fieldPanel = new JPanel();
		fieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		gamePanel.add(buttonPanel, BorderLayout.NORTH);
		gamePanel.add(fieldPanel, BorderLayout.CENTER);
		_gameapi=gameapi;
		window = new JFrame(); 
		window.add(gamePanel);
		//window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setSize(500, 400);
		window.setVisible(true);
		for(int i = 0; i < w ; i++) {
			final int iFinal = i; //This looks really horrible but is necessary. i can't be referenced in the enclosed space
			columnButtons[i] = new JButton(""+(i+1));
			buttonPanel.add(columnButtons[i]);
			columnButtons[i].addActionListener(e -> {_gameapi.dropStone(iFinal);});	
		}
		drawField();
	}
	
	void drawField() {

	}
}

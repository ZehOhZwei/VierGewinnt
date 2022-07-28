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
	FieldPanel fieldPanel;
	JButton[] columnButtons;

	
	
	/**
	 * The Constructor for the IngameUI. Firstly initializes three JPanels. 
	 * One for the row of buttons on the top of the screen,
	 * One for the playing field in the middle and one to contain the two others.
	 * Then a window is created and set visible to house the components.
	 * Lastly the row of buttons is created. The "left" and "right" buttons are created manually,
	 * but the buttons for choosing a column are created based on how many columns there are on the field.
	 * For this, we create an array of JButtons and initialize them each in a for-loop. 
	 * 
	 * @param gameapi the GameAPI that created this IngameUI, saved here for later reference.
	 * @param w the width of the playing field
	 * @param h the height of the playing field
	 */
	public IngameUI(GameAPI gameapi, int w, int h) {
		_gameapi = gameapi;

		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		columnButtons = new JButton[w];
		buttonPanel = new JPanel();
		fieldPanel = new FieldPanel(w, h, this);
		fieldPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		gamePanel.add(buttonPanel, BorderLayout.NORTH);
		gamePanel.add(fieldPanel, BorderLayout.CENTER);

		window = new JFrame();
		window.add(gamePanel);
		// window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setSize(720, 676);
		window.setVisible(true);

		JButton left = new JButton("<");
		buttonPanel.add(left);
		left.addActionListener(e -> {
			_gameapi.turnLeft();
		});
		for (int i = 0; i < w; i++) {
			final int iFinal = i; // This looks really horrible but is necessary. i can't be referenced in the
									// enclosed space
			columnButtons[i] = new JButton("" + (i + 1));
			buttonPanel.add(columnButtons[i]);
			columnButtons[i].addActionListener(e -> {
				_gameapi.dropStone(iFinal);
			});
		}
		JButton right = new JButton(">");
		buttonPanel.add(right);
		right.addActionListener(e -> {
			_gameapi.turnRight();
		});

	}

	/**
	 * Calls the update method in the FieldPanel
	 */
	public void update() {
		fieldPanel.update();
	}

}

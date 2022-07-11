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

	public void dropStone(int column, int row, int turn) {
		fieldPanel.dropStone(column, row, turn);
	}

}

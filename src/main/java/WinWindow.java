import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinWindow {
	
	JDialog window;
	JPanel textContainer;
	JPanel buttonContainer;
	
	GameAPI api;
	
	JLabel text;
	JButton restart;
	JButton close;
	
	public WinWindow(int turn, GameAPI gameAPI)
	{
		window = new JDialog(gameAPI.ui.window, "Congratulations!");
		window.setLayout(new BorderLayout());
		textContainer = new JPanel();
		buttonContainer = new JPanel();
		
		api = gameAPI;
		
		text = new JLabel("Player " + turn + " wins");
		restart = new JButton("Restart");
		restart.addActionListener(e -> {
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			api.ui.window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			new GameAPI(api.startWidth, api.startHeight, api._bot);
			});
		close = new JButton("Close");
		close.addActionListener(e -> {
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			api.ui.window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		});
	
		textContainer.add(text);
		buttonContainer.add(close);
		buttonContainer.add(restart);
		
		window.add(textContainer, BorderLayout.NORTH);
		window.add(buttonContainer, BorderLayout.CENTER);
		window.setSize(300, 200);
		window.setVisible(true);
	}
	
	JPanel gameSelection;
	JPanel choiceContainer;

	
	JButton start;
	
}

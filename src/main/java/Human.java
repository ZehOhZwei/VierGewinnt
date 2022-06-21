import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Human implements KeyListener, Player {
	GameAPI api;
	int currentColumn;
	private boolean confirm;
	
	public Human(GameAPI gameApi) {
		api = gameApi;
		api.ui.addKeyListener(this);

	}
	
	@Override
	public int takeTurn() {
		while(!confirm) {
			System.out.println(currentColumn);
		}
		return currentColumn;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == 51 && currentColumn > 0) {
			currentColumn += 1;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

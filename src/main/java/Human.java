import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Human implements KeyListener, Player {
	GameAPI api;
	int currentColumn;
	private boolean confirm = false;
	
	public Human(GameAPI gameApi) {
		api = gameApi;
		api.ui.window.addKeyListener(this);

	}
	
	@Override
	public int getCurrentColumn() {
		return currentColumn;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == 29 && currentColumn >= 0) {
			currentColumn -= 1;
		}
		if(e.getKeyCode() == 32 && currentColumn <= 0) {
			currentColumn += 1;
		}
		if(e.getKeyCode() == 62 && currentColumn > 0) {
			confirm = true;
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

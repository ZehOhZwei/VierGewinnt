public class GameController {
	public static void main(String args[]) {
		new GameUI();	
		
	}
	
	
	/**
	 * The method that starts the game itself. 
	 * Creates a new GameAPI with a specified field height and width
	 * This method is called when the Button "Start" in the GameUI is pressed
	 * 
	 * @param x the width of the playing field that is to be created
	 * @param y the height of the playing field that is to be created
	 * 
	 **/
	public static void startGame(int x, int y) {
		GameAPI gameapi=new GameAPI(x,y);
	}
}

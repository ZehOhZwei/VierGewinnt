
public class GameController {
	public static void main(String args[]) {
		GameAPI game = new GameAPI(7, 6, new Human(), new Human());
		while (!game.checkForWin())
		{
			
		}
	}
}

public class GameController {
	public static void main(String args[]) {
		new GameUI();	
		
	}

	public static void startGame(int x, int y) {
		GameAPI gameapi=new GameAPI(x,y);
	}
}

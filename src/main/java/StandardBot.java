public class StandardBot implements Bot{
	public int makeTurn(GameAPI board) {
		return 0;
	}
	
	public int[] simmulateDepth(GameAPI game, int depth){
		GameAPI simulation = game;
		int[] results = new int[simulation.getWidth()];
		int i=0;
		simulation.dropStone(i);
		//if(game.checkForWin(game.getHighestEmptySpace(i), i)) {
		//	results[i]=2;
		//}
		
		return results;
	}
	
}

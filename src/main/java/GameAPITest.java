import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameAPITest {

	@Test
	void dropStoneTest() {
		GameAPI api = new GameAPI(7,6);
		api.dropStone(0);
		assertTrue(api._board[0][0] == 1);
	}
	
	@Test
	
	void checkHorizontalTest() {
		GameAPI api = new GameAPI(7,6);
		api.turn = 1;
		api._board[0][0] = 1;
		api._board[1][0] = 1;
		api._board[2][0] = 1;
		api._board[3][0] = 1;
		assertTrue(api.checkHorizontal(0, 0));
	}
	@Test
	
	void checkVerticalTest() {
		GameAPI api = new GameAPI(7,6);
		api._board[0][0] = 1;
		api._board[0][1] = 1;
		api._board[0][2] = 1;
		api._board[0][3] = 1;
		assertTrue(api.checkVertical(0, 0));
	}
	@Test
	
	void checkDiagonalRisingTest() {
		GameAPI api = new GameAPI(7,6);
		api._board[0][0] = 1;
		api._board[1][1] = 1;
		api._board[2][2] = 1;
		api._board[3][3] = 1;
		assertTrue(api.checkDiagonalRising(0, 0));
	}
	
	@Test
	void checkDiagonalFallingTest() {
		GameAPI api = new GameAPI(7,6);
		api._board[3][3] = 1;
		api._board[4][2] = 1;
		api._board[5][1] = 1;
		api._board[6][0] = 1;
		assertTrue(api.checkDiagonalFalling(3, 3));
	}
	
	

}

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameAPITest {

	@Test
	void dropStoneTest() {
		GameAPI api = new GameAPI(7,6);
		api.dropStone(0);
		assertEquals(api._board.getCell(0, 0),  1);
	}
	
	@Test
	
	void checkHorizontalTest() {
		GameAPI api = new GameAPI(7,6);
		api.turn = 1;
		api._board.setCell(0, 0, 1);
		api._board.setCell(1, 0, 1);
		api._board.setCell(2, 0, 1);
		api._board.setCell(3, 0, 1);
		assertTrue(api._board.checkHorizontal(0, 0, 1));
	}
	@Test
	
	void checkVerticalTest() {
		GameAPI api = new GameAPI(7,6);
		api._board.setCell(0, 0, 1);
		api._board.setCell(0, 1, 1);
		api._board.setCell(0, 2, 1);
		api._board.setCell(0, 3, 1);
		assertTrue(api._board.checkVertical(0, 0, 1));
	}
	@Test
	
	void checkDiagonalRisingTest() {
		GameAPI api = new GameAPI(7,6);
		api._board.setCell(0, 0, 1);
		api._board.setCell(1, 1, 1);
		api._board.setCell(2, 2, 1);
		api._board.setCell(3, 3, 1);
		assertTrue(api._board.checkDiagonalRising(0, 0, 1));
	}
	
	@Test
	void checkDiagonalFallingTest() {
		GameAPI api = new GameAPI(7,6);
		api._board.setCell(3, 3, 1);
		api._board.setCell(4, 2, 1);
		api._board.setCell(5, 1, 1);
		api._board.setCell(6, 0, 1);

		assertTrue(api._board.checkDiagonalFalling(3, 3, 1));
	}
	
	

}

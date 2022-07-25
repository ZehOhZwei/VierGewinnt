import java.util.Random;

//comment and me
public class GameAPI {
	public IngameUI ui;
	public PlayingField _board;
	public int _x;
	public int _y;
	public int turn;
	public boolean againstBot;
	public Bot _bot;

	public GameAPI(int w, int h) {
		ui = new IngameUI(this, w, h);

		againstBot = false;
		_x = w;
		_y = h;
		_board = new PlayingField(_x, _y, this);
		turn = 1;
	}

	public GameAPI(int w, int h, Bot bot) {
		againstBot = true;
		_bot = bot;
		_x = w;
		_y = h;
		_board = new PlayingField(_x, _y, this);
		turn = 1;

	}

	public void dropStone(int column) {
		_board.dropStone(column, turn);
		switchTurn();
		return;
	}

	public void turnLeft() {
		_board.turnLeft(turn);
		ui.fieldPanel.turn(_x, _y);
		switchTurn();
	}

	public void turnRight() {
		_board.turnRight(turn);
		ui.fieldPanel.turn(_x, _y);
		switchTurn();
	}

	private void switchTurn() {
		if (turn == 1)
			turn = 2;
		else if (turn == 2)
			turn = 1;
	}

	public int getCell(int x,int  y) {
		return _board.getCell(x, y);
	}
	//This is for testing purposes only
	public void setCell(int x, int y, int value) {
		_board.setCell(x, y, value);
	}
	
	public int getHeight() {
		return _board.getHeight();
	}

	public int getWidth() {
		return _board.getWidth();
	}

	public int getHighestEmptySpace(int column) {
		return _board.getHighestEmptySpace(column);
	}

}

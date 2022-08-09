import java.util.Random;

//comment and me
public class GameAPI {
	public IngameUI ui;
	public PlayingField _board;
	public int width;
	public int _y;
	public int turn;
	public boolean againstBot;
	public Bot _bot;
	public int _initX;
	public int _initY;
	
	/**
	 * The constructor for if there is no Bot present in the game. 
	 * Creates a new IngameUI with the given width and height parameters 
	 * and sets some variables to be able to start the game.
	 * Player 1 will always have the first move.
	 * 
	 * @param w The width of the playing field
	 * @param h The height of the Playing field
	 */
	public GameAPI(int w, int h) {
		ui = new IngameUI(this, w, h);

		againstBot = false;
		width = w;
		_y = h;
		_initX = width;
		_initY = _y;
		_board = new PlayingField(width, _y);
		turn = 1;
	}

	/**
	 * The overloaded constructor for the game against a Bot. 
	 * Does everything the other constructer does as well as set the variable AgainstBot to true.
	 * 
	 * @param w The width of the playing field
	 * @param h The height of the Playing field
	 * @param bot the Bot that was created earlier and that will be used for the game
	 */
	public GameAPI(int w, int h, Bot bot) {
		this(w, h);
		
		againstBot = bot != null;
		_bot = bot;
	}
	
	/**
	 * Calls the method dropStone in the PlayingField and switches the turn to the other player.
	 * Importantly, the int column here refers to the place in the array meaning that 
	 * for a stone to be dropped in the first column, the value 0 needs to be passed in the method.
	 * 
	 * @param column the column into which the stone is going to be dropped.
	 */
	public void dropStone(int column) {
		boolean stoneDropped = _board.dropStone(column, turn);
		checkForWinAndShowWinningWindow(turn);
		ui.update();
		if (stoneDropped) {
			switchTurn();
		}
	}

	/**
	 * Calls the turnLeft method in the PlayingField, passing the current turn so that CheckForWin can work properly
	 * After this, the UI needs to be updated to a new playing field so the new bounds of the playing field are given to the FieldPanel
	 * Finally, the turn is switched to the other player so rotating the playing field also constitutes a turn
	 */
	public void turnLeft() {
		_board.turnLeft();
		checkForWinAndShowWinningWindow(1);
		checkForWinAndShowWinningWindow(2);
		ui.fieldPanel.rotate(width, _y);
		switchTurn();
	}

	/**
	 * Calls the turnRight method in the PlayingField, passing the current turn so that CheckForWin can work properly
	 * After this, the UI needs to be updated to a new playing field so the new bounds of the playing field are given to the FieldPanel
	 * Finally, the turn is switched to the other player so rotating the playing field also constitutes a turn
	 */
	public void turnRight() {
		_board.turnRight();
		checkForWinAndShowWinningWindow(1);
		checkForWinAndShowWinningWindow(2);
		ui.fieldPanel.rotate(width, _y);
		switchTurn();
	}


	private void switchTurn() {
		if (turn == 1) {
			turn = 2;
			makeBotTurn();
		} else if (turn == 2) {
			turn = 1;
		}
	}
	
	private void makeBotTurn() {
		_board = _bot.makeTurn(_board);
		turn = 1;
	}

	public int getCell(int x,int  y) {
		return _board.getCell(x, y);
	}


	/**
	 * Gets the current height of the board, calling the getHeight method in the PlayingField
	 * 
	 * @return the boards height
	 */
	
	public int getHeight() {
		return _board.getHeight();
	}

	/**
	 * Gets the current width of the board, calling the getWidth method in the PlayingField
	 * 
	 * @return the boards width
	 */
	public int getWidth() {
		return _board.getWidth();
	}

	/**
	 * Calls the getLowestEmptySpace method in the PlayingField
	 * 
	 * @param column the column that needs to be checked
	 * @return the lowest empty space in the specified column
	 */
	public int getLowestEmptySpace(int column) {
		return _board.getLowestEmptySpace(column);
	}
	
	/**
	 * This method checks if the given player won and shows a Congratulations window
	 * 
	 * @param turn the specified player for which the win condition should be checked
	 */
	public void checkForWinAndShowWinningWindow(int turn) {
		if (_board.checkForWin(turn)) {
			WinWindow win = new WinWindow(turn, this);
		}
	}
	
}

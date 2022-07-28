
public class PlayingField {

	private int[][] _board;
	private int _x;
	private int _y;
	private GameAPI api;

	/**
	 * The constructor for the PlayingField. This only saves the passed in variables
	 * as well as initializing the _board array which represents the actual playing
	 * field with the passed in x and y values.
	 * 
	 * @param x       the width of the playing field
	 * @param y       the height of the playing field
	 * @param gameAPI A reference to the GameAPI that created this PlayingField
	 */
	public PlayingField(int x, int y, GameAPI gameAPI) {
		_board = new int[x][y];
		_x = x;
		_y = y;
		api = gameAPI;
	}

	/**
	 * This method checks for the highest empty space and marks the corresponding
	 * space in the array with the value of the current turn
	 * 
	 * @param column the column in which to drop a stone
	 * @param turn   the current turn, used for the check for win method
	 */
	public void dropStone(int column, int turn) {
		setCell(column, getHighestEmptySpace(column), turn);
		checkForWin(turn);
		// checkForDraw();
		return;
	}

	/**
	 * Rotates the playing field by 90 degrees to the left. This is done by firstly
	 * going through the entire playing field and putting every stone into a
	 * corresponding space in a new rotated array after this, the current playing
	 * field is overriden by the new playing field and all cells with a stone in
	 * them have the cascadeDown method called on them. After this is done the board
	 * is successfully rotated and it is checked whether this action has caused
	 * anyone to win.
	 */
	public void turnLeft() {
		int[][] newBoard = new int[_y][_x];
		for (int i = 0; i < _x; i++) {
			for (int j = 0; j < _y; j++) {
				newBoard[_y - j - 1][i] = _board[i][j];
			}
		}

		_board = newBoard;
		int newX = _y;
		int newY = _x;
		_x = newX;
		_y = newY;

		for (int i = 0; i < _x; i++) {
			for (int j = 1; j < _y; j++) {
				cascadeDown(i, j);
			}
		}
		checkForWin(1);
		checkForWin(2);
	}

	/**
	 * Rotates the playing field by 90 degrees to the left. This is done by firstly
	 * going through the entire playing field and putting every stone into a
	 * corresponding space in a new rotated array after this, the current playing
	 * field is overriden by the new playing field and all cells with a stone in
	 * them have the cascadeDown method called on them. After this is done the board
	 * is successfully rotated and it is checked whether this action has caused
	 * anyone to win.
	 */
	public void turnRight() {
		int[][] newBoard = new int[_y][_x];
		for (int i = 0; i < _x; i++) {
			for (int j = 0; j < _y; j++) {
				newBoard[j][_x - i - 1] = _board[i][j];
			}
		}

		_board = newBoard;
		int newX = _y;
		int newY = _x;
		_x = newX;
		_y = newY;

		for (int i = 0; i < _x; i++) {
			for (int j = 1; j < _y; j++) {
				cascadeDown(i, j);
			}
		}
		checkForWin(1);
		checkForWin(2);
	}

	private void cascadeDown(int i, int j) {
		if (j > 0 && _board[i][j] != 0) {
			if (_board[i][j - 1] == 0) {
				_board[i][j - 1] = _board[i][j];
				_board[i][j] = 0;
				cascadeDown(i, j - 1);
			} else
				return;
		} else
			return;
	}

	/**
	 * A helping method to check whether the specified player has four stones in a horizontal row.
	 * This method presumes a space has alreay been filled with the stone of the player who has just dropped a stone.
	 * Firstly the space to the left of that space is checked. 
	 * It is important here that the if-statement for checking if 
	 * the array even goes as far as we need to check is checked first.
	 * Otherwise the array would still have issues with out-of-bounds exceptions.
	 * If the space one to the left of the initial space is filled with the same value as the initial space,
	 * the counter variable i is incremented by one. 
	 * This process is done two more times in the same way, after which, the space to the right of the initial space is checked
	 * If it is also filled with the same value as the others, the same variable i is incremented.
	 * This serves to cover the case in which a player places their stone in the middle of other stones,
	 * thus getting four stones in a row. 
	 * 
	 * @param x the horizontal position of the space that should be checked
	 * @param y the vertical position of the space that should be checked
	 * @param turn the value for which should be checked.
	 * @return a boolean value representing whether or not the current player has four in a row
	 */
	public boolean checkHorizontal(int x, int y, int turn) {
		int i = 1;
		if (x - 1 >= 0) {
			if (_board[x - 1][y] == turn) {
				i++;
				if (x - 2 >= 0) {
					if (_board[x - 2][y] == turn) {
						i++;
						if (x - 3 >= 0) {
							if (_board[x - 3][y] == turn) {
								i++;
							}
						}
					}
				}
			}
		}
		if (x + 1 > _x) {
			if (_board[x + 1][y] == turn) {
				i++;
				if (x + 2 > _x) {
					if (_board[x + 2][y] == turn) {
						i++;
						if (x + 3 > _x) {
							if (_board[x + 3][y] == turn) {
								i++;
							}
						}
					}
				}
			}
		}
		return (i >= 4);
	}

	/**
	 * A helping method to check whether the specified player has four stones in a vertical row.
	 * This method presumes a space has alreay been filled with the stone of the player who has just dropped a stone.
	 * Firstly the space to the left of that space is checked. 
	 * It is important here that the if-statement for checking if 
	 * the array even goes as far as we need to check is checked first.
	 * Otherwise the array would still have issues with out-of-bounds exceptions.
	 * If the space one to the left of the initial space is filled with the same value as the initial space,
	 * the counter variable i is incremented by one. 
	 * This process is done two more times in the same way, after which, the space to the right of the initial space is checked
	 * If it is also filled with the same value as the others, the same variable i is incremented.
	 * This serves to cover the case in which a player places their stone in the middle of other stones,
	 * thus getting four stones in a row. 
	 * 
	 * @param x the horizontal position of the space that should be checked
	 * @param y the vertical position of the space that should be checked
	 * @param turn the value for which should be checked.
	 * @return a boolean value representing whether or not the current player has four in a row
	 */
	public boolean checkVertical(int x, int y, int turn) {
		int i = 1;
		if (y - 1 >= 0) {
			if (_board[x][y - 1] == turn) {
				i++;
				if (y - 2 >= 0) {
					if (_board[x][y - 2] == turn) {
						i++;
						if (y - 3 >= 0) {
							if (_board[x][y - 3] == turn) {
								i++;
							}
						}
					}
				}
			}
		}
		if (y + 1 > _x) {
			if (_board[x][y + 1] == turn) {
				i++;
				if (y + 2 > _x) {
					if (_board[x][y + 2] == turn) {
						i++;
						if (y + 3 > _x) {
							if (_board[x][y + 3] == turn) {
								i++;
							}
						}
					}
				}
			}
		}
		return (i >= 4);
	}

	/**
	 * A helping method to check whether the specified player has four stones in a diagonal rising row.
	 * This method presumes a space has alreay been filled with the stone of the player who has just dropped a stone.
	 * Firstly the space to the left of that space is checked. 
	 * It is important here that the if-statement for checking if 
	 * the array even goes as far as we need to check is checked first.
	 * Otherwise the array would still have issues with out-of-bounds exceptions.
	 * If the space one to the left of the initial space is filled with the same value as the initial space,
	 * the counter variable i is incremented by one. 
	 * This process is done two more times in the same way, after which, the space to the right of the initial space is checked
	 * If it is also filled with the same value as the others, the same variable i is incremented.
	 * This serves to cover the case in which a player places their stone in the middle of other stones,
	 * thus getting four stones in a row. 
	 * 
	 * @param x the horizontal position of the space that should be checked
	 * @param y the vertical position of the space that should be checked
	 * @param turn the value for which should be checked.
	 * @return a boolean value representing whether or not the current player has four in a row
	 */
	public boolean checkDiagonalRising(int x, int y, int turn) {
		int i = 1;

		if (x - 1 >= 0 && y - 1 >= 0) {
			if (_board[x - 1][y - 1] == turn) {
				i++;
				if (x - 2 >= 0 && y - 2 >= 0) {
					if (_board[x - 2][y - 2] == turn) {
						i++;
						if (x - 3 >= 0 && y - 3 >= 0) {
							if (_board[x - 3][y - 3] == turn) {
								i++;
							}
						}
					}
				}
			}
		}
		if (x + 1 >= _x && y + 1 >= _y) {
			if (_board[x + 1][y + 1] == turn) {
				i++;
				if (x + 2 >= _x && y + 2 >= _y) {
					if (_board[x + 2][y + 2] == turn) {
						i++;
						if (x + 3 >= _x && y + 3 >= _y) {
							if (_board[x + 3][y + 3] == turn)

							{
								i++;
							}
						}
					}
				}
			}
		}
		return (i >= 4);
	}

	/**
	 * A helping method to check whether the specified player has four stones in a diagonal falling row.
	 * This method presumes a space has alreay been filled with the stone of the player who has just dropped a stone.
	 * Firstly the space to the left of that space is checked. 
	 * It is important here that the if-statement for checking if 
	 * the array even goes as far as we need to check is checked first.
	 * Otherwise the array would still have issues with out-of-bounds exceptions.
	 * If the space one to the left of the initial space is filled with the same value as the initial space,
	 * the counter variable i is incremented by one. 
	 * This process is done two more times in the same way, after which, the space to the right of the initial space is checked
	 * If it is also filled with the same value as the others, the same variable i is incremented.
	 * This serves to cover the case in which a player places their stone in the middle of other stones,
	 * thus getting four stones in a row. 
	 * 
	 * @param x the horizontal position of the space that should be checked
	 * @param y the vertical position of the space that should be checked
	 * @param turn the value for which should be checked.
	 * @return a boolean value representing whether or not the current player has four in a row
	 */
	public boolean checkDiagonalFalling(int x, int y, int turn) {
		int i = 1;
		if (x - 1 >= 0 && y + 1 >= 0) {
			if (_board[x - 1][y + 1] == turn) {
				i++;
				if (x - 2 >= 0 && y + 2 >= 0) {
					if (_board[x - 2][y + 2] == turn) {
						i++;
						if (x - 3 >= 0 && y + 3 >= 0) {
							if (_board[x - 3][y + 3] == turn)

							{
								i++;
							}
						}
					}
				}
			}
		}
		if (x + 1 >= _x && y - 1 >= _y) {
			if (_board[x + 1][y - 1] == turn) {
				i++;
				if (x + 2 >= _x && y - 2 >= _y) {
					if (_board[x + 2][y - 2] == turn) {
						i++;
						if (x + 3 >= _x && y - 3 >= _y) {
							if (_board[x + 3][y - 3] == turn)

							{
								i++;
							}
						}
					}
				}
			}
		}
		return (i >= 4);
	}

	/**
	 * This method goes through the entire array, checking whether a specified player has four stones in a row anywhere.
	 * 
	 * @param turn the specified player for which the win condition should be checked
	 */
	public void checkForWin(int turn) {

		for (int i = 0; i < _x - 1; i++) {
			for (int j = 0; j < _y - 1; j++) {
				if (_board[i][j] == turn) {

					if ((checkHorizontal(i, j, turn) || checkVertical(i, j, turn) || checkDiagonalRising(i, j, turn)
							|| checkDiagonalFalling(i, j, turn))) {
						WinWindow win = new WinWindow(turn, api);
					}
				}
			}
		}
	}

	
	/**
	 * This method checks whether there are any free spaces left. 
	 * If there are not, the game will be drawn.
	 * 
	 * @return a boolean value representing whether a draw has ocurred
	 */
	public boolean checkForDraw() {
		for (int i = 0; i <= _x; i++) {
			if (_board[i][_y] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the value of a specified cell in the array
	 * 
	 * @param x the x-position of the cell that is to be returned
	 * @param y the y-position of the cell that is to be returned
	 * @return the value of the specified cell
	 */
	public int getCell(int x, int y) {

		if (x < _x && y < _y) {
			return _board[x][y];
		}
		return 0;
	}	
	
	/**
	 * A method that sets the value of a specified cell. 
	 * !Should only be used for testing!
	 * 
	 * @param x the x-position of the cell that is to be set to a new value
	 * @param y the y-position of the cell that is to be set to a new value
	 * @param value the new value the specified cell should be set to
	 */
	public void setCell(int x, int y, int value) {
		_board[x][y] = value;
	}

	/**
	 * Returns the current height of the playing field
	 * 
	 * @return the current height of the playing field
	 */
	public int getHeight() {
		return _y;
	}

	/**
	 * Returns the current width of the playing field
	 * 
	 * @return the current width of the playing field
	 */
	public int getWidth() {
		return _x;
	}
	
	/**
	 * Goes through the given column to find the highest empty space in the column.
	 * 
	 * @param column the column that should be checked
	 * @return either the y-position of the highest empty space or -1 in the case of an error 
	 */
	public int getHighestEmptySpace(int column) {
		for (int i = 0; i <= _y; i++) {
			if (_board[column][i] == 0) {
				return i;
			}
		}
		return -1;
	}

}

import java.util.Random;

//comment and me
public class GameAPI {
	public IngameUI ui;
	public int[][] _board;
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
		_board = new int[_x][_y];
		turn = 1;
		System.out.println(_board.length);
	}

	public GameAPI(int w, int h, Bot bot) {
		againstBot = true;
		_bot = bot;
		_x = w;
		_y = h;
		_board = new int[_x][_y];
		turn = 1;

	}

	public void dropStone(int column) {
		for (int i = 0; i <= _y; i++) {
			if (_board[column][i] == 0) {
				_board[column][i] = turn;
				ui.dropStone(column, i, turn);

				checkForWin();
				// checkForDraw();
				switchTurn();
				return;
			}
		}
		switchTurn();
		return;
	}

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
		checkForWin();
		ui.fieldPanel.turn(_x, _y);
		switchTurn();
	}

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
		checkForWin();
		ui.fieldPanel.turn(_x, _y);
		switchTurn();
	}

	private void switchTurn() {
		if (turn == 1)
			turn = 2;
		else if (turn == 2)
			turn = 1;
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
	
	// Diese genesteten if-statements sind n�tig, weil ansonsten �ber die Bounds
	// des
	// Arrays hinausgegangen wird
	public boolean checkHorizontal(int x, int y) {
		int i = 1;
		if (x - 1 >= 0) {
			if (_board[x - 1][y] == turn) {
				i++;
				if (x - 2 >= 0) {
					if (_board[x - 2][y] == turn) {
						i++;
						if (x - 3 >= 0) {
							if (_board[x - 3][y] == turn)

							{
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
							if (_board[x + 3][y] == turn)

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

	public boolean checkVertical(int x, int y) {
		int i = 1;
		if (y - 1 >= 0) {
			if (_board[x][y - 1] == turn) {
				i++;
				if (y - 2 >= 0) {
					if (_board[x][y - 2] == turn) {
						i++;
						if (y - 3 >= 0) {
							if (_board[x][y - 3] == turn)

							{
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
							if (_board[x][y + 3] == turn)

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

	public boolean checkDiagonalRising(int x, int y) {
		int i = 1;

		if (x - 1 >= 0 && y - 1 >= 0) {
			if (_board[x - 1][y - 1] == turn) {
				i++;
				if (x - 2 >= 0 && y - 2 >= 0) {
					if (_board[x - 2][y - 2] == turn) {
						i++;
						if (x - 3 >= 0 && y - 3 >= 0) {
							if (_board[x - 3][y - 3] == turn)

							{
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

	public boolean checkDiagonalFalling(int x, int y) {
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

	public void checkForWin() {

		for (int i = 0; i < _x - 1; i++) {
			for (int j = 0; j < _y - 1; j++) {
				if (_board[i][j] != 0) {

					if ((checkHorizontal(i, j) || checkVertical(i, j) || checkDiagonalRising(i, j)
							|| checkDiagonalFalling(i, j))) {
						WinWindow win = new WinWindow(turn, this);
						System.out.println("Player " + turn + " wins");
					}
				}
			}
		}
	}

	public boolean checkForDraw() {
		for (int i = 0; i <= _x; i++) {
			if (_board[i][_y] == 0) {
				return false;
			}
		}
		return true;
	}

	public int getHeight() {
		return _y;
	}

	public int getWidth() {
		return _x;
	}

	public int getHighestEmptySpace(int column) {
		for (int i = 0; i <= _y; i++) {
			if (_board[column][i] == 0) {
				return i;
			}
		}
		return -1;
	}

}

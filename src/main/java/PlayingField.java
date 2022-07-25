
public class PlayingField {
	
	private int[][] _board;
	private int _x;
	private int _y;
	private GameAPI api;

	public PlayingField(int x, int y, GameAPI gameAPI){
		_board = new int[x][y];
		_x = x;
		_y = y;
		api = gameAPI;
	}
	
	public void dropStone(int column, int turn) {
		for (int i = 0; i <= _y; i++) {
			if (_board[column][i] == 0) {
				_board[column][i] = turn;
				checkForWin(turn);
				//checkForDraw();
				return;
			}
		}
		return;
	}
	
	public void turnLeft(int turn) {
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
		checkForWin(turn);
	}

	public void turnRight(int turn) {
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
		checkForWin(turn);
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
	
	public boolean checkHorizontal(int x, int y, int turn) {
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

	public boolean checkVertical(int x, int y, int turn) {
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

	public boolean checkDiagonalRising(int x, int y, int turn) {
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

	public void checkForWin(int turn) {

		for (int i = 0; i < _x - 1; i++) {
			for (int j = 0; j < _y - 1; j++) {
				if (_board[i][j] != 0) {

					if ((checkHorizontal(i, j, turn) || checkVertical(i, j, turn) || checkDiagonalRising(i, j, turn)
							|| checkDiagonalFalling(i, j, turn))) {
						WinWindow win = new WinWindow(turn, api);
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
	
	public int getCell(int x, int y) {
		
		if(x < _x && y < _y) {
			return _board[x][y];
		}
		return 0;
	}
	//This is for testing purposes only
	public void setCell(int x, int y, int value) {
		_board[x][y] = value;		
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

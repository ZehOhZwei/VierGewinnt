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
	
	public GameAPI(int w, int h)
	{
		ui = new IngameUI(this, w, h);

		againstBot=false;
		_board = new int[w][h];
		_x = w;
		_y = h;
		Random random= new Random();
		turn = random.nextInt(1, 2);
	}
	
	public GameAPI(int w, int h, Bot bot)
	{
		againstBot=true;		
		_bot=bot;
		_x=w;
		_y=h;
		_board = new int[_x][_y];
		Random random= new Random();
		turn = random.nextInt(1, 2);
		
	}
	
	public void dropStone(int column)
	{
		System.out.println("Player " + turn + " drops piece in column " + (column + 1));
		for(int i=0;i<=_y;i++)
		{
			if (_board[column][i]==0)
			{
				_board[column][i]=turn;
				ui.dropStone(column, i, turn);

				
				if(checkForWin(column, i)) {
					System.out.println("Spieler " + turn + " Gewinnt");
				}
				//checkForDraw();
				if(turn == 1) turn = 2;
				else if(turn == 2) turn = 1;
				return;
			}
		}     
		
		return;
	}
	
	//Diese genesteten if-statements sind nötig, weil ansonsten über die Bounds des Arrays hinausgegangen wird
	public boolean checkHorizontal(int x, int y)
	{
		int i=1;
		if (x-1>=0) {
			if  (_board[x-1][y]==turn){
				i++;
				if (x-2>=0) {
					if( _board[x-2][y]==turn){
						i++;
						if (x-3>=0) {
							if (_board[x-3][y]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		if (x+1>_x) {
			if  (_board[x+1][y]==turn){
				i++;
				if (x+2>_x) {
					if( _board[x+2][y]==turn){
						i++;
						if (x+3>_x) {
							if (_board[x+3][y]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		return (i>=4);
	}
	public boolean checkVertical(int x, int y)
	{
		int i=1;
		if (y-1>=0) {
			if  (_board[x][y-1]==turn){
				i++;
				if (y-2>=0) {
					if( _board[x][y-2]==turn){
						i++;
						if (y-3>=0) {
							if (_board[x][y-3]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		if (y+1>_x) {
			if  (_board[x][y+1]==turn){
				i++;
				if (y+2>_x) {
					if( _board[x][y+2]==turn){
						i++;
						if (y+3>_x) {
							if (_board[x][y+3]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		return (i>=4);
	}
	public boolean checkDiagonalRising(int x, int y)
	{
		int i=1;

		if (x-1>=0 && y-1>=0) {
			if  ( _board[x-1][y-1]==turn){
				i++;
				if (x-2>=0 && y-2>=0) {
					if( _board[x-2][y-2]==turn){
						i++;
						if (x-3>=0 && y-3>=0) {
							if (_board[x-3][y-3]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		if (x+1>=_x && y+1>=_y) {
			if  (_board[x+1][y+1]==turn){
				i++;
				if (x+2>=_x && y+2>=_y) {
					if(_board[x+2][y+2]==turn){
						i++;
						if (x+3>=_x && y+3>=_y) {
							if ( _board[x+3][y+3]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		return (i>=4);
	}
	public boolean checkDiagonalFalling(int x, int y)
	{
		int i=1;
		if (x-1>=0 && y+1>=0) {
			if  ( _board[x-1][y+1]==turn){
				i++;
				if (x-2>=0 && y+2>=0) {
					if( _board[x-2][y+2]==turn){
						i++;
						if (x-3>=0 && y+3>=0) {
							if (_board[x-3][y+3]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		if (x+1>=_x && y-1>=_y) {
			if  (_board[x+1][y-1]==turn){
				i++;
				if (x+2>=_x && y-2>=_y) {
					if(_board[x+2][y-2]==turn){
						i++;
						if (x+3>=_x && y-3>=_y) {
							if ( _board[x+3][y-3]==turn)
						
							{
								i++;
							}
						}
					}
				}
			}
		}
		return (i>=4);
	}
	
	public boolean checkForWin(int x, int y)
	{
		return(checkHorizontal(x, y) || checkVertical(x, y) ||checkDiagonalRising(x, y)||checkDiagonalFalling(x, y));
	}

	public boolean checkForDraw()
	{
		for(int i=0;i<=_x;i++)
		{
			if (_board[i][_y]==0)
			{
				return false;
			}
		}
		return true;
	}
	
	
	

}

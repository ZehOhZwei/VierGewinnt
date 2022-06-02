import java.util.Random;
//comment
public class GameAPI {
	public int[][] _board;
	public int _x=6;
	public int _y=5;
	public int turn;
	public Player player1;
	public Player player2;
	
	public GameAPI(int w, int h, Player p1, Player p2)
	{
		_x=w;
		_y=h;
		_board = new int[_x][_y];
		player1=p1;
		player2=p2;
		Random random= new Random();
		turn = random.nextInt(1, 2);
		
	}
	
	public void dropStone(int column)
	{
		for(int i=0;i<=_y;i++)
		{
			if (_board[column][i]==0)
			{
				_board[column][i]=turn;
				checkForWin(column, i);
				checkForDraw();
				return;
			}
		}     
		return;
	}
	
	public boolean checkHorizontal(int x, int y)
	{
		int i=1;
		if (x-1>=0 && _board[x-1][y]==turn)
		{
			i++;
			if (x-2>=0 && _board[x-2][y]==turn)
			{
				i++;
				if (x-3>=0 && _board[x-3][y]==turn)
				{
					i++;
				}
			}
		}
		if (x+1>=_x && _board[x+1][y]==turn)
		{
			i++;
			if (x+2>=_x && _board[x+2][y]==turn)
			{
				i++;
				if (x+3>=_x && _board[x+3][y]==turn)
				{
					i++;
				}
			}
		}
		return (i>=4);
	}
	public boolean checkVertical(int x, int y)
	{
		int i=1;
		if (y-1>=0 && _board[x][y-1]==turn)
		{
			i++;
			if (y-2>=0 && _board[x][y-2]==turn)
			{
				i++;
				if (y-3>=0 && _board[x][y-3]==turn)
				{
					i++;
				}
			}
		}
		if (y+1>=_y && _board[x][y+1]==turn)
		{
			i++;
			if (y+2>=_y && _board[x][y+2]==turn)
			{
				i++;
				if (y+3>=_y && _board[x][y+3]==turn)
				{
					i++;
				}
			}
		}
		return (i>=4);
	}
	public boolean checkDiagonalRising(int x, int y)
	{
		int i=1;
		if (x-1>=0 && y-1>=0 && _board[x-1][y-1]==turn)
		{
			i++;
			if (x-2>=0 && y-2>=0 && _board[x-2][y-2]==turn)
			{
				i++;
				if (x-3>=0 && y-3>=0 && _board[x-3][y-3]==turn)
				{
					i++;
				}
			}
		}
		if (x+1>=_x && y+1>=_y && _board[x+1][y+1]==turn)
		{
			i++;
			if (x+2>=_x && y+2>=_y && _board[x+2][y+2]==turn)
			{
				i++;
				if (x+3>=_x && y+3>=_y && _board[x+3][y+3]==turn)
				{
					i++;
				}
			}
		}
		return (i>=4);
	}
	public boolean checkDiagonalFalling(int x, int y)
	{
		int i=1;
		if (x-1>=0 && y+1>=_y && _board[x-1][y+1]==turn)
		{
			i++;
			if (x-2>=0 && y+2>=_y && _board[x-2][y+2]==turn)
			{
				i++;
				if (x-3>=0 && y+3>=_y && _board[x-3][y+3]==turn)
				{
					i++;
				}
			}
		}
		if (x+1>=_x && y-1>=0 && _board[x+1][y-1]==turn)
		{
			i++;
			if (x+2>=_x && y-2>=0 && _board[x+2][y-2]==turn)
			{
				i++;
				if (x+3>=_x && y-3>=0 && _board[x+3][y-3]==turn)
				{
					i++;
				}
			}
		}
		return (i>=4);
	}
	
	public boolean checkForWin(int x, int y)
	{
		return(checkHorizontal(x, y) || checkVertical(x, y) ||checkDiagonalRising(x, y)||checkDiagonalRising(x, y));
	}
	
	public boolean checkForWin()
	{
		
		return true;
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
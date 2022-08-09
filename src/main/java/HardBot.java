
public class HardBot implements Bot {

	private static final int SINGLE_STONE_VALUE = 100;
	private static final int TWOS_ROW_VALUE = 500;
	private static final int THREES_ROW_VALUE = 1500;
	
	private final int botPlayer;
	
	public HardBot(int botPlayer) {
		this.botPlayer = botPlayer;
	}
	
	@Override
	public PlayingField makeTurn(PlayingField board) {
		return alphabeta(board, 10, Integer.MIN_VALUE, Integer.MAX_VALUE, true).board;
	}
	
	public Move alphabeta(PlayingField node, int depth, int alpha, int beta, boolean maximizingPlayer) {
		int player = maximizingPlayer ? botPlayer : otherPlayer(botPlayer);
		
		int valueForPlayer = evaluateBoardForPlayer(node, player);
		int valueForOtherPlayer = evaluateBoardForPlayer(node, otherPlayer(player));
		
		if (depth == 0 || valueForPlayer == Integer.MAX_VALUE) {
			return new Move(node, valueForPlayer  - valueForOtherPlayer);
		}
		
		if (maximizingPlayer) {
			int value = Integer.MIN_VALUE;
			PlayingField board = node;
			for (PlayingField child : node.getPlayingFieldsForNextMove(player)) {
				Move move = alphabeta(child, depth - 1, alpha, beta, false);
				
				if (move.heuristicValue > value) {
					value = move.heuristicValue;
					board = child;
				}
				
				if (value >= beta) {
					break;
				}
				
				alpha = Math.max(alpha, value);
			}
			return new Move(board, value);
		} else {
			int value = Integer.MAX_VALUE;
			PlayingField board = node;

			for (PlayingField child : node.getPlayingFieldsForNextMove(player)) {
				Move move = alphabeta(child, depth - 1, alpha, beta, true);
				
				if (move.heuristicValue < value) {
					value = move.heuristicValue;
					board = child;
				}
				
				if (value <= alpha) {
					break;
				}
				
				beta = Math.min(beta, value);
			}
			return new Move(board, value);
		}
	}
	
	@Deprecated
	public int evaluateBoard(PlayingField board, int player) {
		return evaluateBoardForPlayer(board, player) - evaluateBoardForPlayer(board, otherPlayer(player));
	}
	
	private int otherPlayer(int turn) {
		if (turn == 1)
			return 2;
		else
			return 1;
	}
	
	private int evaluateBoardForPlayer(PlayingField board, int player) {
		int foursCount = countRows(board, player, 4);
		int threesCount = countRows(board, player, 3);
		int twosCount = countRows(board, player, 2);
		int stoneCount = countStones(board, player);
		
		if (foursCount >= 1) {
			return Integer.MAX_VALUE;
		}
		
		if (threesCount >= 2) {
			return 900000;
		}
		
		return stoneCount * SINGLE_STONE_VALUE + twosCount * TWOS_ROW_VALUE + threesCount * THREES_ROW_VALUE;
	}
	
	private int countStones(PlayingField board, int player) {
		int stones = 0;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.getCell(x, y) == player) {
					stones++;
				}
			}
		}
		return stones;
	}
	
	private int countRows(PlayingField board, int player, int rowSize) {
		int verticalRows = 0;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.checkVertical(x, y, player) == rowSize) {
					verticalRows++;
				}
				if (board.checkHorizontal(x, y, player) == rowSize) {
					verticalRows++;
				}
				if (board.checkDiagonalFalling(x, y, player) == rowSize) {
					verticalRows++;
				}
				if (board.checkDiagonalRising(x, y, player) == rowSize) {
					verticalRows++;
				}
			}
		}
		return verticalRows;
	}
	
	static class Move {
		public PlayingField board;
		public int heuristicValue;
		public Move(PlayingField board, int heuristicValue) {
			super();
			this.board = board;
			this.heuristicValue = heuristicValue;
		}
	}
	
}

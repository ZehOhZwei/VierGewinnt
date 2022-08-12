
public class HardBot implements Bot {

	private static final int SINGLE_STONE_VALUE = 100;
	private static final int TWOS_ROW_VALUE = 500;
	private static final int THREES_ROW_VALUE = 3000;
	
	private final int botPlayer;
	
	public HardBot(int botPlayer) {
		this.botPlayer = botPlayer;
	}
	
	@Override
	public PlayingField makeTurn(PlayingField board) {
//		System.out.println("###############");
//		System.out.println(board.toString());
//		System.out.println(evaluateBoardForPlayer(board, botPlayer, false));
		return alphabeta(board, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true).board;
	}
	
	public Move alphabeta(PlayingField node, int depth, int alpha, int beta, boolean maximizingPlayer) {
		int player = maximizingPlayer ? botPlayer : otherPlayer(botPlayer);
		
		int valueForPlayer = evaluateBoardForPlayer(node, player);
		int valueForOtherPlayer = evaluateBoardForPlayer(node, otherPlayer(player));
		
//		System.out.println("DEPTH: " + depth);
//		
//		System.out.println("player: " + player);
//		System.out.println("move: " + node.moveName);
//		System.out.println("valueForPlayer: " + valueForPlayer);
//		System.out.println("valueForOtherPlayer: " + valueForOtherPlayer);
		
		if (depth == 0) {
			if (player == 2) {
				return new Move(node, valueForPlayer - valueForOtherPlayer);
			} else {
				return new Move(node, valueForOtherPlayer - valueForPlayer);
			}
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
			
//			System.out.println("DEPTH END: " + depth);
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
//			System.out.println("DEPTH END: " + depth);
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
		return evaluateBoardForPlayer(board, player, false);
	}
	
	private int evaluateBoardForPlayer(PlayingField board, int player, boolean debug) {
		int foursCount = countRows(board, player, 4, debug);
		int threesCount = countRows(board, player, 3, debug);
		int twosCount = countRows(board, player, 2, debug);
		int singleStoneValue = calculateValueOfSingleStone(board, player);
		
		if (debug) {
			System.out.println("twos: " + twosCount);
			System.out.println("threes: " + threesCount);
			System.out.println("fours: " + foursCount);
			System.out.println("singleStoneValue: " + singleStoneValue);
		}
		
		if (foursCount >= 1) {
			return Integer.MAX_VALUE;
		}
		
		if (threesCount >= 2) {
			return 1000000;
		}
		
		return singleStoneValue + twosCount * TWOS_ROW_VALUE + threesCount * THREES_ROW_VALUE;
	}
	
	/**
	 * Gives the value
	 * 
	 * @param board The board for which the value is to be calculated
	 * @param player The player for whom the value is to be calculated for.
	 * @return returns the value the bot gives to a single stone.
	 */
	private int calculateValueOfSingleStone(PlayingField board, int player) {
		int value = 0;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.getCell(x, y) == player) {
					value= value + SINGLE_STONE_VALUE + calculateCenterValue(board, x);
				}
			}
		}
		return value;
	}
	
	public int calculateCenterValue(PlayingField board, int column) {
		return board.getWidth() - Math.abs(board.getWidth() / 2 - column);
	}
	
	/**
	 * Counts the rows of a designated length for a player on the given board. 
	 * Where a row are stones set next to each other in the vertical, horizontal or diagonal on the board.
	 *  
	 * @param board The board the rows are to be counted on.
	 * @param player Player whose row's are to be searched
	 * @param rowSize The size of rows you wish to know
	 * @param debug
	 * @return Number of rows
	 */
	private int countRows(PlayingField board, int player, int rowSize, boolean debug) {
		int rows = 0;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.getCell(x, y) != player) {
					continue;
				}
				
				if (board.checkVertical(x, y, player) == rowSize) {
					rows++;
					if (debug) {
						System.out.println("vertical row at " + x + "|" + y + " for player " + player);
					}
				}
				if (board.checkHorizontal(x, y, player) == rowSize) {
					rows++;
					if (debug) {
						System.out.println("horizontal row at " + x + "|" + y + " for player " + player);
					}
				}
				if (board.checkDiagonalFalling(x, y, player) == rowSize) {
					rows++;
					if (debug) {
						System.out.println("falling row at " + x + "|" + y + " for player " + player);
					}
				}
				if (board.checkDiagonalRising(x, y, player) == rowSize) {
					rows++;
					if (debug) {
						System.out.println("rising row at " + x + "|" + y + " for player " + player);
					}
				}
			}
		}
		return rows;
	}
	/**
	 * A Class used for the bot, contains a board and it's rating by the bot.
	 * 
	 */
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

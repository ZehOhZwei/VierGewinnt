public interface Bot {
	
	/**
	 * @param board
	 * @return column for next move
	 */
	public PlayingField makeTurn(PlayingField board);
	
}

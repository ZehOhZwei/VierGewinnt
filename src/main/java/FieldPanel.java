import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {

	private int rows;
	private int columns;

	private int[][] stones;

	private Graphics _g;
	private IngameUI ui;

	/**
	 * The constructor for the FieldPanel. Firstly saves some variables, 
	 * then sets the width and height to 100 times their value. 
	 * This serves to give the later methods a good frame of reference
	 * 
	 * @param w the width of the playing field
	 * @param h the height of the playing field
	 * @param ingameUi a reference to the IngameUI that houses this FieldPanel
	 */
	public FieldPanel(int w, int h, IngameUI ingameUi) {
		_g = this.getGraphics();
		columns = w;
		rows = h;
		ui = ingameUi;
		this.setSize(100 * w, 100 * h);
	}

	/**
	 * A method that calls the super.repaint method. 
	 * This method is called every time a stone is dropped into the playing field.
	 */
	public void update() {
		super.repaint();
	}

	/**
	 * A method that calls the super.repaint method 
	 * as well as updating the width and height of the playing field
	 * THis method is called every time the playing field is turned. 
	 * 
	 * @param newColumns
	 * @param newRows
	 */
	public void rotate(int newColumns, int newRows) {
		rows = newRows;
		columns = newColumns;
		super.repaint();

	}

	/**
	 * The overriden PaintComponent method of this JPanel. 
	 * This creates the playing field grid and 
	 * uses the GetCell method of the GameAPI to display every stone that has been dropped so far using a for loop.
	 * This is done by calculating the middle point of each cell in the grid. 
	 * Since the grid can have different sizes this has to be calculated every time
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.setPaintMode();
		if(columns == 10)
		{
			this.setSize(700, 700);
		}
		else 
		{
			this.setSize(100 * columns, 100 * rows);
		}
		g.drawRect(0, 0, this.getWidth(), this.getHeight());

		for (int i = 0; i <= columns - 1; i++) {
			g.drawLine(((this.getWidth() / columns)) * i, 0, (this.getWidth() / columns) * i, this.getHeight());
		}
		for (int i = 0; i <= rows - 1; i++) {
			g.drawLine(0, (this.getHeight() / rows) * i, this.getWidth(), (this.getHeight() / rows) * i);
		}

		for (int i = 0; i <= columns - 1; i++) {
			for (int j = 0; j <= rows - 1; j++) {
				switch (ui._gameapi._board.getCell(j, i)) {
				case 1:
					drawCircle((this.getWidth() / (columns * 2)) + this.getWidth() / columns * i,
							this.getHeight() - (this.getHeight() / (rows * 2)) - this.getHeight() / rows * j, Color.red,
							g);
					break;
				case 2:
					drawCircle((this.getWidth() / (columns * 2)) + this.getWidth() / columns * i,
							this.getHeight() - (this.getHeight() / (rows * 2)) - this.getHeight() / rows * j,
							Color.blue, g);
					break;
				default:

				}
			}

		}
	}

	private void drawCircle(int x, int y, Color c, Graphics g) {
		g.setColor(c);

		g.fillOval(x - 20, y - 20, 40, 40);
	}

}

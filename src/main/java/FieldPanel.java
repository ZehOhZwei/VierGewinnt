import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {

	
	private int rows;
	private int columns;
	
	private int[][] stones;
	
	private Graphics _g;
	private IngameUI ui;

	public FieldPanel(int w, int h, IngameUI ingameUi) {
		_g = this.getGraphics();
		this.setSize(100 * w, 100 * h);
		columns = w;
		rows = h;
		ui = ingameUi;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.setPaintMode();
		this.setSize(100 * columns, 100 * rows);

		for (int i = 0; i <= columns - 1; i++) {
			g.drawLine(((this.getWidth() /columns)) * i, 0,(this.getWidth()/columns) * i , this.getHeight());
		}
		for (int i = 0; i <= rows - 1; i++) {
			g.drawLine(0, (this.getHeight()/rows) * i, this.getWidth(),(this.getHeight()/rows) * i );
		}
		

		for (int i = 0; i <= columns - 1; i++) {
			for (int j = 0; j <= rows - 1; j++) {
				switch (ui._gameapi._board[i][j]) {
				case 1:
					drawCircle((this.getWidth()/(columns * 2)) + this.getWidth() / columns * i,
							this.getHeight() - (this.getHeight()/(rows * 2)) + this.getHeight() / rows * j, 
							Color.blue, g);
				case 2:
					drawCircle((this.getWidth()/(columns * 2)) + this.getWidth() / columns * i,
							this.getHeight() - (this.getHeight()/(rows * 2)) + this.getHeight() / rows * j, 
							Color.red, g);
				}
			}
		
		}
	}
	
	
	public void dropStone(int column, int row, int turn) {
		super.repaint();
	}
	
	private void drawCircle(int x, int y, Color c, Graphics g) {
		g.setColor(c);
		
		g.fillOval(x-20, y-20, 40, 40);
	}

}
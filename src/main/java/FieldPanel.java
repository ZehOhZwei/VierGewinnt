import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FieldPanel extends JPanel {

	
	private int rows;
	private int columns;
	
	private int[][] steine;

	public FieldPanel(int w, int h) {
		columns = w;
		rows = h;
		steine = new int[w][h];
		for (int i = 0; i <= w; i++) {
			for (int j = 0; i <= h; i++) {
				steine[i][j] = 0;
			}
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.setPaintMode();
		for (int i = 0; i <= columns - 1; i++) {
			g.drawLine((this.getWidth()/columns) * i, 0,(this.getWidth()/columns) * i , this.getHeight());
		}
		for (int i = 0; i <= rows - 1; i++) {
			g.drawLine(0, (this.getHeight()/rows) * i, this.getWidth(),(this.getHeight()/rows) * i );
		}
		

		for (int i = 0; i <= columns; i++) {
			for (int j = 0; i <= rows; i++) {
				switch (steine[i][j]) {
				case 1:
					drawCircle((this.getWidth()/(columns * 2))* i,(this.getHeight()/(rows * 2))* i , Color.red, g);
					System.out.println("drawing");
				case 2:
					drawCircle((this.getWidth()/(columns * 2))* i,(this.getHeight()/(rows * 2))* i , Color.blue, g);
				}
			}
		
		}
	}
	public void dropStone(int column, int row, int turn) {
		steine[column][row] = turn;
		
	}
	
	private void drawCircle(int x, int y, Color c, Graphics g) {
		g.setColor(c);
		
		g.drawOval(x-10, y-10, x+10, y+10);
	}
}

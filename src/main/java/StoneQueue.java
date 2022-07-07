import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class StoneQueue {
	
	private List<Stein> steine = new ArrayList<Stein>();
	
	public void add(Stein stein) {
		steine.add(stein);
	}
	
	public void workOff(Graphics g) {
		for(int i = 0; i <= steine.size(); i++) {
			steine.get(i).draw(g);
		}
	}
	
	public interface Stein{
		void draw(Graphics g);
	}
	
	public class RoterStein implements Stein{
		int _x;
		int _y;
		
		public RoterStein(int x, int y) {
			_x = x;
			_y = y;
		}
		public void draw(Graphics g) {
			g.setColor(Color.red);
			g.drawOval(_x,  _y, 30, 30);
		}
	}
	
	public class BlauerStein implements Stein{
		int _x;
		int _y;
		
		public BlauerStein(int x, int y) {
			_x = x;
			_y = y;
		}
		public void draw(Graphics g) {
			g.setColor(Color.blue);
			g.drawOval(_x,  _y, 30, 30);
		}
	}
}

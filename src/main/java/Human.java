import java.io.IOException;

public class Human implements Player{

	@Override
	public void takeTurn() {
		int column;
		try {
			column = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

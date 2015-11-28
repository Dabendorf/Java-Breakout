package breakoutpack;

import java.util.ArrayList;

/**
 * Diese Klasse repraesentiert die gesamte Mauer, die aus einzelnen Bricks gebildet wird.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Wall {
	
	/**Diese Liste umfasst alle Einzelsteine der Mauer.*/
	private ArrayList<Brick> bricks = new ArrayList<Brick>();
	/**Diese Zahl zeigt die Restmenge an Mauern, die noch nicht zerstoert wurden.*/
	private int numOfWalls;
	
	public Wall(int columns, int rows) {
		numOfWalls = columns*rows;
		for(int a=0;a<columns;a++) {
			for(int b=0;b<rows;b++) {
				bricks.add(new Brick(1+10*a,2+4*b,9,3));
			}
		}
	}
	
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	
	/**
	 * Diese Methode zeigt, dass ein Loch in die Mauer geschlagen wurde und setzt den Steinzaehler um eins herunter.
	 */
	public void holeInWall() {
		numOfWalls--;
	}
	
	/**
	 * Diese Methode prueft, ob noch ein Stueck Mauer existiert oder die gesamte Mauer zerstoert wird.
	 * @return Gibt zurueck, ob die Mauer noch Steine hat.
	 */
	public boolean wallExist() {
		if(numOfWalls==0) {
			return false;
		} else {
			return true;
		}
	}
}
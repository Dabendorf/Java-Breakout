package breakoutpack;

/**
 * Diese Klasse repraesentiert einen einzelnen "Brick" der Gesamtmauer.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Brick {
	
	/**Dies ist die x-Position des Bricks.*/
	private int posx;
	/**Dies ist die y-Position des Bricks.*/
	private int posy;
	/**Dies ist die Breite des Bricks.*/
	private int width;
	/**Dies ist die Hoehe des Bricks.*/
	private int height;
	/**Dies ist Anzahl von Restleben des Bricks. Er hat anfangs drei und*/
	private int health;
	
	public Brick(int posx, int posy, int widht, int height) {
		this.posx = posx;
		this.posy = posy;
		this.width = widht;
		this.height = height;
		this.health = 3;
	}
	
	public int getPosX() {
		return posx;
	}
	
	public int getPosY() {
		return posy;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * Diese Methode reagiert auf eine Beruehrung und mindert die Anzahl der Leben um eins.
	 */
	public void hit() {
		health--;
	}
	
	public int getHealth() {
		return health;
	}
}
package breakoutpack;

/**
 * Diese Klasse repraesentiert das Paddle des Spiels, womit der Spieler den Ball zurueckschlagen muss.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Paddle {
	
	/**Dies ist die x-Position des Paddles.*/
	private int posx;
	/**Dies ist die y-Position des Paddles.*/
	private int posy;
	/**Dies ist die Breite des Paddles.*/
	private int width;
	/**Dies ist die Hoehe des Paddles.*/
	private int height;
	
	public Paddle(int posx, int posy, int widht, int height) {
		this.posx = posx;
		this.posy = posy;
		this.width = widht;
		this.height = height;
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
	 * Diese Methode bewegt den Schlaeger um einen Wert length nach rechts (negativer Wert bedeutet nach links).
	 * @param length Die Weite, die der Schlaeger bewegt wird.
	 */
	public void move(int length) {
		posx += length;
	}
	
	/**
	 * Diese Methode setzt die Position des Schlaegers neu.
	 * @param posx Nimmt die neue x-Position entgegen.
	 * @param posy Nimmt die neue y-Position entgegen.
	 */
	public void setPos(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
}
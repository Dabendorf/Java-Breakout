package breakoutpack;

import java.util.Random;

/**
 * Diese Klasse repraesentiert den Ball mitsamt seiner Eigenschaften.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Ball {
	
	/**Dies ist die x-Position des Balls.*/
	private int posx;
	/**Dies ist die y-Position des Balls.*/
	private int posy;
	/**Dies ist der Radius des Balls.*/
	private int rad = 1;
	/**Dies ist die x-Flugrichtung des Balls.*/
	private int dirX;
	/**Dies ist die y-Flugrichtung des Balls.*/
	private int dirY;
	/**Dies ist die Geschwindigkeit des Balls.*/
	private int speed = 100;
	
	public void setPos(int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
	}
	
	public int getPosX() {
		return posx;
	}
	
	public int getPosY() {
		return posy;
	}
	
	public int getRad() {
		return rad;
	}

	public int getDirX() {
		return dirX;
	}

	public int getDirY() {
		return dirY;
	}
	
	/**
	 * Diese Methode lost aus, ob ein Ball nach Links oder nach Rechts fliegt.
	 */
	public void startDirection() {
		Random coin = new Random();
		boolean boolX = coin.nextBoolean();
		if(boolX==true) {
			dirX = 1;
		} else {
			dirX = -1;
		}
		dirY = -1;
	}
	
	/**
	 * Diese Methode wechselt die x-Richtung des Balls.
	 */
	public void changeDirX() {
		dirX *= -1;
	}
	
	/**
	 * Diese Methode wechselt die y-Richtung des Balls.
	 */
	public void changeDirY() {
		dirY *= -1;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
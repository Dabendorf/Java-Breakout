package breakoutpack;

import java.util.ArrayList;

/**
 * Diese Klasse repraesentiert das Balldepot mit seinen x Baellen.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class BallDepot {
	
	/**Die Liste an benutzbaren Baellen.*/
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	public BallDepot(int numOfBalls) {
		for(int i=0;i<numOfBalls;i++) {
			balls.add(new Ball());
		}
	}
	
	/**
	 * Diese Methode gibt zurueck, ob der Ballvorrat leer ist.
	 * @return Gibt true zurueck, wenn keine Baelle mehr da sind.
	 */
	public boolean isEmpty() {
		if(balls.size()==0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Diese Methode gibt einen Ball zurueck und entfernt ihn aus dem Balldepot.
	 * @return Gibt den naechsten Ball aus.
	 */
	public Ball getNewBall() {
		return balls.remove(0);
	}
	
	/**
	 * Diese Methode gibt die Restzahl an Baellen aus.
	 * @return Gibt einen Integer wieder, wie viele Baelle uebrig sind.
	 */
	public int restNumber() {
		return balls.size();
	}
}
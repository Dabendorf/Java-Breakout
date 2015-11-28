package de.hpi.javaide.breakout.elements;

import java.awt.Point;
import java.util.ArrayList;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.Measureable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * Diese Klasse repraesentiert ein BallDepot voller Baelle.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class BallDepot implements Displayable, Measureable {

	/**Die Liste von Baellen*/
	private ArrayList<Ball> balls;
	/**Die x-Position, wo das Depot angezeigt wird*/
	private int depotX;
	/**Die y-Position, wo das Depot angezeigt wird*/
	private int depotY;
	/**Die Groesse des Depots fuer die graphische Anzeige*/
	private int offset = 50;
	
	public BallDepot(Game game) {
		balls = new ArrayList<>();
		depotX = game.width - this.getWidth() - 50 * GameConstants.LIVES + 10;
		depotY = game.height - this.getHeight() - 20;
		
		for(int i=0; i<GameConstants.LIVES; i++) {
			balls.add(new Ball(game, new Point(depotX + (i * offset), depotY)));
			balls.get(i).display();
		}
	}

	/**
	 * Methode getX() ist leer und wird nicht benoetigt.
	 */
	@Override
	public int getX() {
		return 0;
	}

	/**
	 * Methode getY() ist leer und wird nicht benoetigt.
	 */
	@Override
	public int getY() {
		return 0;
	}

	/**
	 * Methode getWidth() ist leer und wird nicht benoetigt.
	 */
	@Override
	public int getWidth() {
		return 0;
	}

	/**
	 * Methode getHeight() ist leer und wird nicht benoetigt.
	 */
	@Override
	public int getHeight() {
		return 0;
	}

	/**
	 * Diese Methode zeigt alle noch verfuegbaren Baelle an.
	 */
	@Override
	public void display() {
		for(int i=0; i<balls.size(); i++) {
			balls.get(i).display();
		}
	}

	/**
	 * Ueberprueft, ob noch Baelle verfuegbar sind oder das Lager leer ist.
	 * @return Gibt Boolean, ob das Lager leer ist zurueck.
	 */
	public boolean isEmpty() {
		return balls.isEmpty();
	}

	/**
	 * Gibt einen weiteren Ball aus.
	 * @return Gibt einen Ball zurueck.
	 */
	public Ball dispense() {
		if(balls.size() > 0) {
			Ball currentBall = balls.remove(0);
			currentBall.moveToStart();
			return currentBall;
		} else {
			return null;
		}
	}
}
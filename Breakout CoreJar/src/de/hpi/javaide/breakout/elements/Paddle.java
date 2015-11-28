package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

/**
 * Diese Klasse repraesentiert das Paddle, mit welchem der Spieler den Ball zurueckschlagen muss.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Paddle extends Rectangular {
	
	public Paddle(Game game) {
		super(game, new Point(GameConstants.SCREEN_X / 2, GameConstants.SCREEN_Y - 100), new Dimension(100, 20));
		setColor(150, 150, 150);
	}

	/**
	 * Diese Methode zeigt das Paddle mit Farbe und Text an.
	 */
	@Override
	public void display() {
		game.rectMode(PApplet.CENTER);
		game.noStroke();
		game.fill(getR(), getG(), getB());
		game.rect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Diese Methode bewegt das Paddle an eine andere Stelle.
	 */
	public void move() {
		update(new Point(game.mouseX, getY()), new Dimension(getWidth(), getHeight()));
	}
	
	/**
	 * Diese Methode bewegt das Paddle nach links.
	 */
	public void moveKeysLeft() {
		update(new Point(getX()-GameConstants.KEYSPEED, getY()), new Dimension(getWidth(), getHeight()));
		if(getX() < getWidth()/2) {
			update(new Point(getWidth()/2, getY()), new Dimension(getWidth(), getHeight()));
		}
	}
	
	/**
	 * Diese Methode bewegt das Paddle nach rechts.
	 */
	public void moveKeysRight() {
		update(new Point(getX()+GameConstants.KEYSPEED, getY()), new Dimension(getWidth(), getHeight()));
		if(getX() > GameConstants.SCREEN_X-getWidth()/2) {
			update(new Point(GameConstants.SCREEN_X-getWidth()/2, getY()), new Dimension(getWidth(), getHeight()));
		}
	}
}
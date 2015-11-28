package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Rectangular;
import de.hpi.javaide.breakout.starter.Game;
import processing.core.PApplet;

/**
 * Diese Klasse repraesentiert einen einzelnen Brick aus einer Mauer.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Brick extends Rectangular {

	/**Gibt den Zerstoerungsstatus des Steins wieder*/
	private int status;
	
	public Brick(Game game, Point position, Dimension dimension) {
		super(game, position, dimension);
		setColor(0, 150, 40);
		status = 3;
	}

	/**
	 * Setzt die Position des Bricks fest.
	 * @param position Nimmt die Position des Steins entgegen.
	 */
	public void setPos(Point position) {
		update(position, new Dimension(getWidth(), getHeight()));
	}
	
	/**
	 * Ueberprueft, ob der Stein vollstaendig zerstoert wurde.
	 * @return Gibt true zurueck bei vollstaendiger Zerstoerung.
	 */
	public boolean isDead() {
		return(status == 0);
	}
	
	/**
	 * Diese Methode zeigt den Brick mit Farbe und Text an.
	 */
	@Override
	public void display() {
		game.rectMode(PApplet.CENTER);
		game.strokeWeight(4);
		game.fill(getR(), getG(), getB());
		game.noStroke();
		game.rect(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Diese Methode setzt den Zerstoerungsgrad des Steins um eins herauf.
	 */
	public void nextStatus() {
		if(!isDead()) {
			status--;
			setColor(0, status*50, status*15);
		}
	}
	
	/**
	 * Diese Methode gibt den Zerstoerungsgrad des Steins zurueck.
	 * @return Gibt den Zerstoerungsgrad des Steins zurueck.
	 */
	public int getStatus() {
		return status;
	}
}
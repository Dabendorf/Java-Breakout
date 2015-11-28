package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;

import de.hpi.javaide.breakout.basics.Elliptic;
import de.hpi.javaide.breakout.basics.Vector;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;
import processing.core.PApplet;

/**
 * Diese Klasse repraesentiert einen Spielball, der die Mauern zerschlaegt.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Ball extends Elliptic {
	
	/**Radius des Balls*/
	private int fullSize;
	/**Richtung des Balls*/
	private Vector direction;
	
	public Ball(Game game, Point position) {
		super(game, position, new Dimension(20, 20));
		direction = GameConstants.BALLDIRECTION;
		fullSize = 30;
		setColor(150, 0, 77);
	}

	/**
	 * Diese Methode zeigt den Ball mit Farbe und Text an.
	 */
	@Override
	public void display() {
		game.ellipseMode(PApplet.CENTER);
		game.strokeWeight(4);
		game.fill(getR(), getG(), getB());
		game.noStroke();
		game.ellipse(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Diese Methode fuegt Luft hinzu.
	 */
	public void addAir() {
		update(new Point(getX(), getY()), new Dimension(fullSize, fullSize));
	}
	
	/**
	 * Diese Methode bewegt den Ball an eine andere Position.
	 */
	public void move() {
		update(new Point(Math.round(getX()+direction.getX()), Math.round(getY()+direction.getY())), new Dimension(fullSize, fullSize));
	}
	
	/**
	 * Diese Methode setzt die Position des Balls fest.
	 * @param position Der Punkt, wo der Ball positioniert ist.
	 */
	public void setPos(Point position) {
		update(position, new Dimension(fullSize, fullSize));
	}

	/**
	 * Diese Methode bewegt den Ball zur Startposition.
	 */
	public void moveToStart() {
		Point startPosition = GameConstants.STARTPOSITION;
		Dimension newSize = new Dimension(fullSize, fullSize);
		update(startPosition, newSize);
	}

	/**
	 * Diese Methode ueberprueft, ob der Ball nach oben geschossen wird.
	 * @return Gibt einen boolean zurueck.
	 */
	public boolean isMovingUpwards() {
		return direction.getY() < 0;
	}

	/**
	 * Diese Methode wechselt die x-Richtung des Balls.
	 */
	public void bounceX() {
		direction.setX(direction.getX()*(-1));
	}
	
	/**
	 * Diese Methode wechselt die x-Richtung des Balls.
	 */
	public void bounceY() {
		direction.setY(direction.getY()*(-1));
	}
}
package de.hpi.javaide.breakout.starter;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

import de.hpi.javaide.breakout.basics.Vector;

/**
 * Dieses Interface enthaelt alle wichtigen, verstellbaren Gamekonstanten des Breakout-Spiels.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public interface GameConstants {
	
	/**Anzahl der verfuegbaren Baelle*/
	int LIVES = 3;
	/**Breite des Bildschirms*/
	int SCREEN_X = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	/**Hoehe des Bildschirms*/
	int SCREEN_Y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**Position auf welcher ein neuer Ball ins Spiel startet*/
	Point STARTPOSITION = new Point(SCREEN_X/2+(new Random().nextInt(300)-150), SCREEN_Y/2+(new Random().nextInt(300)-150));
	/**Geschwindigkeit des Paddles pro Pfeiltastenbewegung*/
	int KEYSPEED = 25;
	/**Groesse der Mauer in Steinen (x*y)*/
	Dimension WALLDIMENSION = new Dimension(10, 3);
	/**Hoehe eines einzelnen Steins*/
	int BRICKHEIGHT = 50;
	/**Abstand zwischen zwei Mauersteinen*/
	int BRICKMARGIN = 10;
	/**Die Richtung und Geschwindigkeit des Balls*/
	Vector BALLDIRECTION = new Vector(4, -8);
	/**Die Zeit, mit der das Spiel startet*/
	int TIME = 0;
}
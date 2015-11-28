package de.hpi.javaide.breakout.starter;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.screens.Screen;
import de.hpi.javaide.breakout.screens.ScreenManager;
import processing.core.PApplet;

/**
 * Diese Klasse steuert wichtige graphische Grundprinzipien des Spiels selbst.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Game extends PApplet implements GameConstants {
	
	/**
	 * Diese Methode laedt das Spiel mit graphischer Oberflaeche.
	 */
	@Override
	public void setup() {
		size(SCREEN_X, SCREEN_Y);
		background(0);
		frameRate(30);
		Font.init(this);
		ScreenManager.setScreen(this, Screen.START);
	}

	/**
	 * Diese Methode laedt den aktuellen Spielbildschirm.
	 */
	@Override
	public void draw() {
		background(0);
		ScreenManager.getCurrentScreen().update();
		ScreenManager.getCurrentScreen().display();
	}

	/**
	 * Diese Methode reagiert darauf, was bei Mausbewegungen passiert.
	 */
	@Override
	public void mouseDragged() {
		ScreenManager.getCurrentScreen().handleMouseDragged();
	}

	/**
	 * Diese Methode dient dazu die Anweisungen von der Tastatur zu bearbeiten.
	 */
	@Override
	public void keyPressed() {
		switch(key) {
		case RETURN:
		case ENTER:
			ScreenManager.getCurrentScreen().handleKeyPressed(Screen.KEY_ENTER);
			break;
		case CODED:
			if(keyCode == LEFT) {
				ScreenManager.getCurrentScreen().handleKeyPressed(Screen.KEY_LEFT);
			}
			if(keyCode == RIGHT) {
				ScreenManager.getCurrentScreen().handleKeyPressed(Screen.KEY_RIGHT);
			}
		default: break;
		}
	}

	/**
	 * Diese Methode erhoeht den Spielscore um i Punkte.
	 * @param i Anzahl der Punkte, um die der Score steigt.
	 */
	public void increaseScore(int i) {
		ScreenManager.getCurrentScreen().increaseScore(i);
	}
}
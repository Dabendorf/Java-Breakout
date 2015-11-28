package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * Diese Klasse repraesentiert den Timer, der die Sekunden seit Beginn des Spiels mitzaehlt.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Timer extends UIObject {

	/**Die Anzahl vergangener Sekunden*/
	private int seconds;

	public Timer(Game game) {
		super(game);
		seconds = GameConstants.TIME;
	}

	/**
	 * Diese Methode zeigt den Timer mit Farbe und Text an.
	 */
	@Override
	public void display() {
		game.fill(255);
		game.textFont(Font.getFont16());
		game.text("Time: " + seconds, game.width-130, game.height-70);
	}

	/**
	 * Diese Methode aktualisiert den Timer-Stand.
	 */
	@Override
	public void update(String input) {
		seconds = Integer.parseInt(input);
	}

	/**
	 * Gibt die Anzahl an Sekunden des Timers zurueck.
	 * @return Gibt die Sekundenzahl zurueck.
	 */
	public int getSeconds() {
		return seconds;
	}
}
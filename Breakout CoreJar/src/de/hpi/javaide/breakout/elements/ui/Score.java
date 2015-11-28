package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Diese Klasse repraesentiert den Score, der die Punkte des Spielers mitzaehlt.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Score extends UIObject {

	/**Die Anzahl erreichter Punkte*/
	private String score;
	
	public Score(Game game) {
		super(game);
		score = "0";
	}

	/**
	 * Diese Methode zeigt den Score mit Farbe und Text an.
	 */
	@Override
	public void display() {
		game.fill(255);
		game.textFont(Font.getFont16());
		game.text("Score: " + score, game.width-130, game.height-50);
	}

	/**
	 * Diese Methode aktualisiert den Spielstand.
	 */
	@Override
	public void update(String input) {
		score = input;
	}
}
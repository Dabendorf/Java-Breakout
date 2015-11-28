package de.hpi.javaide.breakout.elements.ui;

import de.hpi.javaide.breakout.basics.Font;
import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Diese Klasse repraesentiert einen Informationsbildschirm, der am Anfang und Ende des Spiels angezeigt wird.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Info extends UIObject {

	/**Der Text, der auf dem Bildschirm angezeigt wird*/
	private String content;
	
	public Info(Game game, String content) {
		super(game);
		this.content = content;
	}

	/**
	 * Diese Methode zeigt den Infobildschirm mit Farbe und Text an.
	 */
	@Override
	public void display() {
		game.fill(255);
	    game.textFont(Font.getFont32());
	    game.text(content, 100, 100);
	}

	/**
	 * Diese Methode aktualisiert den Infobildschirm.
	 */
	@Override
	public void update(String input) {
		content = input;
	}
}
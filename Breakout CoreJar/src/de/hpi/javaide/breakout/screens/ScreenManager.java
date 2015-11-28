package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.starter.Game;

/**
 * Diese Klasse verwaltet alle Bildschirmanzeigen in einer Klasse.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class ScreenManager {

	/**Der aktuell angezeigte Screen*/
	private static Screen currentScreen;

	/**
	 * Legt fest, in welchen Screen gewechselt werden muss.
	 * @param game Uebergibt das aktuelle Spiel.
	 * @param type Sagt, um welchen ScreenTyp es sich handelt.
	 */
	public static void setScreen(Game game, String type) {
		switch(type) {
		case Screen.START:
			currentScreen = StartScreen.getInstance(game);
			break;
		case Screen.GAME:
			currentScreen = GameScreen.getInstance(game);
			break;
		case Screen.END:
			currentScreen = EndScreen.getInstance(game);
			break;
		}
	}

	public static Screen getCurrentScreen() {
		return currentScreen;
	}
}
package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.basics.UIObject;
import de.hpi.javaide.breakout.elements.ui.Info;
import de.hpi.javaide.breakout.starter.Game;

/**
 * Diese Klasse repraesentiert den EndScreen, der bei Spielende angezeigt wird.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class EndScreen implements Screen {

	/**Eine Instance des Spielbildschirms*/
	private static Screen instance;
	/**Das Spiel selbst*/
	private Game game;
	/**Die Informationsanzeige fuer Spielerinfos*/
	private UIObject infoBox;

	private EndScreen(Game game) {
		this.game = game;
		init();
	}

	/**
	 * EndScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four) 
	 * This approach is not "Thread safe", but is sufficient for our current needs.
	 * 
	 * Please, be aware that Singletons need to be handled with care.
	 * There are various ways to implement them, all have there pros and cons.
	 * In his book, Effective Java, Joshua Bloch recommends to create Singletons using an enum, 
	 * which is a language concept that we have not discussed here so far.
	 * For those of you who want to go further we suggest to follow this recommendation at some point of time. 
	 * 
	 * @return the EndScreen
	 */
	public static Screen getInstance(Game game) {
		if (instance == null) {
			instance = new EndScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}
	
	/**
	 * Initialisiert den EndScreen mit Anweisungen fuer den Spieler.
	 */
	@Override
	public void init() {
		game.background(0);
		String info = "Game over!"+System.getProperty("line.separator")+"Press Enter to restart!";
		infoBox = new Info(game, info);
		infoBox.display();
	}

	/**
	 * Diese Methode aktualisiert den EndScreen.
	 * Sie ist vollstaendig leer und wird nicht benoetigt.
	 */
	@Override
	public void update() {
	}

	/**
	 * Diese Methode laedt den EndScreen.
	 */
	@Override
	public void display() {
		infoBox.display();
	}

	/**
	 * Diese Methode reagiert auf den Druck von Enter und leitet fuer ein neues Spiel in den StartScreen weiter.
	 */
	@Override
	public void handleKeyPressed(String key) {
		switch(key) {
		case Screen.KEY_ENTER:
			ScreenManager.setScreen(game, Screen.START);
			break;
		default: break;
		}
	}

	/**
	 * Diese Methode regelt die Interaktion der Maus.
	 * Sie ist im EndScreen nicht noetig.
	 */
	@Override
	public void handleMouseDragged() {
	}

	/**
	 * Diese Methode erhoeht den Spielscore um i Punkte.
	 * Sie wird im EndScreen nicht benoetigt und ist entsprechend leer.
	 * @param i Anzahl der Punkte, um die der Score steigt.
	 */
	@Override
	public void increaseScore(int i) {
	}
}
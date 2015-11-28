package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.elements.Ball;
import de.hpi.javaide.breakout.elements.BallDepot;
import de.hpi.javaide.breakout.elements.CollisionLogic;
import de.hpi.javaide.breakout.elements.Paddle;
import de.hpi.javaide.breakout.elements.Wall;
import de.hpi.javaide.breakout.elements.ui.Score;
import de.hpi.javaide.breakout.elements.ui.Timer;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * Diese Klasse repraesentiert die Anzeige des Spielbildschirms.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class GameScreen implements Screen {

	/**Eine Instance des GameScreens*/
	private static Screen instance;

	/**Das Balldepot*/
	private BallDepot ballDepot;
	/**Der aktuell im Spiel befindliche Ball*/
	private Ball currentBall;

	/**Das Paddle zur spielerischen Interaktion*/
	private Paddle paddle;
	/**Die zu zerstoerende Mauer*/
	private Wall wall;

	/**Der aktuelle Score des Spielers*/
	private Score score;
	/**Die bisher vergangene Zeit*/
	private Timer timer;
	
	/**Die Punkte des Spielers*/
	private int points;
	/**Die UnixZeit, zu der das Spiel begonnen hat*/
	private long startTime;
	/**Die bisher vergangene Zeit*/
	private long timePast;

	/**Die Instance des aktuellen Spiels*/
	private Game game;

	/**Ein boolean, ob das Spiel bereits gestartet wurde*/
	private boolean timeStarted;

	private GameScreen(Game game) {
		this.game = game;
		init();
	}

	/**
	 * GameScreen implements a "Lazy Instantiation" of the Singleton Design Patterns (Gang of Four) 
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
		if(instance == null) {
			instance = new GameScreen(game);
		} else {
			instance.init();
		}
		return instance;
	}

	/**
	 * Initialisiert den GameScreen mitsamt seiner wichtigen Eigenschaften.
	 */
	@Override
	public void init() {
		ballDepot = new BallDepot(game);
		paddle = new Paddle(game);
		wall = new Wall(game, GameConstants.WALLDIMENSION.width, GameConstants.WALLDIMENSION.height);
		score = new Score(game);
		timer = new Timer(game);
		game.loop();
		points = 0;
		timePast = GameConstants.TIME;
		timeStarted = false;
	}

	/**
	 * Diese Methode laedt den Zustand des GameScreens neu.
	 */
	@Override
	public void update() {
		if(currentBall != null) {
			currentBall.move();
			CollisionLogic.checkCollision(game, currentBall, paddle, wall);
		}
		if(timeStarted) {
			//TODO Einbauen, dass der Ball schneller wird
			timePast = System.currentTimeMillis()/1000 - startTime + GameConstants.TIME;
			timer.update(String.valueOf(timePast));
		}
	}

	/**
	 * Diese Methode laedt den gesamten Spielbildschirm mit Paddle, Ball, Infoanzeige und Ball.
	 */
	@Override
	public void display() {
		ballDepot.display();
		if(currentBall != null) {
			currentBall.display();
		} else {
			if(ballDepot.isEmpty()) {
				ScreenManager.setScreen(game, Screen.END);
			}
		}
		if(wall.isEmpty()) {
			ScreenManager.setScreen(game, Screen.END);
		}
		paddle.display();
		wall.display();
		score.display();
		timer.display();
	}

	/**
	 * Diese Methode bewegt das Paddle nach Links und Rechts und startet einen neuen Ball bei Klick auf Enter.
	 */
	@Override
	public void handleKeyPressed(String key) {
		if(key == Screen.KEY_LEFT) {
			paddle.moveKeysLeft();
		} else if(key == Screen.KEY_RIGHT) {
			paddle.moveKeysRight();
		}
		
		switch(key) {
		case Screen.KEY_ENTER:
			currentBall = ballDepot.dispense();
			if(!timeStarted) {
				timeStarted = true;
				startTime = System.currentTimeMillis()/1000;
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Bewegt das Paddle.
	 */
	@Override
	public void handleMouseDragged() {
		paddle.move();
	}

	/**
	 * Diese Methode erhoeht den Spielscore um i Punkte.
	 * @param i Anzahl der Punkte, um die der Score steigt.
	 */
	@Override
	public void increaseScore(int i) {
		points += i;
		score.update(String.valueOf(points));
	}
}
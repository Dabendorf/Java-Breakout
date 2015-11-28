package de.hpi.javaide.breakout.elements;

import de.hpi.javaide.breakout.Collidable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * Diese Klasse kontrolliert die Logik nach welcher der Ball an bestimmten Gegenstaenden abprallen muss.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class CollisionLogic {
	
	/**
	 * Diese Methode ueberprueft, ob Kollisionen des Balls mit Spielelementen vorliegen und reagiert darauf.
	 * 
	 * @param game Uebergibt das aktuelle Spiel.
	 * @param ball Uebergibt den aktuellen Ball.
	 * @param paddle Uebergibt das Paddle.
	 * @param wall Uebergibt die Mauer des Spiels.
	 */
	public static void checkCollision(Game game, Ball ball, Paddle paddle, Wall wall) {
		if(collidesWithSideBoundary(game, ball)) {
			ball.bounceX();
		} else if(collidesWithTopBoundary(game, ball)) {
			ball.bounceY();
		} else if(collidesWithPaddle(ball, paddle)) {
			ball.bounceY();
		} else {
			for(Brick b : wall) {
				if(collidesWithBrick(ball, b)) {
					if(!b.isDead()) {
						if(ball.isMovingUpwards()) {
							b.nextStatus();
							game.increaseScore(1);
						}
						ball.bounceY();
					}
				}
			}
		}
	}

	/**
	 * Diese Methode ueberprueft, ob eine Kollision mit einem Stein vorliegt.
	 * 
	 * @param ball Uebergibt den aktuellen Ball.
	 * @param b Uebergibt etwas kollidierbares.
	 * @return Gibt einen Boolean zurueck.
	 */
	private static boolean collidesWithBrick(Ball ball, Collidable b) {
		return ((ball.getX() >= b.getX() - b.getWidth()/2) && (ball.getX() <= b.getX() + b.getWidth()/2) && (ball.getY() - ball.getHeight()/2 <= b.getY() + b.getHeight()/2) && (ball.getY() + ball.getHeight()/2 >= b.getY() - b.getHeight()/2));
	}

	/**
	 * Diese Methode ueberprueft, ob eine Kollision mit dem Paddle vorliegt.
	 * 
	 * @param ball Uebergibt den aktuellen Ball.
	 * @param paddle Uebergibt das Paddle.
	 * @return Gibt einen Boolean zurueck.
	 */
	private static boolean collidesWithPaddle(Ball ball, Collidable paddle) {
		int left = paddle.getX() - paddle.getWidth()/2;
		int right = paddle.getX() + paddle.getWidth()/2;
		int top = paddle.getY() - paddle.getHeight();
		boolean isInPaddleArea = (ball.getX() >= left) && (ball.getX() <= right);
		
		return (isInPaddleArea && (ball.getY() >= top) && (ball.getY() <= top + paddle.getHeight()*2));
	}

	/**
	 * Diese Methode ueberprueft, ob eine Kollision mit der oberen Wand vorliegt.
	 * 
	 * @param game Uebergibt das aktuelle Spiel.
	 * @param ball Uebergibt den aktuellen Ball.
	 * @return Gibt einen Boolean zurueck.
	 */
	private static boolean collidesWithTopBoundary(Game game, Collidable ball) {
		return 0 > (ball.getY() - ball.getHeight() / 2);
	}

	/**
	 * Diese Methode ueberprueft, ob eine Kollision mit den Seitenwaenden vorliegt.
	 * 
	 * @param game Uebergibt das aktuelle Spiel.
	 * @param ball Uebergibt den aktuellen Ball.
	 * @return Gibt einen Boolean zurueck.
	 */
	private static boolean collidesWithSideBoundary(Game game, Collidable ball) {
		return 0 > (ball.getX() - ball.getWidth()/2) || GameConstants.SCREEN_X < (ball.getX() + ball.getWidth()/2);
	}
}
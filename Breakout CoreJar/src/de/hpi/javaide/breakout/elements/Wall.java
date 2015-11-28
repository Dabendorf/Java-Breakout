package de.hpi.javaide.breakout.elements;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.starter.Game;
import de.hpi.javaide.breakout.starter.GameConstants;

/**
 * Diese Klasse repraesentiert die gesamte Mauer mitsamt ihrer Steine.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Wall implements Displayable, Iterable<Brick> {

	/**Die Liste von Steinen in der Mauer*/
	private ArrayList<Brick> wall;

	public Wall(Game game, int i, int j) {
		wall = new ArrayList<>();
		buildWall(game, i, j);
	}
	
	@Override
	public Iterator<Brick> iterator() {
		return wall.iterator();
	}
	
	/**
	 * Build the wall by putting the single bricks into their position.
	 * Hint: You might want to use one or two for-loops.
	 * 
	 * @param game Uebergibt das aktuelle Spiel.
	 * @param columns Die Anzahl von Steinen pro Reihe.
	 * @param rows Die Anzahl an Steinreihen.
	 */
	private void buildWall(Game game, int columns, int rows) {
		int bWidth = ((GameConstants.SCREEN_X-GameConstants.BRICKMARGIN)/columns)-GameConstants.BRICKMARGIN;
		System.out.println(bWidth);
		for(int i=1; i<=rows; i++) {
			for(int j=1; j<=columns; j++) {
				wall.add(new Brick(game, new Point(j*bWidth-bWidth/2+j*GameConstants.BRICKMARGIN, (i-1)*GameConstants.BRICKHEIGHT+i*GameConstants.BRICKMARGIN+GameConstants.BRICKHEIGHT/2), new Dimension(bWidth, GameConstants.BRICKHEIGHT)));
			}
		}
	}
	/**
	 * Diese Methode zeigt die gesamte Mauer mit ihren Steinen an.
	 */
	@Override
	public void display() {
		if(wall.size() > 0) {
			for(int i=0; i<wall.size(); i++) {
				if(!wall.get(i).isDead()) {
					wall.get(i).display();
				}	
			}
		}
	}
	
	/**
	 * Gibt zurueck, ob die Mauer vollstaendig zerstoert wurde.
	 * @return Bei true ist das Spiel gewonnen.
	 */
	public boolean isEmpty() {
		int sum = 0;
		for(int i=0; i<wall.size(); i++) {
			sum += wall.get(i).getStatus();
		}
		return (sum == 0);
	}
}
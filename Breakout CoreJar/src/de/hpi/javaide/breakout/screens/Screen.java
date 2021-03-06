package de.hpi.javaide.breakout.screens;

import de.hpi.javaide.breakout.Displayable;
import de.hpi.javaide.breakout.Initializable;
import de.hpi.javaide.breakout.Updateable;

/**
 * Dieses Interface regelt die Eigenschaften eines Screens. 
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public interface Screen extends Initializable, Displayable, Updateable {
	String START = "start";
	String GAME = "game";
	String END = "end";

	String KEY_ENTER = "Enter key pressed";
	String KEY_SPACE = "Space key pressed";
	String KEY_LEFT = "Left key pressed";
	String KEY_RIGHT = "Right key pressed";

	void handleKeyPressed(String key);

	void handleMouseDragged();

	void increaseScore(int amount);
}
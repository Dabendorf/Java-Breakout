package de.hpi.javaide.breakout.starter;

import processing.core.PApplet;

/**
 * Diese Klasse enthaelt die main-Methode und startet das Breakout-Spiel.
 * 
 * @author Lukas Schramm
 * @author openHPI
 * @version 1.0
 *
 */
public class Main {

	public static void main(String[] args)  {
		PApplet.main(new String[]{"--present", "de.hpi.javaide.breakout.starter.Game"});
	}
}
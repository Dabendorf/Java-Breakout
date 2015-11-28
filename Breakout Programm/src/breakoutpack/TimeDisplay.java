package breakoutpack;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

/**
 * Diese Klasse beherbergt einen Timer, der die bisher abgelaufende Zeit mitschneidet.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class TimeDisplay implements ActionListener {
	
	/**Ein Timer-Element, was in 1Sekunden-Schritten hochzaehlt.*/
	private Timer timer = new Timer(1000, this);
	/**Dies ist die Anzahl an bisher vergangenen Sekunden.*/
	private int t = 0;
		
	public TimeDisplay(){
		timer.start();
	}
	
	public void actionPerformed(ActionEvent evt) {
		t++;
	}
	
	public int getTime() {
		return t;
	}
	
	/**
	 * Diese Methode setzt den Timer zurueck.
	 */
	public void reset() {
		t = 0;
	}
}
package breakoutpack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Diese Klasse beherbergt den Timer der zaehlt, wie viel Zeit seit der letzten Geschwindigkeitserhoehung vergangen ist.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class TimerSpeed implements ActionListener {
	
	/**Ein Timer-Element, was in 1Sekunden-Schritten hochzaehlt.*/
	private Timer timerSpeedRaising = new Timer(1000, this);
	/**Dies ist die Anzahl an bisher vergangenen Sekunden.*/
	private int tSpeedRaising = 0;
		
	public TimerSpeed(){
		timerSpeedRaising.start();
	}
	
	public void actionPerformed(ActionEvent evt) {
		tSpeedRaising++;
	}
	
	public int getTimeSpeedRaising() {
		return tSpeedRaising;
	}
	
	/**
	 * Diese Methode setzt den Timer zurueck.
	 */
	public void resetTimeSpeedRaising() {
		tSpeedRaising = 0;
	}
}
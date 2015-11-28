package breakoutpack;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Dies ist die Hauptklasse des Breakoutprojekts.
 * Sie startet das Spiel, generiert saemtliche Teile der graphischen Oberflaeche
 * und steuert auch den gesamten Spielablauf mit allen Sonderfaellen.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class GameScreen {
	
	/**Dieses Element ist der Spielframe, auf dem alles dargestellt wird.*/
	private JFrame frame = new JFrame("Breakout");
	/**Diese Werte geben die Laenge und Breite des Feldes in Zellen wieder.*/
	private int width=81,height=40;
	/**Dieser 2D-Array beeinhaltet alle Zellen des Spielfelds.*/
	private Cell[][] gamefield = new Cell[width][height];
	/**Hier wird das BallDepot gespeichert.*/
	private BallDepot ballDepot;
	/**Dies ist der aktuell im Spiel befindliche Ball.*/
	private Ball currentBall;
	/**Hier wird das Paddle gespeichert.*/
	private Paddle paddle;
	/**Hier wird die Mauer aus Steinen gespeichert.*/
	private Wall wall;
	/**Dieser Wert gibt an, welcher Spielzustand gerade ist.
	 * Bei 2 ist kein Ball im Spiel, bei 1 ist er gerade gestartet worden
	 * und bei 0 ist das Spiel am Laufen.*/
	private int start = 2;
	/**Dieses JPanel speichert alle Anzeigeelemente zum aktuellen Spielstand ab.*/
	private JPanel informations = new JPanel();
	/**Dieses JLabel gibt die Anzahl von Punkten aus.*/
	private JLabel pointsLabel = new JLabel();
	/**Dieser Integer speichert die Anzahl erspielter Punkte.*/
	private int points = 0;
	/**Dieses JLabel gibt die Anzahl der restlichen Baelle aus.*/
	private JLabel numOfBallsLabel = new JLabel();
	/**Dieses JLabel gibt die abgelaufene Zeit aus.*/
	private JLabel timeLabel = new JLabel();
	/**Dieses Attribut ist der Timer, der die Anzahl vergangener Sekunden zaehlt.*/
	private TimeDisplay td = new TimeDisplay();
	/**Dieses Attribut ist der Timer, der die Anzahl vergangener Sekunden seit der letzten Ball-Geschwindigkeitserhoehung zaehlt.*/
	private TimerSpeed ts = new TimerSpeed();
	/**Dieses JPanel nimmt das gesamte Spielfeld auf.*/
	private JPanel breakoutpanel;
	
	public GameScreen() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Container cp = frame.getContentPane();
		cp.setLayout(new BorderLayout());
		breakoutpanel = new JPanel();
		breakoutpanel.setLayout(new GridLayout(height,width));
		
		informations.setLayout(new GridLayout(1,3));
		informations.add(pointsLabel);
		informations.add(numOfBallsLabel);
		informations.add(timeLabel);
		pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numOfBallsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pointsLabel.setText("Punkte: "+points);
		timeLabel.setText("Zeit: "+td.getTime());
		cp.add(informations,BorderLayout.NORTH);
		cp.add(breakoutpanel,BorderLayout.CENTER);
		
		generateGame();
		
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		/**
		 * Diese Methode ist der KeyListener.
		 * Er ueberprueft, ob die Pfeiltasten betaetigt wurden und verschiebt dementsprechend das Paddle nach links und rechts.
		 */
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(start == 2) {
					start = 1;
					currentBall.startDirection();
					startBall();
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					if(paddle.getPosX()-2>=0) {
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()+paddle.getWidth()-1][paddle.getPosY()+b].setColorNum(0);
							gamefield[paddle.getPosX()+paddle.getWidth()-2][paddle.getPosY()+b].setColorNum(0);
						}
						paddle.move(-2);
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()][paddle.getPosY()+b].setColorNum(2);
							gamefield[paddle.getPosX()+1][paddle.getPosY()+b].setColorNum(2);
						}
					} else if(paddle.getPosX()-1>=0) {
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()+paddle.getWidth()-1][paddle.getPosY()+b].setColorNum(0);
						}
						paddle.move(-1);
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()][paddle.getPosY()+b].setColorNum(2);
						}
					}
				} else if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
					if(paddle.getPosX()+paddle.getWidth()<width-1) {
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()][paddle.getPosY()+b].setColorNum(0);
							gamefield[paddle.getPosX()+1][paddle.getPosY()+b].setColorNum(0);
						}
						paddle.move(2);
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()+paddle.getWidth()-1][paddle.getPosY()+b].setColorNum(2);
							gamefield[paddle.getPosX()+paddle.getWidth()-2][paddle.getPosY()+b].setColorNum(2);
						}
					} else if(paddle.getPosX()+paddle.getWidth()<width) {
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()][paddle.getPosY()+b].setColorNum(0);
						}
						paddle.move(1);
						for(int b=0;b<paddle.getHeight();b++) {
							gamefield[paddle.getPosX()+paddle.getWidth()-1][paddle.getPosY()+b].setColorNum(2);
						}
					}
				}
			} 
		});
	}
	
	/**
	 * Diese Methode generiert ein neues Spiel. Es laedt alle Spielzellen und ruft eine neue Mauer, ein neues Paddle und einen Ball auf.
	 */
	private void generateGame() {
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				gamefield[x][y] = new Cell(0);
				gamefield[x][y].setOpaque(true);
				breakoutpanel.add(gamefield[x][y]);
			}
		}
		paddle = new Paddle((width-1)/2-5, height-3, 11, 2);
		ballDepot = new BallDepot(3);
		showPaddle();
		showBall();
		showWall();
	}
	
	/**
	 * Diese Methode setzt den Ball in Bewegung. Sie startet einen Thread, der alle x Sekunden neu ausgefuehrt wird.
	 * Die x Sekunden entsprechen hier der genannten Ballgeschwindigkeit (currentBall.getSpeed()), die sich nach einer Zeit erhoeht.
	 * In ihr befindlich sind Methoden zur Ueberpruefung, ob der Ball an den Waenden, den Mauersteinen oder dem Paddle abprallt.
	 * Es wird ebenfalls ueberprueft, ob der Ball ins Aus gegangen ist oder alle Steine fertig zerstoert wurden.
	 * Dies alles passiert durch Nutzung zahlreicher if-Bedingungen auf Grundlage der Objekteigenschaften.
	 */
	private void startBall() {
		Thread thread = new Thread(new Runnable() {
	        public void run() {
	            try {
	            	while(start!=2) {        		
	            		SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								timeLabel.setText("Zeit: "+td.getTime());
								if(currentBall.getPosX()+currentBall.getDirX()<0 || currentBall.getPosX()+currentBall.getDirX()>=width) {
									currentBall.changeDirX();
								}
								if(currentBall.getPosY()+currentBall.getDirY()<0) {
									currentBall.changeDirY();
								}
								if(currentBall.getPosY()+currentBall.getDirY()>=paddle.getPosY() && start==0) {
									if(currentBall.getPosX()+currentBall.getDirX() >= paddle.getPosX() && currentBall.getPosX()+currentBall.getDirX() < paddle.getPosX()+paddle.getWidth()) {
										currentBall.changeDirY();
									} else {
										start=2;
										gamefield[currentBall.getPosX()][currentBall.getPosY()].setColorNum(0);
										showBall();
									}
								}
								gamefield[currentBall.getPosX()][currentBall.getPosY()].setColorNum(0);
								currentBall.setPos(currentBall.getPosX()+currentBall.getDirX(), currentBall.getPosY()+currentBall.getDirY());
								gamefield[currentBall.getPosX()][currentBall.getPosY()].setColorNum(1);
								if(start==1) {
									start--;
								}
								try {
									int cellColorNum = gamefield[currentBall.getPosX()+currentBall.getDirX()][currentBall.getPosY()+currentBall.getDirY()].getColorNum();
									if(cellColorNum>=3) {
										int brickPos = gamefield[currentBall.getPosX()+currentBall.getDirX()][currentBall.getPosY()+currentBall.getDirY()].getBrickPos();
										wall.getBricks().get(brickPos).hit();
										if(wall.getBricks().get(brickPos).getHealth()==0) {
											wall.holeInWall();
										}
										if(!wall.wallExist()) {
											start=2;
											gameOver(true);
										}
										if(wall.getBricks().get(brickPos).getHealth()==0) {
											for(int a=0;a<wall.getBricks().get(brickPos).getWidth();a++) {
												for(int b=0;b<wall.getBricks().get(brickPos).getHeight();b++) {
													gamefield[wall.getBricks().get(brickPos).getPosX()+a][wall.getBricks().get(brickPos).getPosY()+b].setColorNum(0);
												}
											}
										} else {
											for(int a=0;a<wall.getBricks().get(brickPos).getWidth();a++) {
												for(int b=0;b<wall.getBricks().get(brickPos).getHeight();b++) {
													gamefield[wall.getBricks().get(brickPos).getPosX()+a][wall.getBricks().get(brickPos).getPosY()+b].setColorNum(2+wall.getBricks().get(brickPos).getHealth());
												}
											}
										}
										currentBall.changeDirY();
										points += 10;
										pointsLabel.setText("Punkte: "+points);
									}
									int ballSpeed = currentBall.getSpeed();
									if(ts.getTimeSpeedRaising()>=15 && ballSpeed>50) {
										ts.resetTimeSpeedRaising();
										currentBall.setSpeed(ballSpeed-10);
									}
								} catch(ArrayIndexOutOfBoundsException e) {
									/**wird gezielt gecatcht, weil aussenstehende Werte bei der Ueberpruefung benoetigt werden*/
								}
							}
						});
		            	Thread.sleep(currentBall.getSpeed());
	            	}
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	    });
		thread.setDaemon(true);
		thread.start();
	}
	
	/**
	 * Diese Methode entfernt das Paddle von seiner aktuellen Stelle und ruft es an der Start-Position in der Mitte auf.
	 */
	private void showPaddle() {
		paddle.setPos((width-1)/2-5, height-3);
		for(int a=0;a<width;a++) {
			for(int b=0;b<paddle.getHeight();b++) {
				gamefield[a][paddle.getPosY()+b].setColorNum(0);
			}
		}
		for(int a=0;a<paddle.getWidth();a++) {
			for(int b=0;b<paddle.getHeight();b++) {
				gamefield[paddle.getPosX()+a][paddle.getPosY()+b].setColorNum(2);
			}
		}
	}
	
	/**
	 * Diese Methode ueberprueft, ob noch neue Baelle im Depot zu finden sind.
	 * Wenn ja, dann wird der alte Ball entfernt und ein neuer Ball hinzugefuegt.
	 * Wenn nein, dann wird die gameOver()-Methode aufgerufen.
	 */
	private void showBall() {
		if(!ballDepot.isEmpty()) {
			currentBall = ballDepot.getNewBall();
			currentBall.setPos((width-1)/2,height-4);
			for(int a=0;a<currentBall.getRad();a++) {
				for(int b=0;b<currentBall.getRad();b++) {
					gamefield[currentBall.getPosX()+a][currentBall.getPosY()+b].setColorNum(1);
				}
			}
			ts.resetTimeSpeedRaising();
			numOfBallsLabel.setText("Bälle: "+(ballDepot.restNumber()+1));
			showPaddle();
		} else {
			gameOver(false);
		}
	}
	
	/**
	 * Diese Methode generiert eine neue Mauer aus Steinen, die es zu treffen gilt.
	 */
	private void showWall() {
		wall = new Wall(8,3);
		int i = -1;
		for(Brick br:wall.getBricks()) {
			i++;
			for(int a=0;a<br.getWidth();a++) {
				for(int b=0;b<br.getHeight();b++) {
					gamefield[br.getPosX()+a][br.getPosY()+b].setColorNum(5);
					gamefield[br.getPosX()+a][br.getPosY()+b].setBrickPos(i);
				}
			}
		}
	}
	
	/**
	 * Diese Methode reagiert auf das Ende des Spiels. Je nach Erfolg oder Misserfolg wird eine entsprechende Meldung angezeigt.
	 * Anschliessend wird der Spieler gefragt, ob er eine neue Partie starten moechte. Wenn ja, dann wird das Spiel resettet und neu gestartet.
	 * Wenn er kein neues Spiel starten moechte, schliesst sich das Programm.
	 * @param win Nimmt einen boolean entgegen, ob das Spiel gewonnen oder verloren worden.
	 */
	private void gameOver(boolean win) {
		if(win) {
			JOptionPane.showMessageDialog(null, "Du hast gewonnen!\nMit einer Punktzahl von "+points+" hast Du alle Steine zerstört!", "Spielende", JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Du hast verloren!\nMit einer Punktzahl von "+points+" hast Du alle Bälle verbraucht!", "Spielende", JOptionPane.PLAIN_MESSAGE);
		}
		int dialogneustart = JOptionPane.showConfirmDialog(null, "Möchtest Du eine neue Runde starten?", "Neue Runde?", JOptionPane.YES_NO_OPTION);
        if(dialogneustart == 0) {
        	JOptionPane.showMessageDialog(null, "Das Erstellen eines neuen Spiels kann einen kleinen Augenblick dauern.\nBitte warte, bis die gesamte Oberfläche geladen ist.", "Warnung", JOptionPane.INFORMATION_MESSAGE);
        	breakoutpanel.removeAll();
        	generateGame();
        	frame.repaint();
        	td.reset();
        	ts.resetTimeSpeedRaising();
        	points = 0;
        	pointsLabel.setText("Punkte: "+points);
    		timeLabel.setText("Zeit: "+td.getTime());
    		paddle = new Paddle((width-1)/2-5, height-3, 11, 2);
        } else {
        	System.exit(0);
        }
	}
	
	public static void main(String[] args) {
		new GameScreen();
	}
}
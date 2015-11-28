package breakoutpack;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Diese Klasse repraesentiert eine Einzelzelle des gesamten Felds, welche der Anzeige des Spiels dient.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Cell extends JPanel {
	
	/**Dieser Integer ist ein Farbwert, nach welcher die Zelle gefaerbt wird.*/
	private int colorNum;
	/**Dieses Attribut ist die Farbe der Zelle.*/
	private Color color;
	/**Dieser Wert sagt, wenn die Zelle Teil eines Bricks ist, welchen Indexwert dieser Brick in der Wall hat.*/
	private int brickPos;

	public Cell(int colorNum) {
		this.colorNum = colorNum;
		calculateColor();
	}
	
	@Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        this.setBackground(color);
    }
	
	/**
	 * Diese Methode legt den Farbwert der Zelle neu fest und malt die Zelle entsprechend neu.
	 * @param colorNum Nimmt den Farb-Integer entgegen.
	 */
	public void setColorNum(int colorNum) {
		this.colorNum = colorNum;
		calculateColor();
		repaint();
	}
	
	/**
	 * Diese Methode filtert je nach Farbwert die zugeordnete Farbe heraus.
	 * Jede Farbe hat eine besondere Bedeutung.
	 */
	private void calculateColor() {
		switch(colorNum) {
        case 0: color= new Color(0x000000);break;/**empty cell*/
        case 1: color= new Color(0xFFFFFF);break;/**ball cell*/
        case 2: color= new Color(0x33B200);break;/**paddle cell*/
        case 3: color= new Color(0xEE3B3B);break;/**wall cell 1*/
        case 4: color= new Color(0xFF7F24);break;/**wall cell 2*/
        case 5: color= new Color(0xFFD39B);break;/**wall cell 3*/
        }
	}
	
	public int getColorNum() {
		return colorNum;
	}
	
	public void setBrickPos(int brickPos) {
		this.brickPos = brickPos;
	}
	
	public int getBrickPos() {
		return brickPos;
	}
}
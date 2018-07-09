
package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.shape.Ellipse;
import javax.swing.ImageIcon;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>
 */
public class Laser extends MovingGameObject {


    public Laser(int xPosicion, int yPosicion, int diametro, Color color) {
        super(xPosicion, yPosicion, 0, 0, color);
    }
    

    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosicion(), this.getYPosicion(), 7, 15);
    }
    

    @Override
    public Rectangle getBounds() {
        Rectangle beamHitbox = new Rectangle(xPos, yPos, 7, 15);
        return beamHitbox;
    }
}

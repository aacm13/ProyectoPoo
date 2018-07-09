/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;


import Controladores.ControlDeTecaldo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 *
 * @author aacm12
 */
public class Nave extends ControlledGameObject{
    ImageIcon ship = new ImageIcon("images/shipSkin.gif");
    ImageIcon bonusEnemy = new ImageIcon("images/bonusEnemySkin.gif");
    ImageIcon lifeCounterShip = new ImageIcon("images/shipSkinSmall.gif");

    // Constructor para objetos
    public Nave(int xPosicion, int yPosicion, Color color, ControlDeTecaldo control) {
        super(xPosicion, yPosicion, color, control);
    }

    // nave con puntos extra
    public void bonusDraw(Graphics g) {

        bonusEnemy.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
    }

    public void vidaDraw(Graphics g) {

        lifeCounterShip.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
    }

    // Nave de usuario
    @Override
    public void dibujar(Graphics g) {
        ship.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());

    }

    @Override
    public Rectangle getBounds() {
        Rectangle shipHitbox = new Rectangle(this.getXPosicion(), this.getYPosicion(), 50, 50);
        return shipHitbox;
    }

    // moviemiento de naves
    @Override
    public void mover() {
        // flecha izquierda
        if (control.getKeyStatus(37)) {
            xPos -= 10;
        }
        // flecha derecha
        if (control.getKeyStatus(39)) {
            xPos += 10;
        }
        
        // se mueve de lado a lado sin parar
        if (xPos > 800) {
            xPos = -50;
        }
        if (xPos < -50) {
            xPos = 800;
        }
    }
}

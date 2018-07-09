/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 *
 * @author aacm12
 */
public class Disparo extends MovingGameObject {

    // My player ship shoots bullets!
    int diametro;
    int yVelocidad;

    // Constructor for bullet
    public Disparo(int xPosicion, int yPosicion, int diametro, Color color) {
        super(xPosicion, yPosicion, 0, 0, color);
        this.diametro = diametro;
    }

    // Gets the diameter of the bullet
    public int getDiametro() {
        return diametro;
    }

    // Used to draw the bullet
    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosicion(), this.getYPosicion(), 7, 15);

    }

    @Override
    public Rectangle getBounds() {
        Rectangle bulletHitbox = new Rectangle(xPos, yPos, 7, 15);
        return bulletHitbox;
    }
}
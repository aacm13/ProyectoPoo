/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author aacm12
 */
public class Enemigo extends MovingGameObject{
    ImageIcon alien1 = new ImageIcon("images/alien1Skin.gif");
    ImageIcon alien2 = new ImageIcon("images/alien2Skin.gif");
    ImageIcon alien3 = new ImageIcon("images/alien3Skin.gif");
    ImageIcon alienBoss = new ImageIcon("images/boss1.gif");
    ImageIcon alienBoss2 = new ImageIcon("images/boss2.gif");
    ImageIcon alienBoss3 = new ImageIcon("images/boss3.gif");
    
    private int typoEnemigo, width, height;

    
    // Constuctor for any enemy
    public Enemigo(int xPosicion, int yPosicion, int xVelocidad, int yVelocidad, int typoEnemigo, Color color, int width, int height) {
        super(xPosicion, yPosicion, xVelocidad, yVelocidad, color);
        this.typoEnemigo = typoEnemigo;
        this.width = width;
        this.height = height;
    }
    
    @Override
    // Draws alien
    public void dibujar(Graphics g) {
        // Varient 1
        if (this.typoEnemigo % 3 == 0) {
            alien1.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
        // Varient 2
        } else if (this.typoEnemigo % 3 == 1 && this.typoEnemigo != 100) {
            alien2.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
        // Varient 3
        } else if (this.typoEnemigo % 3 == 2) {
            alien3.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
        // Boss Enemigo
        } if (this.typoEnemigo == 100)
        {
            if(PanelDeJuego.getBossHealth()>20){
                alienBoss.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
            }
            else if(PanelDeJuego.getBossHealth()>10){
                alienBoss2.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
            }
            else if(PanelDeJuego.getBossHealth()>0){
                alienBoss3.paintIcon(null, g, this.getXPosicion(), this.getYPosicion());
            }
        }
    }

    // Gets the hitbox for normal eneimes
    @Override
    public Rectangle getBounds() {
        Rectangle enemyHitBox = new Rectangle(this.getXPosicion(), this.getYPosicion(), width, height);
        return enemyHitBox;
    }

    // Used to move all enemies
    @Override
    public void mover() {
        xPos += xVel;
    }
}

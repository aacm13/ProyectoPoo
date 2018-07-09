
package Juego;

import java.awt.Color;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>
 */
public abstract class MovingGameObject extends GameObject implements Movible{
    
    int xVel;
    int yVel;
    
    public MovingGameObject(int xPosicion, int yPosicion, int xVeloidad, int yVelocidad, Color color)
    {
        super(xPosicion, yPosicion, color);
        this.xVel = xVeloidad;
        this.yVel = yVelocidad;
    
    }
    
    public int getXVelocidad()
    {
        return xVel;
    }
    public int getYVelocidad()
    {
        return yVel;
    }
    public void setXVelocidad(int xVelocity)
    {
        this.xVel = xVelocity;
    }
    public void setYVelocidad(int yVelocity)
    {
        this.yVel = yVelocity;
    }
    
    @Override
    
    public void mover()
    {
        this.xPos += xVel;
        this.yPos += yVel;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Josué González <00034715@uca.edu.sv>
 */
public class ControlDeTecaldo implements KeyListener {

    //Arreglo de Booleano que nos va servir para verificar el estado de las teclas
    private boolean[] keyStatus;

    public ControlDeTecaldo() {
        keyStatus = new boolean[256];
    }

    public boolean getKeyStatus(int keyCode)
    {
        if(keyCode < 0 || keyCode > 255)
        {
            return false; 
        }
        else
        {
            return keyStatus[keyCode]; 
        }
    }
    
    public void reiniciarControlador()
    {
        keyStatus = new boolean[256]; 
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = false; 
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Roxana
 */
public class GameFrame extends JFrame{
    private PanelDeJuego game;
    
    public GameFrame() throws IOException
    {
        // Título de la ventana 
        super("Spaceship and aliens");
        
        // Asegurarse del que programa exista cuando se aprete el botón cerrar
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        // Creando una instancia de la clase Game y encendiendo el doble buffer para asegurar la animacion suave 
        game = new PanelDeJuego();
        game.setDoubleBuffered(true);
        
        // Agrargando la instancia de inicio al panel del contenido de la ventana para mostrarla
        this.getContentPane().add(game); 
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Iniciar el juego
        game.start();  
    }
    
    public void main() 
    {
         java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GameFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
}

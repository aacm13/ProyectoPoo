/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Juego.GameFrame;
import Juego.PanelDeJuego;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //Libreía para utilizar el texto.
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author josue
 */
public class ventana extends JPanel {
    
    public int WIDTH = 300, widthTF = 150, widthB = 100;
    public int HEIGHT = 400, heightTF = 30, heightB = 40;
    public JButton iniciar, puntaje, tienda, salir;
    public JLabel titulo, user;
    //public JPanel h;
    
    public ventana() throws IOException {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        agregarLabels();
        agregarButones();
        action();
        add(iniciar);
        add(salir);
        add(puntaje);
        add(tienda);
        add(titulo);
        add(user);

    }
    private void agregarLabels() throws FileNotFoundException, IOException{
        titulo = new JLabel("<html><font size = '15'>SPACE INVADERS</font></hmtl>");
        titulo.setBounds(new Rectangle(5, 5, 300, 50));
        
        String d;
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("Cookie.txt"));
            d = br.readLine();
           
        }finally{
            if(br!=null){
                br.close();
            }
        }
        
        user = new JLabel("Usuario:" + d);
        user.setBounds(10, 360, 200, heightB);
        
    }
    private void agregarButones(){
        iniciar = new JButton("iniciar");
        iniciar.setBounds(new Rectangle(100, 80, widthB, heightB));
        
        puntaje = new JButton("puntaje");
        puntaje.setBounds(new Rectangle(100, 130, widthB, heightB));
        
        tienda = new JButton("tienda");
        tienda.setBounds(new Rectangle(100, 180, widthB, heightB));
        
        salir = new JButton("Salir");
        salir.setBounds(new Rectangle(100, 230, widthB, heightB));
    }
    private void action(){
        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    GameFrame play = new GameFrame();
                    play.main();
                } catch (IOException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        puntaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Puntos punt = new Puntos();
                    punt.main();
                } catch (IOException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        tienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    tienda t = new  tienda();
                    t.main();
                } catch (IOException ex) {
                    Logger.getLogger(ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
                
            }
        });
    }
    
    public void main() throws IOException{
        JFrame ventana = new JFrame("Space Invaders");
        ventana.setContentPane(new ventana());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setVisible(true);
    }
    
    
}

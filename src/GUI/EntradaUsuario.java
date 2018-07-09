/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //Libreía para utilizar el texto.
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author aacm12
 */
public class EntradaUsuario extends JPanel {
    
    public int WIDTH = 300, widthTF = 150, widthB = 130;
    public int HEIGHT = 400, heightTF = 30, heightB = 40;
    public JButton nuevoUsuario, login;
    //public JLabel titulo;
    //public JPanel h;
    JFrame EntradaUsuario = new JFrame("Space Invaders");
    
    public EntradaUsuario() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        agregarButones();
        action();
        
        add(login);
        add(nuevoUsuario);
        
        
    }
    
    public void main(){
        EntradaUsuario.setContentPane(new EntradaUsuario());
        EntradaUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        EntradaUsuario.setResizable(false);
        EntradaUsuario.pack();
        EntradaUsuario.setVisible(true);
    }
    
    private void agregarButones(){
        login = new JButton("Login");
        login.setBounds(new Rectangle(80, 130, widthB, heightB));
        
        nuevoUsuario = new JButton("Nuevo Usuario");
        nuevoUsuario.setBounds(new Rectangle(80, 180, widthB, heightB));
    }
    private void action(){
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Login login = new Login();
                login.main();
                EntradaUsuario.setVisible(false);
                EntradaUsuario.dispose();
                
                
            }
        });
        nuevoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                NuevoUsuario nuevo = new NuevoUsuario();
                nuevo.main();
                EntradaUsuario.setVisible(false);
                EntradaUsuario.dispose();
            }
        });
    }
}

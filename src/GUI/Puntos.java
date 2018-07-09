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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import usuario.Dato;

/**
 *
 * @author aacm12
 */
public class Puntos extends JPanel {
    private JLabel [] labels, scores;
    public int WIDTH = 300, widthTF = 150, widthB = 100;
    public int HEIGHT = 400, heightTF = 30, heightB = 40;
    public JLabel titulo, userXscore;
    
    //public JPanel h;
    
    public Puntos() throws IOException {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        agregarLabels();
        agregarButones();
        action();

        add(titulo);
        add(userXscore);
        
        
        
        
    }
    private void agregarLabels() throws FileNotFoundException, IOException{
        titulo = new JLabel("<html><font size = '15'>High Scores</font></hmtl>");
        titulo.setBounds(new Rectangle(5, 5, 300, 50));
        //
        
        String d="";
        BufferedReader br = null;
        Dato dato = new Dato();
        ArrayList<Dato> datos = new ArrayList<>();
        try{
            br = new BufferedReader(new FileReader("Score.txt"));
            while ((d= br.readLine())!= null){
                
                String [] splitting = d.split(",");
                String tUser = splitting[0];
                
                int tHigh = Integer.parseInt(splitting[1]);
                //System.out.println(tHigh);
                datos.add(new Dato(tUser,tHigh));
                
            }
        }finally{
            if(br!=null){
                br.close();
            }
        }
        //
        userXscore = new JLabel("Usuario                                          Score");
        userXscore.setBounds(10, 60, 300, heightB);
        
        
        int num = 10;
        labels = new JLabel[num];
        scores = new JLabel[num];
        //Container container = getContentPane();
        
        for (int i=0; i < num; i++){
            scores[i] = new JLabel(Integer.toString(datos.get(i).getHigh()));
            scores[i].setBounds(225,(40+(i*20)),100,150);
            labels[i] = new JLabel((i+1)+". "+ datos.get(i).getUser());
            labels[i].setBounds(10,(40+(i*20)),100,150);
            add(labels[i]);
            add(scores[i]);
        }
        
        
        
    }
    private void agregarButones(){
        
        
    }
    private void action(){
        
        
    }
    
    public void main() throws IOException{
        JFrame Puntos = new JFrame("Space Invaders");
        Puntos.setContentPane(new Puntos());
        Puntos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Puntos.setResizable(false);
        Puntos.pack();
        Puntos.setVisible(true);
    }
}

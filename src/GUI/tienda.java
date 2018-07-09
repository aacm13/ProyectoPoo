/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //Libreía para utilizar el texto.
import javax.swing.JPanel;
import javax.swing.JTextField;
import usuario.Dato;
import usuario.usuario;


/**
 *
 * @author josue
 */
public class tienda extends JPanel {
    private JLabel [] labels;
    private JButton [] buy;
    public int WIDTH = 500, widthTF = 150, widthB = 100;
    public int HEIGHT = 700, heightTF = 30, heightB = 40;
    public JButton buy1, buy2, buy3, buy4;
    public JLabel titulo, usuario, dinero;
    public int din, precio1 = 2000, precio2 = 5000, precio3 = 10000;
    public JLabel descripcion1,descripcion2,descripcion3,descripcion4;
    private String[] nombres = {"trek150","Normandy150","m150"};
    
    public tienda() throws IOException {
        agregarImagenes();
        agregarButtones();
        agregarLabels();
        accionesBuy();
        add(dinero);
        add(usuario);
        add(titulo);
        add(descripcion1);
        add(descripcion2);
        add(descripcion3);
        setBackground(Color.BLACK);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    public void main() throws IOException {
        JFrame tienda = new JFrame("Space Invaders");
        tienda.setContentPane(new tienda());
        tienda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tienda.setResizable(false);
        tienda.pack();
        tienda.setVisible(true);
    }
    
    private void agregarLabels() throws IOException{
        usuario dat = new usuario();
        //Login log = new Login();
         
        titulo = new JLabel("TIENDA");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Serif", Font.PLAIN, 50));

        titulo.setBounds(new Rectangle(10, 3, 200, heightB));
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
        
        
        String dato = dat.valores(d);
        String [] splitting = dato.split(",");
        String tUser = splitting[0];
        int tMoney = Integer.parseInt(splitting[3]); 
        
        din = tMoney;
        
        usuario = new JLabel("<html>usuario: <font color='red'>"+tUser+"</font></html>");
        usuario.setForeground(Color.WHITE);
        usuario.setFont(new Font("Serif", Font.PLAIN, 20));

        usuario.setBounds(new Rectangle(10, 40, 200, heightB));
        
        dinero = new JLabel("<html>creditos: <font color='red'>"+din+"</font></html>");
        dinero.setForeground(Color.WHITE);
        dinero.setFont(new Font("Serif", Font.PLAIN, 20));

        dinero.setBounds(new Rectangle(350, 40, 200, heightB));
        //----------DESCRIPCIONES DE OBJETOS--------------
        
        //descripcion1.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
        
        descripcion1 = new JLabel("<html><font size='5' color='white'>U.S.S. ENTERPRISE</font><br/><font size='3' color='white'> El USS Enterprise (NCC-1701) es una nave espacial de Clase Constitution botada en el año 2245, y es considerada la nave más emblemática de la Flota Estelar del Siglo XXIII.</font><br /><br /><font size='4' color='white'> Precio: "+precio1+"</font></html>");
        descripcion1.setBounds(163, 110, 190, 150);
        
        descripcion2 = new JLabel("<html><font size='5' color='white'>SSV Normandy</font><br/><font size='3' color='white'> Es una nave de la Armada de la Alianza. Es un prototipo de lo que serán las fragatas de infiltración, las cuales serán referidas como naves 'clase Normandía'</font><br /><br /><font size='4' color='white'> Precio: "+precio2+"</font></html>");
        descripcion2.setBounds(163, 265, 195, 150);
        
        descripcion3 = new JLabel("<html><font size='5' color='white'>Millenium Falcon</font>\n<font size='3' color='white'> Un carguero ligero corelliano YT-1300\n utilizado por los contrabandistas Han Solo y Chewbacca</font><br /><br /><font size='4' color='white'> Precio: "+precio3+"</font></html>");
        descripcion3.setBounds(163, 420, 185, 150);
    }
    
    private void agregarButtones(){
        int num = 3;
        buy = new JButton[num];
        for (int i=0;i<num;i++){
            buy[i] = new JButton("comprar");
            buy[i].setBounds(new Rectangle(400, (215+(i*160)), widthB, heightB));
            
            add(buy[i]);
        }
    }
    
    private void agregarImagenes(){
        int num = 3;
        labels = new JLabel[num];
        //Container container = getContentPane();
        
        for (int i=0; i < num; i++){
            labels[i] = new JLabel();//"images/"+
            labels[i].setIcon(new ImageIcon(getClass().getResource(nombres[i]+".jpg")));
            
            labels[i].setBounds(10,(110+(i*155)),150,150);
            labels[i].setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.red));
            
            add(labels[i]);
        }
    }
    
    private void accionesBuy(){
        buy[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                din = din - precio1;
                dinero.setText("<html>creditos: <font color='red'>"+Integer.toString(din)+"</font></html>");
                
            }
        });
        
        buy[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                din = din - precio2;
                dinero.setText("<html>creditos: <font color='red'>"+Integer.toString(din)+"</font></html>");
                
            }
        });
        
        buy[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                din = din - precio3;
                dinero.setText("<html>creditos: <font color='red'>"+Integer.toString(din)+"</font></html>");
                
            }
        });
        
    }
    
    
}

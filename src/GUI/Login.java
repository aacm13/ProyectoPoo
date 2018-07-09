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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; //Libreía para utilizar el texto.
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import usuario.usuario;

/**
 *
 * @author aacm12
 */
public class Login extends JPanel {
    
    public int WIDTH = 300, widthTF = 150, widthB = 100;
    public int HEIGHT = 400, heightTF = 30, heightB = 30;
    public JButton Ingresar;
    public JLabel lblUsername, lblPassword;
    public JTextField user, pass;
    //public JLabel titulo;
    //public JPanel h;
    
    public Login() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        agregarLabels();
        agregarCampos();
        agregarButtones();
        add(Ingresar);
        add(lblUsername);
        add(lblPassword);
        add(user);
        add(pass);
        
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // textF2.setText(textF1.getText());
                boolean bandera;
                usuario ing = new usuario();
                String mensaje1 = "Ingresando...";
                String mensaje2 = "Contraseña o Usuario Incorrecto";
                try {
                    //System.out.println(user.getText());
                    bandera = ing.login(user.getText(), pass.getText());
                    if(bandera == true){
                        Login log = new Login();
                        log.User(user.getText());
                        ventana ven = new ventana();
                        ven.main();
                    }else{
                        JOptionPane.showMessageDialog(null, mensaje2, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    
                    System.out.println("ERROR, no pudo accesar el text file");
                }
                
            }
        });
    }
    public void User(String user){
        try{
            FileWriter fw = new FileWriter("Cookie.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(user);
            bw.newLine();
            
            bw.close();
            
        }catch(IOException e){
            System.out.println("ERROR, no se pudo agregar a file");
            e.printStackTrace();
        } 
    }
    
    public void main(){
        JFrame Login = new JFrame("Space Invaders");
        Login.setContentPane(new Login());
        Login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Login.setResizable(false);
        Login.pack();
        Login.setVisible(true);
    }
    
    private void agregarLabels() {
        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        lblUsername.setBounds(10, 100, widthTF, heightTF);
        lblPassword.setBounds(10, 130, widthTF, heightTF);
    }
    
    private void agregarCampos(){
        user = new JTextField();
        pass = new JTextField();
        user.setBounds(80, 100, widthTF, heightTF);
        pass.setBounds(80, 130, widthTF, heightTF);
    }
    
    private void agregarButtones(){
        Ingresar = new JButton("Ingresar");
        Ingresar.setBounds(new Rectangle(75, 160, widthB, heightB));
    }
    
}


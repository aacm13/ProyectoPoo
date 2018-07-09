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
public class NuevoUsuario extends JPanel {
    
    public int WIDTH = 300, widthTF = 150, widthB = 100;
    public int HEIGHT = 400, heightTF = 30, heightB = 30;
    public JButton Registrar;
    public JLabel lblUsername, lblPassword, lblConfirmarPass;
    public JTextField user, pass, confirmar;
    //public JLabel titulo;
    //public JPanel h;
    
    public NuevoUsuario() {
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        agregarLabels();
        agregarCampos();
        agregarButtones();
        add(Registrar);
        add(lblUsername);
        add(lblPassword);
        add(lblConfirmarPass);
        add(user);
        add(pass);
        add(confirmar);
        
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                usuario reg = new usuario();
                boolean bandera = false;
                boolean check = false;
                /*
                try {
                    check = reg.checkExiste(user.getText());
                } catch (IOException ex) {
                    System.out.println("Algo salio mal");
                }*/
                  
                if(confirmar.getText().equals(pass.getText())){
                    bandera = true;
                    
                    if(check == true){
                        System.out.println(user.getText());
                        String mensaje = "El usuario ingresado ya Existe";
                        JOptionPane.showMessageDialog(null, mensaje, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
                    
                    }
                    
                    
                    
                }else{
                    bandera = false;
                    String mensaje = "La confirmacion de contraseña es diferente a la contraseña ";
                    JOptionPane.showMessageDialog(null, mensaje, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
                    
                }
                
                check = false;
                if((bandera == true) && (check == false)){
                    try {
                        //System.out.println("TRUE");
                        String mensaje = "Registrado Exitosamente";
                        reg.nuevo(user.getText(),pass.getText(), confirmar.getText());
                        JOptionPane.showMessageDialog(null, mensaje, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
                        
                        ventana ven = new ventana();
                        ven.main();
                    } catch (IOException ex) {
                        Logger.getLogger(NuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                
            }
        });
    }
    
    public void main(){
        JFrame NuevoUsuario = new JFrame("Space Invaders");
        NuevoUsuario.setContentPane(new NuevoUsuario());
        NuevoUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        NuevoUsuario.setResizable(false);
        NuevoUsuario.pack();
        NuevoUsuario.setVisible(true);
    }
    
    private void agregarLabels() {
        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        lblConfirmarPass = new JLabel("Confirmar");
        lblUsername.setBounds(10, 100, widthTF, heightTF);
        lblPassword.setBounds(10, 130, widthTF, heightTF);
        lblConfirmarPass.setBounds(10, 160, widthTF, heightTF);
    }
    
    private void agregarCampos(){
        user = new JTextField();
        pass = new JTextField();
        confirmar = new JTextField();
        user.setBounds(80, 100, widthTF, heightTF);
        pass.setBounds(80, 130, widthTF, heightTF);
        confirmar.setBounds(80, 160, widthTF, heightTF);
    }
    
    private void agregarButtones(){
        Registrar = new JButton("Registrar");
        Registrar.setBounds(new Rectangle(75, 190, widthB, heightB));
    }
    
}


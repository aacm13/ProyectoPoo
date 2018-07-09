/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import Controladores.ControlDeTecaldo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import usuario.Dato;
import usuario.usuario;

/**
 *
 * @author aacm12
 */
public class PanelDeJuego extends JPanel {

    // componentes
    private Timer timerDeJuego;
    private ControlDeTecaldo control;
    // Controls size of game window and framerate
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final int framesPorSegundo = 120;

    // contadores
    Random r = new Random();
    private int puntos = 0;
    private int nivel = 1;
    private int vidas = 3;
    private int highScore;
    private int markerX, markerY;
    private static int vidaJefe = 30;
    File f = new File("Highscore.txt");

    // objetos
    private Nave playerShip;
    private Nave singleLife;
    private Nave bonusEnemy;
    private Enemigo enemy;
    private Escudo escudo;
    private Disparo bullet;
    private Laser laser, laser2, laser3;

    //boolean
    private boolean newBulletCanFire = true;
    private boolean newBeamCanFire = true;
    private boolean nuevoBonus = true;
    private boolean hitMarker = false;

    // Array Lists
    private ArrayList<Nave> listaDeVidas = new ArrayList();
    private ArrayList<Nave> listaDeBonus = new ArrayList();
    private ArrayList<Enemigo> listaEnemigos = new ArrayList();
    private ArrayList<Escudo> listaEscudo = new ArrayList();
    private ArrayList<Laser> listLaser = new ArrayList();
    private ImageIcon background = new ImageIcon("images/backgroundSkin.jpg");

    // Audio files and streams
    private File beamSound = new File("sounds/alienBeam.wav");
    private File bulletSound = new File("sounds/bulletSound.wav");
    private File levelUpSound = new File("sounds/levelUpSound.wav");
    private File deathSound = new File("sounds/deathSound.wav");
    private File hitmarkerSound = new File("sounds/hitmarkerSound.wav");
    private File shieldSound = new File("sounds/shieldSound.wav");
    private File bossSound = new File("sounds/bossSound.wav");
    private File bonusSound = new File("sounds/bonusSound.wav");
     private File damageSound = new File("sounds/damageSound.wav");
    private AudioStream beamSoundAudio;
    private InputStream beamSoundInput;
    private AudioStream bulletSoundAudio;
    private InputStream bulletSoundInput;
    private AudioStream levelUpSoundAudio;
    private InputStream levelUpSoundInput;
    private AudioStream deathSoundAudio;
    private InputStream deathSoundInput;
    private AudioStream hitSoundAudio;
    private InputStream hitSoundInput;
    private AudioStream shieldSoundAudio;
    private InputStream shieldSoundInput;
    private AudioStream bossSoundAudio;
    private InputStream bossSoundInput;
    private AudioStream bonusSoundAudio;
    private InputStream bonusSoundInput;
    private AudioStream damageSoundAudio;
    private InputStream damageSoundInput;


    public static int getBossHealth() {
        return vidaJefe;
    }

    public final void setupJuego() throws FileNotFoundException, IOException {
        
        // Sets enemigos en niveles no de JEFE
        if (nivel != 3 && nivel != 6 && nivel != 9 && nivel != 12) {
            // Six rows
            for (int row = 0; row < 6; row++) {
                // 5 columns
                for (int column = 0; column < 5; column++) {
                    enemy = new Enemigo((20 + (row * 100)), (20 + (column * 60)), nivel, 0, column, null, 40, 40); // Enemigo speed will increase each nivel
                    listaEnemigos.add(enemy);
                }
            }
        }
        // Setlos niveles en los q aparece el jefe
        if (nivel == 3 || nivel == 6 || nivel == 9 || nivel == 12) {
            AudioPlayer.player.start(bossSoundAudio); // sonido del boss
            enemy = new Enemigo(20, 20, 3, 0, 100, null, 150, 150);
            listaEnemigos.add(enemy);
        }
        // Gives directions on nivel 1
        if (nivel == 1) {
            JOptionPane.showMessageDialog(null, "Bienvenidos!\n\nInstrucciones:\n\n- Use las flechas izquierda/derecha para moverse\n- ocupe space para disparar\n- los enemigos se vuelven mas rapidos cada nivel"
                    + "\n- hay un JEFE cada 3 niveles\n- una nave que da puntos extra saldrá esporádicamente\n- disparele para ganar los puntos!\n- Presione R para reiniciar el puntaje\n- ACTIVAR EL SONIDO\n\n¡Desde el equipo JAR de POO, que lo disfrute!");
        }
        // Resetea todos los movimientos del controlador
        control.reiniciarControlador();

        // Set los valores iniciales para la nave del usuario 
        playerShip = new Nave(375, 730, null, control);

        // pone el contados del las vidas
        for (int column = 0; column < vidas; column++) {
            singleLife = new Nave(48 + (column * 20), 10, Color.WHITE, null);
            listaDeVidas.add(singleLife);
        }

        // Set los valores para los escudos
        for (int row = 0;
                row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                escudo = new Escudo(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                listaEscudo.add(escudo);
            }
        }
    }
    

    @Override
    public void paint(Graphics g) {


        background.paintIcon(null, g, 0, -150);


        if (bullet != null) {
            if (hitMarker) {
                g.setColor(Color.WHITE);
                if (nivel != 3 && nivel != 6 && nivel != 9 && nivel != 12) {
                    g.drawString("+ 100", markerX + 20, markerY -= 1);
                } else {
                    g.drawString("- 1", markerX + 75, markerY += 1);
                }
            }
        }
        // dibuja la nave del usuario
        playerShip.dibujar(g);

        // dibuja los 3 escudos
        for (int index = 0; index < listaEscudo.size(); index++) {
            listaEscudo.get(index).dibujar(g);
        }

        //dibuja los 3 diferentes tipos de aliens
        try {
            for (int index = 0; index < listaEnemigos.size(); index++) {
                listaEnemigos.get(index).dibujar(g);
            }

        } catch (IndexOutOfBoundsException e) {
        }

        // dibuja el disparo al presionar espacio
        if (control.getKeyStatus(32)) {
            if (newBulletCanFire) {
                bullet = new Disparo(playerShip.getXPosicion() + 22, playerShip.getYPosicion() - 20, 0, Color.RED);
                AudioPlayer.player.start(bulletSoundAudio); // Plays bullet sound
                newBulletCanFire = false;
            }
        }

        if (bullet != null) {
            bullet.dibujar(g);
        }

        // genera los ataques enemigos
        if (nivel != 3 && nivel != 6 && nivel != 9 && nivel != 12) {
            if (newBeamCanFire) {
                for (int index = 0; index < listaEnemigos.size(); index++) {
                    if (r.nextInt(30) == index) {
                        laser = new Laser(listaEnemigos.get(index).getXPosicion(), listaEnemigos.get(index).getYPosicion(), 0, Color.YELLOW);
                        listLaser.add(laser);
                        AudioPlayer.player.start(beamSoundAudio);
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Genera los ataques del jefe, estos son mas rapidos de lo normal
        if (nivel == 3 || nivel == 6 || nivel == 9 || nivel == 12) {
            if (newBeamCanFire) {
                for (int index = 0; index < listaEnemigos.size(); index++) {
                    if (r.nextInt(5) == index) {
                        laser = new Laser(listaEnemigos.get(index).getXPosicion() + 75, listaEnemigos.get(index).getYPosicion() + 140, 0, Color.YELLOW);
                        laser2 = new Laser(listaEnemigos.get(index).getXPosicion(), listaEnemigos.get(index).getYPosicion() + 110, 0, Color.YELLOW);
                        laser3 = new Laser(listaEnemigos.get(index).getXPosicion() + 150, listaEnemigos.get(index).getYPosicion() + 110, 0, Color.YELLOW);
                        listLaser.add(laser);
                        listLaser.add(laser2);
                        listLaser.add(laser3);
                        AudioPlayer.player.start(beamSoundAudio); 
                    }
                    newBeamCanFire = false;
                }
            }
        }
        // Dibuja los disparos
        for (int index = 0; index < listLaser.size(); index++) {
            listLaser.get(index).dibujar(g);
        }
        // genera el enemigo bonus
        if (nuevoBonus) {
            if (r.nextInt(3000) == 1500) {
                bonusEnemy = new Nave(-50, 30, Color.RED, null);
                listaDeBonus.add(bonusEnemy);
                nuevoBonus = false;
            }
        }
        // dibuja el bonus
        for (int index = 0; index < listaDeBonus.size(); index++) {
            listaDeBonus.get(index).bonusDraw(g);
        }

        // Set los puntos
        g.setColor(Color.WHITE);
        g.drawString("Score: " + puntos, 260, 20);

        // set el contador de vidas
        g.setColor(Color.WHITE);
        g.drawString("Lives:", 11, 20);
        for (int index = 0; index < listaDeVidas.size(); index++) {
            listaDeVidas.get(index).vidaDraw(g);
        }
        // Set el nivel en 
        g.setColor(Color.WHITE);
        g.drawString("Level " + nivel, 750, 20);

        // Set el puntaje mas alto
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + highScore, 440, 20);

        // dibuja la vida del boss
        if (nivel == 3 || nivel == 6 || nivel == 9 || nivel == 12) {
            g.setColor(Color.WHITE);
            g.drawString("Boss Health: " + vidaJefe, 352, 600);
        }
    }
    
    
    public void updateGameState(int frameNumber) throws IOException {

        // para q el usuario se pueda mover de izquierda a derecha
        playerShip.mover();

        // actualiza el puntaje mas alto 
        try {
            
            Scanner fileScan = new Scanner(f);
            while (fileScan.hasNextInt()) {
                String nextLine = fileScan.nextLine();
                Scanner lineScan = new Scanner(nextLine);
                highScore = lineScan.nextInt();
            }
            
            
            
            
        } catch (FileNotFoundException e) {
        }
        // para resetear el puntaje mas alto
        if (control.getKeyStatus(82)) {
            int respuesta = JOptionPane.showConfirmDialog(null, "resetear puntaje?", ":)", 0);
            control.reiniciarControlador();
            if (respuesta == 0) {
                try {
                    String scoreString = Integer.toString(0);
                    PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
                    pw.write(scoreString);
                    pw.close();
                } catch (FileNotFoundException e) {
                }
            }
        }
        // actualiza el puntaje mas alto en tiempo real, a medida q lo supera
        try {
            if (puntos > highScore) {
                String scoreString = Integer.toString(puntos);
                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                pw.write(scoreString);
                pw.close();
            }
        } catch (FileNotFoundException e) {
        }

        // para q los enemigos se muevan
        if ((listaEnemigos.get(listaEnemigos.size() - 1).getXPosicion() + listaEnemigos.get(listaEnemigos.size() - 1).getXVelocidad()) > 760 || (listaEnemigos.get(0).getXPosicion() + listaEnemigos.get(0).getXVelocidad()) < 0) {
            for (int index = 0; index < listaEnemigos.size(); index++) {
                listaEnemigos.get(index).setXVelocidad(listaEnemigos.get(index).getXVelocidad() * -1);
                listaEnemigos.get(index).setYPosicion(listaEnemigos.get(index).getYPosicion() + 10);
            }
        } else {
            for (int index = 0; index < listaEnemigos.size(); index++) {
                listaEnemigos.get(index).mover();
            }
        }

        // mueve el laser
        if (bullet != null) {
            bullet.setYPosicion(bullet.getYPosicion() - 15);
            if (bullet.getYPosicion() < 0) {
                newBulletCanFire = true;
            }

            // check para ver si le pego a algo
            for (int index = 0; index < listaEnemigos.size(); index++) {
                if (bullet.isColliding(listaEnemigos.get(index))) {
                    AudioPlayer.player.start(hitSoundAudio);
                    bullet = new Disparo(0, 0, 0, null);
                    newBulletCanFire = true;
                    // atualiza el puntaje en nivels normales
                    if (nivel != 3 && nivel != 6 && nivel != 9 && nivel != 12) {
                        puntos += 100;
                        hitMarker = true;
                        markerX = listaEnemigos.get(index).getXPosicion(); 
                        markerY = listaEnemigos.get(index).getYPosicion();
                        listaEnemigos.remove(index);

                    }
                    //atualiza el puntaje en nivels de JEFE
                    if (nivel == 3 || nivel == 6 || nivel == 9 || nivel == 12) {
                        hitMarker = true;
                        markerX = listaEnemigos.get(index).getXPosicion(); 
                        markerY = listaEnemigos.get(index).getYPosicion() + 165;
                        vidaJefe -= 1;
                        if (vidaJefe == 0) {
                            listaEnemigos.remove(index);
                            puntos += 9000;//premio por ganarle al jefe
                        }
                    }
                }
            }

            // check para ver si le pego al escudo o a lasers enemigos
            for (int index = 0; index < listaEscudo.size(); index++) {
                if (bullet.isColliding(listaEscudo.get(index))) {
                    // Calidad del escudo
                    // Fuerte
                    if (listaEscudo.get(index).getColor() == Color.RED) {
                        listaEscudo.get(index).setColor(Color.ORANGE);
                        AudioPlayer.player.start(shieldSoundAudio); 
                        bullet = new Disparo(0, 0, 0, null);
                        newBulletCanFire = true;
                    // Bueno
                    } else if (listaEscudo.get(index).getColor() == Color.ORANGE) {
                        listaEscudo.get(index).setColor(Color.YELLOW);
                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Disparo(0, 0, 0, null);
                        newBulletCanFire = true;
                    // acceptable
                    } else if (listaEscudo.get(index).getColor() == Color.YELLOW) {
                        listaEscudo.get(index).setColor(Color.WHITE);
                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Disparo(0, 0, 0, null);
                        newBulletCanFire = true;
                    // Debil
                    } else if (listaEscudo.get(index).getColor() == Color.WHITE) {
                        listaEscudo.remove(index);
                        AudioPlayer.player.start(shieldSoundAudio);
                        bullet = new Disparo(0, 0, 0, null);
                        newBulletCanFire = true;
                    }
                }
            }
        }
        // movimiento del bonus
        if (!listaDeBonus.isEmpty()) {
            for (int index = 0; index < listaDeBonus.size(); index++) {
                listaDeBonus.get(index).setXPosicion(listaDeBonus.get(index).getXPosicion() + (2));
                if (listaDeBonus.get(index).getXPosicion() > 800) {
                    listaDeBonus.remove(index);
                    nuevoBonus = true;
                }
            }
            // cunado le pega al bonus
            for (int index = 0; index < listaDeBonus.size(); index++) {
                if (bullet != null) {
                    if (listaDeBonus.get(index).isColliding(bullet)) {
                        listaDeBonus.remove(index);
                        bullet = new Disparo(0, 0, 0, null);
                        newBulletCanFire = true;
                        nuevoBonus = true;
                        AudioPlayer.player.start(bonusSoundAudio); 
                        puntos += 5000; // premio por pegarle al bonus
                    }
                }
            }
        }

        // mueve laser para aliens
        if (nivel != 3 && nivel != 6 && nivel != 9 && nivel != 12) {
            if (laser != null) {
                for (int index = 0; index < listLaser.size(); index++) {
                    listLaser.get(index).setYPosicion(listLaser.get(index).getYPosicion() + (4));
                    if (listLaser.get(index).getYPosicion() > 800) {
                        listLaser.remove(index);
                    }
                }
            }
        }
        //mueve laser para jefe
        if (nivel == 3 || nivel == 6 || nivel == 9 || nivel == 12) {
            if (laser != null) {
                for (int index = 0; index < listLaser.size(); index++) {
                    listLaser.get(index).setYPosicion(listLaser.get(index).getYPosicion() + (2 * nivel)); //la velocidad en la q dispara el jefe incrementara cada vez q aparezca
                    if (listLaser.get(index).getYPosicion() > 800) {
                        listLaser.remove(index);
                    }
                }
            }
        }

        // check para cuando los disparos de los enemigos le pegan o al laser del usuario o a los escudos
        try {
            for (int j = 0; j < listaEscudo.size(); j++) {
                for (int index = 0; index < listLaser.size(); index++) {
                    if (listLaser.get(index).isColliding(listaEscudo.get(j))) {
                        // Fuerte
                        if (listaEscudo.get(j).getColor() == Color.RED) {
                            listaEscudo.get(j).setColor(Color.ORANGE);
                            AudioPlayer.player.start(shieldSoundAudio);
                            listLaser.remove(index);
                        // Bueno
                        } else if (listaEscudo.get(j).getColor() == Color.ORANGE) {
                            listaEscudo.get(j).setColor(Color.YELLOW);
                            AudioPlayer.player.start(shieldSoundAudio);
                            listLaser.remove(index);
                        // acceptable
                        } else if (listaEscudo.get(j).getColor() == Color.YELLOW) {
                            listaEscudo.get(j).setColor(Color.WHITE);
                            AudioPlayer.player.start(shieldSoundAudio);
                            listLaser.remove(index);
                        // Debil
                        } else if (listaEscudo.get(j).getColor() == Color.WHITE) {
                            listaEscudo.remove(j);
                            AudioPlayer.player.start(shieldSoundAudio);
                            listLaser.remove(index);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

        // Check cuando le pegan al usuario
        for (int index = 0; index < listLaser.size(); index++) {
            if (listLaser.get(index).isColliding(playerShip)) {
                listLaser.remove(index);
                AudioPlayer.player.start(damageSoundAudio);
                listaDeVidas.remove(listaDeVidas.size() - 1); // quita una vida
            }
        }

        // solo crea nuevos lasers cuando el anterio ya a desaparacido
        if (listLaser.isEmpty()) {
            newBeamCanFire = true;
        }

        //Destruye el escudo si los aliens lo tocan
        for (int input = 0; input < listaEnemigos.size(); input++) {
            for (int j = 0; j < listaEscudo.size(); j++) {
                if (listaEnemigos.get(input).isColliding(listaEscudo.get(j))) {
                    listaEscudo.remove(j);
                }
            }
            // si los aliens llegan a esta posicion x entonces se resetea el nivel y pierde una vida
            if (listaEnemigos.get(input).getYPosicion() + 50 >= 750) {
                listaEnemigos.clear();
                listaEscudo.clear();
                listaDeVidas.clear();
                listLaser.clear();
                vidaJefe = 30;
                vidas -= 1;
                AudioPlayer.player.start(deathSoundAudio); 
                setupJuego();
            }
        }

        //actualiza la imagen del contador de vidas
        if (playerShip.isColliding) {
            int index = listaDeVidas.size() - 1;
            listaDeVidas.remove(index);
        } 
        // rmina el juego si el usuario se queda sin vidas
        else if (listaDeVidas.isEmpty()) {
            AudioPlayer.player.start(deathSoundAudio); 
            // a la opcion de salir o jugar otra vez
            int respuesta = JOptionPane.showConfirmDialog(null, "Quiere Jugar otra vez?", "Perdio con " + puntos + " puntos", 0);
            // si decide jugar otra vez esto reinicia todos los valores
            if (respuesta == 0) {
                listaDeVidas.clear();
                listaEnemigos.clear();
                listaEscudo.clear();
                listLaser.clear();
                listaDeBonus.clear();
                puntos = 0;
                nivel = 1;
                vidaJefe = 30;
                vidas = 3;
                newBulletCanFire = true;
                newBeamCanFire = true;
                nuevoBonus = true;
                setupJuego();
            }
            // si decide acabar el juego
            if (respuesta == 1) {
                System.exit(0);
            }
        }

        // mueve al siguiente nivel
        if (listaEnemigos.isEmpty()) {
            listLaser.clear();
            listaEscudo.clear();
            listaDeBonus.clear();
            listaDeVidas.clear();
            nivel += 1;
            vidaJefe = 30;
            setupJuego();
            AudioPlayer.player.start(levelUpSoundAudio);
        }
        
        // Sonidos
        try {
            beamSoundInput = new FileInputStream(beamSound);
            beamSoundAudio = new AudioStream(beamSoundInput);
            bulletSoundInput = new FileInputStream(bulletSound);
            bulletSoundAudio = new AudioStream(bulletSoundInput);
            levelUpSoundInput = new FileInputStream(levelUpSound);
            levelUpSoundAudio = new AudioStream(levelUpSoundInput);
            deathSoundInput = new FileInputStream(deathSound);
            deathSoundAudio = new AudioStream(deathSoundInput);
            hitSoundInput = new FileInputStream(hitmarkerSound);
            hitSoundAudio = new AudioStream(hitSoundInput);
            shieldSoundInput = new FileInputStream(shieldSound);
            shieldSoundAudio = new AudioStream(shieldSoundInput);
            bossSoundInput = new FileInputStream(bossSound);
            bossSoundAudio = new AudioStream(bossSoundInput);
            bonusSoundInput = new FileInputStream(bonusSound);
            bonusSoundAudio = new AudioStream(bonusSoundInput);
            damageSoundInput = new FileInputStream(damageSound);
            damageSoundAudio = new AudioStream(damageSoundInput);
        } catch (IOException e) {
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// GAME PANEL    
    
    public PanelDeJuego() throws IOException {
        // Set el tamaño del panel
        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        // Register ControlDeTecaldo as KeyListener
        control = new ControlDeTecaldo();
        this.addKeyListener(control);

        // Llama setupJuego ra initializar todos los campos
        this.setupJuego();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }


    public void start() {
        // Set un nuevo timer que se repita cada 20 millisegundos (50 FPS)
        timerDeJuego = new Timer(1000 / framesPorSegundo, new ActionListener() {

            // contancia de los numeros de frames producidos
            private int frameNumber = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // actualiza el estado del juego y pinta la pantalla
                    updateGameState(frameNumber++);
                    repaint();
                } catch (IOException ex) {
                    Logger.getLogger(PanelDeJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Timer gameTimerHitMarker = new Timer(1000, new ActionListener() {

            // contancia de los numeros de frames producidos
            @Override
            public void actionPerformed(ActionEvent e) {
                // actualiza el estado del juego y pinta la pantalla
                hitMarker = false;
            }
        });

        timerDeJuego.setRepeats(true);
        timerDeJuego.start();
        gameTimerHitMarker.setRepeats(true);
        gameTimerHitMarker.start();
    }

}

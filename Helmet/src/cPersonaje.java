import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class cPersonaje {

private int anchura_sprite = 60;
private int altura_sprite = 70;
public static int xa = 0;
public static int x = 0;
private Main game;
private Image Personaje;
private cSonido movimiento = new cSonido();
private cSonido puerta = new cSonido();
public static boolean inmobil;
public static boolean invertido;





public cPersonaje(Main game) {
    this.game = game;
}

//Movimiento del personaje
public void move() {

    if (x == 360 && cPuerta.abierta == true ) {
        x = 0;
        game.puntos =  game.puntos + 20;
        puerta.sonidoUnico("door.wav");
    }
    if ((x + xa > 0) && (x + xa <= 400) ) {
        x = x + xa;
    }
}

public void paint(Graphics2D g) {

    ImageIcon personaje = new ImageIcon(this.getClass().getResource("Imagenes/prota.gif"));
    Personaje = personaje.getImage();

    g.drawImage(Personaje ,x,280, anchura_sprite, altura_sprite, game);
}

public void keyReleased(KeyEvent e) {
    xa = 0;
    movimiento.sonidoUnico("object_sound.wav");
}

//Metodo para cualquier tipo de movimiento, inverso, o inmobil
public void keyPressed(KeyEvent e) {

    if (inmobil == true){
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = 0;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = 0;
        }
    }

    else if (invertido == true){

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = 60;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = -60;
        }

    }
    else {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -60;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (x == 300 && cPuerta.abierta == false) {
                xa = 0;
            } else {
                xa = 60;
            }
        }
    }
}
}
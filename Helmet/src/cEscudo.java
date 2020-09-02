import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class cEscudo extends cObjetos {

    private Main game;
    public static boolean activo = false;
    int rand = new Random().nextInt(0 + 400) - 400;
    int y = rand;
    int yTool = 10;
    boolean puntos_salida = false;
    cSonido vida_menos = new cSonido();

    Thread efecteEscut;


    public cEscudo(Main game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics2D g, ImageIcon escudo) {
        Image escut;
        escut = escudo.getImage();
        g.drawImage(escut , 120,y, game);
    }

    @Override
    public void move() {

        if((game.puntos%100) == 0 && game.puntos > 0 && !puntos_salida)  {
            yTool = yTool + 5;
            puntos_salida = true;
        }
        else if((game.puntos%100) != 0){
            puntos_salida = false;
        }

        if (y >= 235){
            rand = new Random().nextInt(0 + 400) - 400;
            y = rand;
        }
        else {
            y = y + yTool;
        }
    }

    @Override
    public void efecto() {
        if (y >= 235 && cPersonaje.x == 120 && cEscudo.activo == false){
            vida_menos.sonidoUnico("escudo.wav");
            cEscudo.activo = true;

            efecteEscut = new Thread(() -> {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cEscudo.activo = false;
            });

            efecteEscut.start();

        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class cTornavis extends cObjetos {

    private Main game;
    int rand = new Random().nextInt(0 + 400) - 400;
    int y = rand;
    int yTool = 10;
    boolean puntos_salida = false;
    Thread efecteTornavis;
    cSonido vida_menos = new cSonido();

    public cTornavis(Main game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics2D g, ImageIcon tornavis) {
        Image Tornavis;
        Tornavis = tornavis.getImage();
        g.drawImage(Tornavis , 60,y, game);
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
        if((y >= 235 && cPersonaje.x == 60 && cEscudo.activo == false)){

            vida_menos.sonidoUnico("live_lost.wav");

            game.vidas--;

            cPersonaje.invertido = true;

            efecteTornavis = new Thread(() -> {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cPersonaje.invertido = false;
            });

            efecteTornavis.start();
        }
    }
}
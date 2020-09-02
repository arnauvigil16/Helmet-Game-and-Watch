import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class cClau_anglesa extends cObjetos {

    private Main game;
    int rand = new Random().nextInt(0 + 400) - 400;
    int y = rand;
    int yTool = 10;
    boolean puntos_salida = false;
    Thread efecteClau;
    cSonido vida_menos = new cSonido();

    public cClau_anglesa(Main game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics2D g, ImageIcon clau) {
        Image Clau;
        Clau = clau.getImage();
        g.drawImage(Clau , 300,y, game);
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

        if((y >= 235 && cPersonaje.x == 300 && cEscudo.activo == false)){

            game.vidas--;


            vida_menos.sonidoUnico("live_lost.wav");


            cPersonaje.inmobil = true;

            efecteClau = new Thread(() -> {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cPersonaje.inmobil = false;


            });

            efecteClau.start();



        }

    }
}
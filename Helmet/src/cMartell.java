import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class cMartell extends cObjetos {

    private Main game;

    int rand = new Random().nextInt(0 + 400) - 400;

    int y = rand;
    int yTool = 10;
    cSonido vida_menos = new cSonido();

    boolean puntos_salida = false;


    public cMartell(Main game) {
        this.game = game;
    }


    @Override
    public void paint(Graphics2D g,ImageIcon martell) {
        Image Martell;

        Martell = martell.getImage();
        g.drawImage(Martell , 180,y, game);
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
        if(y >= 235 && cPersonaje.x == 180 && cEscudo.activo == false){
                vida_menos.sonidoUnico("live_lost.wav");
                game.vidas = game.vidas - 2;
                cPersonaje.x = 0;
        }
    }
}

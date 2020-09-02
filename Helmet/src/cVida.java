import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class cVida extends cObjetos {

    private Main game;
    int rand = new Random().nextInt(0 + 400) - 400;
    int y = rand;
    int yTool = 10;
    boolean puntos_salida = false;
    cSonido vida = new cSonido();
    cSonido cien_puntos = new cSonido();

    public cVida(Main game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics2D g, ImageIcon vida) {
        Image Vida;
        Vida = vida.getImage();
        g.drawImage(Vida , 240,y, game);
    }

    @Override
    public void move() {

        if((game.puntos%100) == 0 && game.puntos > 0 && !puntos_salida)  {
            yTool = yTool + 5;
            puntos_salida = true;
            cien_puntos.sonidoUnico("100points.wav");
        }
        else if((game.puntos%100) != 0){
            puntos_salida = false;
        }

        if (y >= 235){
            rand = new Random().nextInt(0 + 2000) - 2000;
            y = rand;
        }
        else {
            y = y + yTool;
        }
    }

    @Override
    public void efecto() {
        if (y >= 235 && cPersonaje.x == 240 && cEscudo.activo == false){
            if (game.vidas >= 5){
            }
            else {
                game.vidas++;
                vida.sonidoUnico("1up.wav");
            }
        }
    }
}
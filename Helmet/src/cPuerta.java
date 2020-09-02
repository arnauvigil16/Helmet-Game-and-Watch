import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Rectangle;



public class cPuerta {

    public static boolean abierta;
    private Main game;

    Color color;




    Graphics g;
    Graphics2D g2d = (Graphics2D) g;


    private Timer timer = new Timer("myTimer");
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {


            if (color == Color.black) {
                abierta = false;
                color = Color.white;
            }
            else {
                abierta = true;
                color = Color.BLACK;

                //g2d.setColor(Color.BLACK);
                //g2d.fillRect(30, 30, 30, 30);
            }

        }
    };

    int y_puerta = 276;
    int x_puerta = 360;
    int width = 43;
    int height = 75;
    int random = 3000;

    public cPuerta(Main game) {
        this.game = game;
        timer.scheduleAtFixedRate(timerTask, 0, random);

    }




    public void pintarPuerta(Graphics2D g){

        g.setColor(color);
        g.fillRect(x_puerta, y_puerta, width, height);
    }

}

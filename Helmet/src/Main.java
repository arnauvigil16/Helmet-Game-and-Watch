import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Main extends JPanel {

    private cPersonaje protagonista = new cPersonaje(this);
    private cMartell martell = new cMartell(this);
    private cEscudo escut = new cEscudo(this);
    private cVida vida = new cVida(this);
    private cClau_anglesa clau_anglesa = new cClau_anglesa(this);
    private cTornavis tornavis = new cTornavis(this);
    private cPuerta puerta = new cPuerta(this);

    public static int puntos = 0;
    public static int vidas = 5;

    ArrayList<cObjetos> objetos = new ArrayList<>();

    public Main() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                protagonista.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                protagonista.keyPressed(e);
            }
        });

        setFocusable(true);
        
        objetos.add(martell);
        objetos.add(escut);
        objetos.add(vida);
        objetos.add(clau_anglesa);
        objetos.add(tornavis);
    }

    //Metode que fa moure tot el joc
    private void move() {

        //Efectes dels objectes
        vida.efecto();
        martell.efecto();
        escut.efecto();
        clau_anglesa.efecto();
        tornavis.efecto();

        //Moviment del jugador i objectes
        martell.move();
        escut.move();
        vida.move();
        clau_anglesa.move();
        tornavis.move();
        protagonista.move();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image fondo;
        ImageIcon background;

        //Imatge de fons
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        background = new ImageIcon(this.getClass().getResource("Imagenes/background.jpeg"));

        fondo = background.getImage();
        Graphics2D Pantalla = (Graphics2D)g;

        //Imatges del joc
        ImageIcon Martell = new ImageIcon(this.getClass().getResource("Imagenes/hammer.png"));
        ImageIcon Escudo = new ImageIcon(this.getClass().getResource("Imagenes/shield.png"));
        ImageIcon Corazon = new ImageIcon(this.getClass().getResource("Imagenes/heart.png"));
        ImageIcon Clau = new ImageIcon(this.getClass().getResource("Imagenes/clau_anglesa.png"));
        ImageIcon Tornavis = new ImageIcon(this.getClass().getResource("Imagenes/tornavis.png"));
        Pantalla.drawImage(fondo, 0, 0, this);

        g2d.setFont(new Font("Verdana", Font.TYPE1_FONT, 15));
        g2d.drawString("Puntuacion: " + puntos, 20,20);
        g2d.drawString("Vidas: " + vidas, 200,20);

        //Pintar tots els objectes a la pantalla
        protagonista.paint(g2d);
        martell.paint(g2d, Martell);
        escut.paint(g2d, Escudo);
        vida.paint(g2d, Corazon);
        clau_anglesa.paint(g2d, Clau);
        tornavis.paint(g2d, Tornavis);
        puerta.pintarPuerta(g2d);
    }


    //Crear el espai de joc
    public static void main(String[] args) throws  IOException {
        JFrame frame = new JFrame("Helmet");
        cSonido background = new cSonido();

        Main game = new Main();

        frame.add(game);
        frame.setSize(450,430);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        background.loop("background_music.wav");

        //Loop del joc
        while (true) {
            if (vidas <= 0) {
                background.close();
                cSonido muerte = new cSonido();
                muerte.sonidoUnico("death_sound.wav");

                //Demanem el nom del jugador
                String name = JOptionPane.showInputDialog("Introduce su nombre: ");
                while (name == null || name.isEmpty()){
                    name = JOptionPane.showInputDialog("Es necesario que introduzca su nombre para continuar: ");
                }

                //Guardem les dades en un json
                cPuntuacion.guardarDades(name, puntos);

                //Mostrar el top de les puntuacions, ordenades per major a menor
                ArrayList<cScorePoints> partidasScore = new ArrayList<>();
                String puntuacionesMaximas = "Puntuaciones Maximas: " + "\n" + "";

                partidasScore = cPuntuacion.mostrarDades();

                for (int i = 0 ; i < partidasScore.size(); i++){
                    puntuacionesMaximas = puntuacionesMaximas + partidasScore.get(i) + "\n";
                }

                JOptionPane.showMessageDialog(frame, "Puntuacion: " + puntos + "\n" + "\n" + puntuacionesMaximas);

                System.exit(ABORT);

            }
            else {
                game.move();
                game.repaint();
            }
        }
    }


}

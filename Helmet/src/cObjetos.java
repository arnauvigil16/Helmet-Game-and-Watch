import javax.swing.*;
import java.awt.*;

//Clase abstracte en la que heredan tots els objectes del joc
public abstract class cObjetos {

    public abstract void paint(Graphics2D g, ImageIcon imagen);

    public abstract void move();

    public abstract void efecto();
}

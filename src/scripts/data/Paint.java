package scripts.data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Toon on 04/04/15.
 */
public class Paint {

    public static final RenderingHints AA = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    public static final Image IMG = getImage("http://i.imgur.com/nkI4wZe.png");

    public static final Color white = Color.white;
    public static final Color gray = new Color(0, 0, 0, 130);

    public static final Font text = new Font("Arial", 0, 12);

    public void drawStat(Graphics2D g, String stat, int x, int y){
        g.setFont(text);
        g.setColor(white);
        g.drawString(stat, x, y);
    }

    private static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }

}



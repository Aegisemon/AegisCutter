package scripts.data;

import org.tribot.api2007.Skills;

import java.awt.*;

/**
 * Created by Toon on 04/04/15.
 */
public class Paint {

    public static final long startTime = System.currentTimeMillis();
    public static final int startLvl = Skills.getActualLevel(Skills.SKILLS.WOODCUTTING);
    public static final int startXP = Skills.getXP(Skills.SKILLS.WOODCUTTING);

    public static final Color white = Color.white;
    public static final Color blue = Color.blue;
    public static final Color gray = new Color(0, 0, 0, 100);

    public static final Font title = new Font("Arial", 0, 16);
    public static final Font text = new Font("Arial", 0, 12);

}

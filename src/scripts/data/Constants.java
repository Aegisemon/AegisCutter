package scripts.data;

import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

/**
 * Created by Toon on 03/04/15.
 */
public class Constants {

    public static final String[] TREE = { "Tree", "Oak", "Willow", "Yew" };
    public static final String[] AXE = { "Bronze axe", "Iron axe", "Steel axe", "Mithril axe", "Adamant axe", "Rune axe", "Dragon axe" };

    public static final RSArea DRAYNOR_BANK_AREA = new RSArea(new RSTile(3092, 3240, 0), new RSTile(3097, 3246, 0));
    public static final RSArea TREE_AREA = new RSArea(new RSTile(3088, 3229, 0), 6);

    public static final RSArea[] PATH_TO_TREE_AREA = {
            new RSArea(new RSTile(3098, 3240, 0), new RSTile(3099, 3246, 0)),
            new RSArea(new RSTile(3093, 3234, 0), new RSTile(3096, 3229, 0)),
            new RSArea(new RSTile(3088, 3229, 0), 3)
    };

    public static final long START_TIME = System.currentTimeMillis();
    public static final int START_LVL = Skills.getActualLevel(Skills.SKILLS.WOODCUTTING);
    public static final int START_XP = Skills.getXP(Skills.SKILLS.WOODCUTTING);

}

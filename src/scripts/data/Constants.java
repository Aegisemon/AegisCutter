package scripts.data;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

/**
 * Created by Toon on 03/04/15.
 */
public class Constants {

    public static final String[] TREE = { "Tree", "Oak", "Willow", "Yew" };
    public static final String[] AXE = { "Bronze axe", "Iron axe", "Steel axe", "Mithril axe", "Adamant axe", "Rune axe", "Dragon axe" };
    public static String treeToChop = TREE[2];

    public static boolean hasAxeInInventory = Inventory.find(AXE).length > 0;
    public static boolean hasAxeEquiped = Equipment.find(AXE).length > 0;
    public static boolean bankingEnabled = true;

    public static final RSArea TREE_AREA = new RSArea(new RSTile(3089, 3229, 0), 5);
    public static final RSTile[] PATH_TO_TREE_AREA = { new RSTile(3098, 3243, 0), new RSTile(3096, 3231, 0), new RSTile(3089, 3229, 0) }; //TODO: Randomize this path

}

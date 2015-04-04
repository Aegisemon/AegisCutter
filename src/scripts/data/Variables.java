package scripts.data;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;

/**
 * Created by Toon on 04/04/15.
 */
public class Variables {

    public static String treeToChop = Constants.TREE[0];

    public static boolean hasAxeInInventory = Inventory.find(Constants.AXE).length > 0;
    public static boolean hasAxeEquiped = Equipment.find(Constants.AXE).length > 0;
    public static boolean bankingEnabled = true;
    public static boolean isFirstRun = true;

}

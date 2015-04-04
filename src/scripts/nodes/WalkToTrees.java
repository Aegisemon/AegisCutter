package scripts.nodes;

import scripts.core.Node;
import scripts.data.Constants;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;

/**
 * Created by Toon on 04/04/15.
 */
public class WalkToTrees extends Node {
    @Override
    public String status() {
        return "Walking to the tree area.";
    }

    @Override
    public boolean validate() {
        return !isInTreeArea() && !Inventory.isFull() && !Player.getRSPlayer().isInCombat();
    }

    @Override
    public void execute() {
        walkToTreeArea();
    }

    private void walkToTreeArea() {
        for (RSArea tile : Constants.PATH_TO_TREE_AREA) {
            Walking.clickTileMM(tile.getRandomTile(), 1);
            General.sleep(4000, 7800);
        }
    }

    private boolean isInTreeArea() {
        return Constants.TREE_AREA.contains(Player.getPosition());
    }
}

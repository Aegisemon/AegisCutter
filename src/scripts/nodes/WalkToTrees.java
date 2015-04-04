package scripts.nodes;

import org.tribot.api2007.WebWalking;
import scripts.core.Node;
import scripts.data.Constants;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import scripts.data.Variables;

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
        if (Variables.isFirstRun) {
            WebWalking.walkTo(Constants.DRAYNOR_BANK_AREA.getRandomTile());
            if (isInDraynorBank()) {
                Variables.isFirstRun = false;
            }
        } else {
            walkToTreeArea();
        }
    }

    private void walkToTreeArea() {
        for (RSArea tile : Constants.PATH_TO_TREE_AREA) {
            Walking.clickTileMM(tile.getRandomTile(), 1);
            General.sleep(3000, 6800);
        }
    }

    private boolean isInTreeArea() {
        return Constants.TREE_AREA.contains(Player.getPosition());
    }

    private boolean isInDraynorBank() {
        return Constants.DRAYNOR_BANK_AREA.contains(Player.getPosition());
    }
}

package scripts.nodes;

import org.tribot.api2007.*;
import scripts.core.Node;
import scripts.data.Constants;

import org.tribot.api.General;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.data.Variables;

/**
 * Created by Toon on 03/04/15.
 */
public class Chop extends Node {
    @Override
    public String status() {
        return "Chopping..";
    }

    @Override
    public boolean validate() {
        return (Variables.hasAxeInInventory || Variables.hasAxeEquiped) && !Inventory.isFull() && !Player.getRSPlayer().isInCombat() && Player.getAnimation() == -1;
    }

    @Override
    public void execute() {
        if (isInTreeArea()) {
            RSObject tree = closestTree(Variables.treeToChop, 5);
            if (tree != null && Player.getAnimation() == -1) {
                if (tree.isOnScreen()){
                    tree.getModel().click();
                    preventSpamClick();
                } else {
                    Walking.blindWalkTo(tree.getPosition());
                    Camera.turnToTile(tree.getPosition());
                }
            }
        } else {
            walkToTreeArea();
        }
    }

    private void preventSpamClick() {
        int timeout = 0;
        while (Player.getAnimation() == -1) {
            timeout++;
            General.sleep(10);
            if (timeout > 250)
                break;
        }
    }

    private RSObject closestTree(String tree, int range) {
        RSObject[] objects = Objects.findNearest(range, tree);
        if (objects.length > 0)
            return objects[0];
        return null;
    }

    private void walkToTreeArea() {
        if (isInDraynorBank()) {
            for (RSTile tile : Constants.PATH_TO_TREE_AREA) {
                Walking.clickTileMM(tile, 1);
                General.sleep(2300, 4800);
            }
        } else {
            WebWalking.walkTo(Constants.TREE_AREA.getRandomTile());
        }
    }

    private boolean isInTreeArea() {
        return Constants.TREE_AREA.contains(Player.getPosition());
    }

    private boolean isInDraynorBank() {
        return Constants.DRAYNOR_BANK_AREA.contains(Player.getPosition());
    }
}

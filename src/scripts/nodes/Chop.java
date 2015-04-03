package scripts.nodes;

import org.tribot.api2007.*;
import scripts.core.Node;
import scripts.data.Constants;

import org.tribot.api.General;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

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
        return (Constants.hasAxeInInventory || Constants.hasAxeEquiped) && !Inventory.isFull() && !Player.getRSPlayer().isInCombat();
    }

    @Override
    public void execute() {
        if (isInTreeArea()) {
            RSObject tree = closestTree(Constants.treeToChop, 5);
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
        if (Banking.isInBank()) {
            for (RSTile tile : Constants.PATH_TO_TREE_AREA) {
                Walking.clickTileMM(tile, 1);
                General.sleep(2300, 4800);
            }
        }
    }

    private boolean isInTreeArea() {
        return Constants.TREE_AREA.contains(Player.getPosition());
    }


}

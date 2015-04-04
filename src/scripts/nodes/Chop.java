package scripts.nodes;

import scripts.data.Variables;
import scripts.core.Node;

import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;

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
        return objects.length > 0 ? objects[0] : null;
    }
}

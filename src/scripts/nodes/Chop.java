package scripts.nodes;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.types.RSItem;
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
        return "Cutting..";
    }

    @Override
    public boolean validate() {
        return (Variables.hasAxeInInventory || Variables.hasAxeEquiped) && !Inventory.isFull() && !Player.getRSPlayer().isInCombat();
    }

    @Override
    public void execute() {
        RSObject tree = closestTree(Variables.treeToChop, 20);
        if (tree != null && Player.getAnimation() == -1) {
            if (tree.isOnScreen()){
                tree.getModel().click();

                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Player.getAnimation() == -1;
                    }
                }, General.random(300, 700));

            } else {
                Walking.blindWalkTo(tree.getPosition());
                Camera.turnToTile(tree.getPosition());
            }
        }
    }

    private RSObject closestTree(String tree, int range) {
        RSObject[] trees = Objects.findNearest(range, tree);
        return trees.length > 0 ? trees[0] : null;
    }
}

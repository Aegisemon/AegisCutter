package scripts.nodes;

import scripts.core.Node;
import scripts.data.Constants;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

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
        //TODO: make the chopping execute() function
    }
}

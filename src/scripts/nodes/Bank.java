package scripts.nodes;

import scripts.core.Node;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;

/**
 * Created by Toon on 03/04/15.
 */
public class Bank extends Node {
    @Override
    public String status() {
        return "Banking.";
    }

    @Override
    public boolean validate() {
        return Inventory.isFull();
    }

    @Override
    public void execute() {
        /*
        * Walk to nearest bank
        * Find bank
        * Open the bank
        * Desposit all except for axe if found in inventory
        */
        WebWalking.walkToBank();
    }
}

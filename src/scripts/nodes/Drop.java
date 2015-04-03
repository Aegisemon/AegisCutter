package scripts.nodes;

import scripts.core.Node;
import scripts.data.Constants;

import org.tribot.api2007.Inventory;

/**
 * Created by Toon on 03/04/15.
 */
public class Drop extends Node {
    @Override
    public String status() {
        return "Dropping logs";
    }

    @Override
    public boolean validate() {
        return Inventory.isFull() && !Constants.bankingEnabled;
    }

    @Override
    public void execute() {
        Inventory.dropAllExcept(Constants.AXE);
    }
}

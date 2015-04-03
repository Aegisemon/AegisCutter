package scripts.nodes;

import org.tribot.api2007.Banking;
import scripts.core.Node;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import scripts.data.Constants;

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
        return Inventory.isFull() && Constants.bankingEnabled;
    }

    @Override
    public void execute() {
        if (!Banking.isInBank()) {
            WebWalking.walkToBank();
        } else {
            if (Banking.isInBank()) {
                Banking.openBankBooth();
                if (Banking.isBankScreenOpen())
                    Banking.depositAllExcept(Constants.AXE);
            }
        }
    }
}

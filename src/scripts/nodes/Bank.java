package scripts.nodes;

import scripts.core.Node;
import scripts.data.Constants;
import scripts.data.Variables;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.Banking;

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
        return Inventory.isFull() && Variables.bankingEnabled;
    }

    @Override
    public void execute() {
        if (walk()){
            Banking.openBank();
            if (Banking.isBankScreenOpen()){
                if (Inventory.find("Willow logs").length == Banking.depositAllExcept(Constants.AXE))
                    Banking.close();
            }
        }
    }

    private boolean walk() {
        return WebWalking.walkToBank() && Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(200, 300);
                return Banking.isInBank();
            }
        }, General.random(8000, 9000));
    }
}

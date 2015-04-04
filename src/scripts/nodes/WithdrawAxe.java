package scripts.nodes;

import scripts.core.Node;
import scripts.data.Constants;
import scripts.data.Variables;

/**
 * Created by Toon on 04/04/15.
 */
public class WithdrawAxe extends Node {
    @Override
    public String status() {
        return "Going to get an axe from the bank.";
    }

    @Override
    public boolean validate() {
        return !Variables.hasAxeInInventory && !Variables.hasAxeEquiped;
    }

    @Override
    public void execute() {
        //TODO: Withdraw the right axe from the bank
    }
}

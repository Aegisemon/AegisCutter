package scripts.nodes;

import org.tribot.api2007.Banking;
import scripts.core.Node;

import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;

/**
 * Created by Toon on 03/04/15.
 */
public class Rescue extends Node {
    @Override
    public String status() {
        return "Saving your ass!";
    }

    @Override
    public boolean validate() {
        return Player.getRSPlayer().isInCombat();
    }

    @Override
    public void execute() {
        //TODO: Turn on run speed!
        WebWalking.walkToBank();
        rest();
    }

    public void rest(){
        if (Banking.isInBank()){
            int seconds = (General.random(9, 10) - Player.getRSPlayer().getHealth()) * 45;
            General.sleep((seconds*1000) - 1800, (seconds*1000) + 3200);
        }
    }
}

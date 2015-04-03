package scripts.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import scripts.core.Node;

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
        WebWalking.walkToBank();
        rest();
    }

    public void rest(){
        int seconds = General.random(8, 10) - Player.getRSPlayer().getHealth();
        System.out.println("Waiting " + seconds + " to get health back up.");
        General.sleep((seconds*1000) - 1800, (seconds*1000) + 3200);
    }
}

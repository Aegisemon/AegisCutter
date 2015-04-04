package scripts.core;

import org.tribot.api.Timing;
import org.tribot.api2007.Skills;
import scripts.nodes.Bank;
import scripts.nodes.Chop;
import scripts.nodes.Drop;
import scripts.nodes.Rescue;
import scripts.data.Paint;

import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.interfaces.Painting;
import org.tribot.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Toon on 03/04/15.
 */

@ScriptManifest(name = "AegisChopper", version = 1.0, category = "Woodcutting", authors = { "Aegisemon" }, description = "Cuts Willow trees in Draynor with a level 3 char.")

public class AegisChopper extends Script implements Painting{
    List<Node> nodes = new ArrayList<Node>();

    public void onStart(){
        General.useAntiBanCompliance(true);
        Collections.addAll(nodes, new Chop(), new Bank(), new Drop(), new Rescue());
    }

    @Override
    public void run() {
        onStart();
        loop(300, 800);
    }

    public void loop(int min, int max){
        while (true) {
            for (final Node node : nodes) {
                if (node.validate()) {
                    System.out.println(node.status());
                    node.execute();
                    sleep(General.random(min, max));
                }
            }
        }
    }

    @Override
    public void onPaint(Graphics graphics) {

        //TODO: Make methods

        long timeRunning = System.currentTimeMillis() - Paint.startTime;
        int currentLvl = Skills.getActualLevel(Skills.SKILLS.WOODCUTTING);
        int levelsGained = currentLvl - Paint.startLvl;
        int xpToLevel = Skills.getXPToNextLevel(Skills.SKILLS.WOODCUTTING);

        int xpGained = Skills.getXP(Skills.SKILLS.WOODCUTTING) - Paint.startXP;
        int xpPerHour = (int) (xpGained / ( timeRunning/ 3600000D));
        long timeToLevel = (long) (Skills.getXPToNextLevel(Skills.SKILLS.WOODCUTTING) * 3600000D / xpPerHour);

        Graphics2D g = (Graphics2D)graphics;
        g.setColor(Paint.gray);
        g.fillRect(8, 345, 504, 129);
        g.setFont(Paint.title);
        g.setColor(Paint.blue);
        g.drawString("AegisCutter", 360, 360);
        g.setFont(Paint.text);
        g.setColor(Paint.white);
        g.drawString("Time Ran: " + Timing.msToString(timeRunning), 360, 375);
        g.drawString("Current Lvl: " + currentLvl + " (+" + levelsGained + ")", 360, 390);
        g.drawString("Time to Lvl: " + Timing.msToString(timeToLevel), 360, 405);
        g.drawString("XP Gained: " + xpGained, 360, 420);
        g.drawString("XP/H: " + xpPerHour, 360, 435);
        //g.drawString("Logs chopped: " + );
    }
}

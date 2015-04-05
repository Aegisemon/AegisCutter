package scripts.core;

import org.tribot.api2007.Combat;
import scripts.data.*;
import scripts.nodes.*;
import scripts.data.Paint;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Skills;
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

@ScriptManifest(name = "AegisCutter", version = 1.0, category = "Woodcutting", authors = { "Aegisemon" }, description = "Cuts Willow trees in Draynor with a level 3 char.")

public class AegisChopper extends Script implements Painting{
    List<Node> nodes = new ArrayList<Node>();

    public void onStart(){
        General.useAntiBanCompliance(true);
        Combat.setAutoRetaliate(false);
        Collections.addAll(
                nodes,
                new WalkToTrees(),
                new Chop(),
                new Bank(),
                new Drop(),
                new Rescue());
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

        long timeRunning = System.currentTimeMillis() - Constants.START_TIME;
        int currentLvl = Skills.getActualLevel(Skills.SKILLS.WOODCUTTING);
        int levelsGained = currentLvl - Constants.START_LVL;
        int currentTotalXP = Skills.getXP(Skills.SKILLS.WOODCUTTING);

        int xpGained = Skills.getXP(Skills.SKILLS.WOODCUTTING) - Constants.START_XP;
        int xpPerHour = (int)(xpGained / ( timeRunning/ 3600000D));
        int logs = (int)((currentTotalXP - Constants.START_XP) / 67.5);
        int logsPerHour = (int)(logs / ( timeRunning/ 3600000D));
        long timeToLevel = (long)(Skills.getXPToNextLevel(Skills.SKILLS.WOODCUTTING) * 3600000D / xpPerHour);

        Graphics2D g = (Graphics2D)graphics;
        Paint p = new Paint();

        g.setRenderingHints(Paint.AA);

        g.setColor(Paint.gray);
        g.fillRect(8, 345, 504, 129);

        g.drawImage(Paint.IMG, 0, 303, null);

        p.drawStat(g, Timing.msToString(timeRunning), 405, 373);
        p.drawStat(g, Timing.msToString(timeToLevel), 432, 388);
        p.drawStat(g, currentLvl + " (+" + levelsGained + ")", 432, 403);
        p.drawStat(g, xpGained + " ( XP/H: " + xpPerHour + ")", 373, 418);
        p.drawStat(g, logs + " ( Logs/H: " + logsPerHour + ")", 382, 433);
    }


}

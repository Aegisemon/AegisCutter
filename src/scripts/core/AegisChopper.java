package scripts.core;

import org.tribot.script.ScriptManifest;
import scripts.nodes.Bank;
import scripts.nodes.Chop;
import scripts.nodes.Drop;
import scripts.nodes.Rescue;

import org.tribot.api.General;
import org.tribot.script.Script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Toon on 03/04/15.
 */

@ScriptManifest(name = "AegisChopper", version = 1.0, category = "Woodcutting", authors = { "Aegisemon" }, description = "Cuts Willow trees in Draynor with a level 3 char.")

public class AegisChopper extends Script{
    List<Node> nodes = new ArrayList<Node>();

    public void onStart(){
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
}

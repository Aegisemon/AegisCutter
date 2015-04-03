package scripts.core;

/**
 * Created by Toon on 03/04/15.
 */
public abstract class Node {

    public abstract String status();
    public abstract boolean validate();
    public abstract void execute();

}

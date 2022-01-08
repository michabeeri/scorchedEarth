package engine;

/**
 * Represents a script
 */
public abstract class Script implements GameComponent<Script>{
    public abstract void update(GameObject object, ExecutionContext context);
}


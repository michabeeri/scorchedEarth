package engine;

/**
 * Represents script execution context
 */
public class ExecutionContext {
    private long deltaTime;

    public ExecutionContext(long deltaTime) {
        this.deltaTime = deltaTime;
    }

    public long getDeltaTime() {
        return this.deltaTime;
    }
}


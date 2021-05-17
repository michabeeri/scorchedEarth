package engine;

public interface GameComponent<T extends GameComponent<T>> {
    T createCopy();
}

package engine;

import java.lang.reflect.InvocationTargetException;

public abstract class Script implements GameComponent<Script>{
    public abstract void update(GameObject gameObject, long dt);

    public Script createCopy() {
        try {
            return this.getClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}

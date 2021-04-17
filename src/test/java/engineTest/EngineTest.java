package engineTest;

import java.util.*;
import engine.Engine;
import engine.GameObject;
import engine.Scene;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

class EngineTest {

    @Test
    void createGameObject() {
        var gameEngine = new Engine();
        var mainScene = new Scene();
        var go1 = new GameObject();
        var go2 = new GameObject();
        mainScene.add(go1);
        mainScene.add(go2);
        gameEngine.load(mainScene);
        Assert.assertEquals(gameEngine.toString(), (new ArrayList<>(Arrays.asList(new GameObject[]{go1, go2}))).toString());
    }
}
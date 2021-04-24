package game;

import engine.*;
import viewer.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;

public class ScorchedEarth {

	public static void main(String args[]) {
		System.out.println("Scorched started");
		var dimention = new Dimension(900, 420);
		var viewer = new Viewer(dimention);
		var gameEngine = new Engine(viewer);
		var mainScene = new Scene();

		var backgroundSprite = new Sprite("src/main/resources/background.jpg");
//		var spaceshipSprite = new Sprite("src/main/resources/spaceship.png");

		try {
			backgroundSprite.load();
//			spaceshipSprite.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		var background = new GameObject();
		var backgroundRenderer = new SpriteRenderer(backgroundSprite, new Rectangle2D.Double(0, 0, 900, 420));
		background.addComponent(backgroundRenderer);
		mainScene.add(background);

//		var spaceship = new GameObject();
//		var spaceshipRenderer = new SpriteRenderer(spaceshipSprite, new Rectangle2D.Double(0, 0, 840, 601));
//		background.addComponent(backgroundRenderer);
//		mainScene.add(background);

		gameEngine.load(mainScene);
		gameEngine.render();

//		try
//		{
//			TimeUnit.SECONDS.sleep(1);
//		}
//		catch(InterruptedException ex)
//		{
//			Thread.currentThread().interrupt();
//		}

	}
}

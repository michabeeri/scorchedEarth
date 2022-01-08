package game;

import engine.*;
import viewer.*;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.*;

public class ScorchedEarth {

	public static void main(String args[]) {
		System.out.println("Scorched started");
		Sprite backgroundSprite = null;
		Sprite spaceshipSprite = null;
		try {
			backgroundSprite = Sprite.load("src/main/resources/bg_normal.jpg");
			spaceshipSprite = Sprite.load("src/main/resources/spaceship.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		var dimension = new Dimension(1000, 500);
		var viewer = new Viewer(dimension);
		var camera = new Camera(new Point2D.Double(0, 0), 2.5, 2, new Rectangle2D.Double(0, 0, 1, 1));
		var config = new Config();
		var gameEngine = new Engine(viewer, camera, config);
		var mainScene = new Scene();

		var background = new GameObject();
		var backgroundRenderer = new SpriteRenderer(backgroundSprite, new Rectangle2D.Double(0, 0, 900, 420));
		background.addComponent(backgroundRenderer);
		background.setTransform(new Transform(new Point2D.Double(5, 2.5), new Point2D.Double(5, 5), 0, 10));
		mainScene.add(background);

		var spaceship = new GameObject();
		var spaceshipRenderer = new SpriteRenderer(spaceshipSprite, new Rectangle2D.Double(0, 0, 511, 721));
		spaceship.addComponent(spaceshipRenderer);
		spaceship.setTransform(new Transform(new Point2D.Double(5, 2.5), new Point2D.Double(1, 1), 45, 0));
		spaceship.addComponent(new Spaceship());
		var spaceshipRigidBody = new RigidBody(1, new Point2D.Double(0, 0.001), new Point2D.Double(0, -0.0000002), true);
		spaceship.addComponent(spaceshipRigidBody);
		mainScene.add(spaceship);

		gameEngine.load(mainScene);
		gameEngine.render();
		gameEngine.gameLoop();

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

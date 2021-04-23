package game;

import engine.*;
import viewer.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.*;
import java.io.File;
import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class ScorchedEarth {
	private static BufferedImage loadImage(String filename) {
		try {
			var file = new File(filename);
//            var fs = new FileInputStream(file);
			var img = ImageIO.read(file);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String args[]){
		System.out.println("Scorched started");
//		var gameEngine = new Engine();
//		var mainScene = new Scene();
//		mainScene.add(new GameObject());
//		mainScene.add(new GameObject());
//		gameEngine.load(mainScene);
//		gameEngine.render();
		var img1 = loadImage("src/main/resources/pixelArt1.jpg");
		var img2 = loadImage("src/main/resources/pixelArt2.png");
		var viewer = new Viewer(900, 420);
		viewer.setImage(img2);

		try
		{
			TimeUnit.SECONDS.sleep(1);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}

		System.out.println("here");
		viewer.setImage(img1);
	}
}

package game;

import engine.*;

public class ScorchedEarth {
	public static void main(String args[]){
		System.out.println("Scorched started");
		var gameEngine = new Engine();
		var mainScene = new Scene();
		mainScene.add(new GameObject());
		mainScene.add(new GameObject());
		gameEngine.load(mainScene);
	}
}

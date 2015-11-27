package main;

import logic.MainLogic;
import render.GameManager;

public class Main {

	public static void main(String[] args) {
		MainLogic logic = new MainLogic();
		GameManager.runGame(logic);

	}

}

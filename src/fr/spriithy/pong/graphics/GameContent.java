package fr.spriithy.pong.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class GameContent {

	public Platform	player;
	public Platform	ai;
	public Ball		ball;

	public boolean isEnded = false;

	public GameContent() {
		player = new Platform(5, 225, 5);
		ai = new Platform(480, 225, 5);
		ball = new Ball(250, 250, 7);
		resetPosition();
	}

	public void resetPosition() {
		player.resetPosition();
		ai.resetPosition();
		ball.resetPosition();
	}

	public void display(Graphics gc) {
		player.draw(gc);
		ai.draw(gc);
		ball.draw(gc);
	}

	public void clear(Graphics gc) {
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, 500, 500);
		gc.setColor(Color.WHITE);
		gc.fillRect(0, 0, 500, 30);
	}
}

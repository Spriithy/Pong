package fr.spriithy.pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import fr.spriithy.pong.graphics.GameContent;
import fr.spriithy.pong.graphics.Window;
import fr.spriithy.pong.input.KeyInput;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private Window		window;
	private GameContent	content;
	private KeyInput	input;

	private Thread	thread;
	private boolean	shouldRun	= false;

	public Game() {
		Dimension size = new Dimension(500, 500);
		setPreferredSize(size);

		window = new Window("Pong", 500, 500);
		content = new GameContent();
		input = new KeyInput();

		window.add(this);
		window.addKeyListener(input);
	}

	public synchronized void start() {
		window.setVisible(true);
		shouldRun = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		shouldRun = false;
		try {
			thread.join();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		react();

		if (input.boost) content.player.boost = 2;
		else content.player.boost = 1;

		if (input.up) content.player.moveUp();
		if (input.down) content.player.moveDown();

		content.ball.nextPosition(content.player, content.ai);

		if (content.ball.x < 5 || content.ball.x > 495) content.isEnded = true;
	}

	public void react() {
		if (content.ball.y > content.ai.y && content.ball.movx > 0) {
			if (content.ball.y <= content.ai.y + 50) content.ai.speed = 4;
			content.ai.moveDown();
		}

		if (content.ball.y < content.ai.y + 50 && content.ball.movx > 0) {
			if (content.ball.y >= content.ai.y - 100) content.ai.speed = 4;
			content.ai.moveUp();
		}
		content.ai.speed = 5;
	}

	public void render() {
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics gc = buffer.getDrawGraphics();

		{
			/* Graphic stuff there */
			content.clear(gc);
			content.display(gc);
		}

		gc.dispose();
		buffer.show();
	}

	public void run() {

		long previous = System.nanoTime();
		final double frameRate = 1000000000 / 60.;
		double d = 0;

		while (shouldRun) {
			long current = System.nanoTime();
			d += (current - previous) / frameRate;
			previous = current;

			for (; d >= 1 && (!content.isEnded); d--)
				update();
			
			/* Play again ? */
			if (content.isEnded && input.boost) {
				content.resetPosition();
				content.isEnded = false;
			}
			
			/* Rage quit */
			if (content.isEnded && input.esc) System.exit(0);

			render();
		}
		stop();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}

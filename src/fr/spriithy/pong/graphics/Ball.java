package fr.spriithy.pong.graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Ball implements Drawable {

	private final int	initialX;
	private final int	initialY;

	private Color color = Color.WHITE;

	public int	x;
	public int	y;

	public int	movx;
	public int	movy;

	public int		speed;
	public int		direction;
	public double	angle;

	public Ball(int x, int y, int speed) {
		initialX = x;
		initialY = y;
		this.speed = speed;
		resetPosition();
	}

	public void bounce() {
		direction = (movx > 0) ? -1 : 1;

		angle = Math.random() * Math.PI / 2 - 45;
		movx = (int) (direction * Math.cos(angle) * speed);
		movy = (int) (speed * Math.sin(angle));
	}

	public void nextPosition(Platform player, Platform ai) {
		if (movx < 0 && x < 15 && y >= player.y && y <= player.y + 50) {
			bounce();
			x = 15;
		}

		if (movx > 0 && x > 473 && y >= ai.y && y <= ai.y + 50) {
			bounce();
			x = 470;
		}

		if (y < 35 || y > 470) movy *= -1;

		x += movx;
		y += movy;
	}

	public void resetPosition() {
		direction = 1;
		angle = Math.random() * Math.PI / 2 - 45;
		movx = (int) (direction * Math.cos(angle) * speed);
		movy = (int) (speed * Math.sin(angle));
		x = initialX;
		y = initialY;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void draw(Graphics gc) {
		gc.setColor(color);
		gc.fillOval(x - 6, y - 6, 12, 12);
	}

}

package fr.spriithy.pong.graphics;

import java.awt.Color;
import java.awt.Graphics;

public interface Drawable {

	void resetPosition();

	void setColor(Color color);

	void draw(Graphics gc);

}

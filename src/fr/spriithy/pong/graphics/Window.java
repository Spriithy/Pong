package fr.spriithy.pong.graphics;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

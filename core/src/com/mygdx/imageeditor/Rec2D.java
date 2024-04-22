package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;

public class Rec2D {
	public Vector2 Scale;
	public Vector2 Position;
	public Texture RecTexture;
	private Pixmap _pixelMap;
	protected Color _recColor;
	Texture img;
	public Outline Outline;

	public Rec2D(Vector2 scale, Vector2 position, Color color) {
		Scale = scale;
		Position = position;
		_recColor = color;
		generateTexture();
		Outline = new Outline(scale, Color.WHITE, 10);
		
		ImageEditor.Instance.Rectangles.add(this);
	}

	Pixmap rectangleMap;

	public void generateTexture() {
		_pixelMap = new Pixmap((int) Scale.x, (int) Scale.y, Format.RGBA8888);
		_pixelMap.setColor(_recColor);
		for (int x = 0; x < _pixelMap.getWidth(); x++) {
			for (int y = 0; y < _pixelMap.getHeight(); y++) {
				_pixelMap.drawPixel(x, y);
			}
		}
		RecTexture = new Texture(_pixelMap);
	}

	public void changeColor(Color newColor) {
		_recColor = newColor;
		generateTexture();
	}
	
	public void changeColor() {
		java.util.Random random = new java.util.Random();
		this.changeColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
	}
}

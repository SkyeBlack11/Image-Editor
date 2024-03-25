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
		private Color _recColor;
		Texture img;
		

		public Rec2D(Vector2 scale, Vector2 position, Color color){
			
			Scale = scale;
			Position = position;
			_recColor = color;
		}
		
		Pixmap rectangleMap;
		private void generateTexture() {
			rectangleMap = new Pixmap(200, 100, Format.RGBA8888);
			rectangleMap.setColor(Color.BLACK);
			for(int x = 0; x < rectangleMap.getWidth(); x++) {
				for (int y = 0; y < rectangleMap.getHeight(); y++) {
					rectangleMap.drawPixel(x, y);
				}
			}
			img = new Texture(rectangleMap);
		}
}

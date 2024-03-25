package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

//public Pixmap (int width, int height, Format format) {}


public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		rectangle = new Rec2D(new Vector2(200,100), new Vector2(200,200), Color.RED);
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(.4f, 0f, 0.5f, 1);
		batch.begin();
		batch.draw(rectangle.RecTexture, rectangle.Position.x, rectangle.Position.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}

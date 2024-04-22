package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.desktop.ScreenSleepEvent;
import java.util.*;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public Vector2 _screenSize;
	public static ImageEditor Instance;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	private EditWindow _editWindow;
	
	
	
	@Override
	public void create () {
		Instance = this;
		new ImageInputOutput();
		
		
		CollisionManager collisionManager = new CollisionManager();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		
		batch = new SpriteBatch();
		
		
		_screenSize = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

//Start	
	//TODO	
	//Added texture call here 
		Vector2 editWindowSize = new Vector2(500, _screenSize.y - 50);
		_editWindow = new EditWindow(editWindowSize, new Vector2(_screenSize.x - editWindowSize.x, 0));
		
		//try {
		//	ImageInputOutput.Instance.saveImage("C:\\Users\\haley\\Desktop\\testImage.bmp");
		//} catch (Exception e) {
		//	// TODO: handle exception
		//	System.out.println("SaveImage did not work");
		//	e.printStackTrace();
		//}//End Catch
		
		
		//Util.testIntToSignedBytes();
		
		Button button1 = new Button(new Vector2(60,60), Vector2.Zero, Color.GOLD);
		
	}//End Create()

	
	
	
	
	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		
		Rec2D rec;
		for(int i =0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
			
		batch.draw(_editWindow.DoodleTexture,_editWindow.Position.x, _editWindow.Position.y, _editWindow.Scale.x, _editWindow.Scale.y);
		batch.end();
	}//End Render()
	
	@Override
	public void dispose () {
		batch.dispose();
	}//End Dispose()
	
	public void filesImported(String[] filepaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filepaths[0]);
		if(map == null)return;
		
		_editWindow.RecTexture = new Texture(map);
	}//end filesImported
}//End Class




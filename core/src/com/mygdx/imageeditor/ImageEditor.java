package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.Button;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;
import com.mygdx.utility.CollisionManager;
import com.mygdx.utility.ImageInputOutput;
import com.mygdx.utility.InputManager;

import java.awt.desktop.ScreenSleepEvent;
import java.util.*;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public Vector2 _screenSize;
	public static ImageEditor Instance;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	private EditWindow _editWindow;
	private BitmapFont _font;
	Button button;
	
	
	
	@Override
	public void create () {
		Instance = this;
		
		inializeUtilityClasses();
		createGraphicalElements();
	}
	
	private void inializeUtilityClasses() {
		new ImageInputOutput();
		new CollisionManager();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();
		
		
	}
	
	private void createGraphicalElements() {
		_screenSize = new Vector2(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Vector2 editWindowSize = new Vector2(500, _screenSize.y - 25);
		_editWindow = new EditWindow(editWindowSize, new Vector2(_screenSize.x - editWindowSize.x, 0));
		
		batch = new SpriteBatch();
		createButtonSheet();
		new SaveButton(new Vector2(75,  25), new Vector2(75*0, _screenSize.y-25), Color.GRAY);
		new ExitButton(new Vector2(75,  25), new Vector2(75*1+1, _screenSize.y-25), Color.GRAY);
		new ClearDoodleButton(new Vector2(75,  25), new Vector2(75*2+2, _screenSize.y-25), Color.GRAY);
		
	}
	
	public void createButtonSheet() {
		new ColorButton(new Vector2(42, 42), new Vector2(0, 0), Color.GOLD);
		new ColorButton(new Vector2(42, 42), new Vector2(0,42), Color.BLUE);
		new ColorButton(new Vector2(42, 42), new Vector2(0, 42*2), Color.ORANGE);
		new ColorButton(new Vector2(42, 42), new Vector2(0,42*3), Color.RED);
		new ColorButton(new Vector2(42, 42), new Vector2(0, 42*4), Color.GREEN);
		new ColorButton(new Vector2(42, 42), new Vector2(42,42), Color.PURPLE);
		new ColorButton(new Vector2(42, 42), new Vector2(42,0), Color.FIREBRICK);
		new ColorButton(new Vector2(42, 42), new Vector2(42,42*2), Color.BLACK);
		new ColorButton(new Vector2(42, 42), new Vector2(42,42*3), Color.WHITE);
		new ColorButton(new Vector2(42, 42), new Vector2(42, 42*4), Color.BROWN);
		new ColorButton(new Vector2(42, 42), new Vector2(42,42*5), Color.VIOLET);
		new ColorButton(new Vector2(42, 42), new Vector2(0, 42*5), Color.TEAL);
	}

	
	
	
	
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
		
//This is broken rn
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(0);
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
			//System.out.println("Made it here");
			
		}
//Broken above here	
		
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			if(rec instanceof Button) {
				button = (Button) rec;
			
			if(button.ButtonText == null) continue;
			_font.draw(batch, button.ButtonText, button.Position.x, button.Position.y + button.Scale.y*0.75f, button.Scale.x, Align.center, false);
		
			}
		}	
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




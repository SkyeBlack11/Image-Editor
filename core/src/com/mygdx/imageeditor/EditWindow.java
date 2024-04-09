package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable {
	public Texture DoodleTexture;
	private Pixmap _doodleMap;

	public EditWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
		super(scale, position, backgroundColor);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
		InputManager.Instance.Clickable.add(this);
	}
	
	public void paintAtPosition(Vector2 worldPosition) {
		_doodleMap.drawPixel((int) (worldPosition.x - Position.x), (int) (Scale.y - worldPosition.y));
		DoodleTexture = new Texture(_doodleMap);
	}

	public void onClickDown(Vector2 mousePosition) {
		paintAtPosition(mousePosition);
		//_doodleMap.drawPixel((int) (mousePosition.x - Position.x), (int) (Scale.y - mousePosition.y));
		//DoodleTexture = new Texture(_doodleMap);
	}

	@Override
	public void onClickUp(Vector2 mousePosition) {

	}

	@Override
	public void onClickDragged(Vector2 mousePosition) {
		paintAtPosition(mousePosition);
	}

}

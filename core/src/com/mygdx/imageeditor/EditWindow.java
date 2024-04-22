package com.mygdx.imageeditor;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable{
	public Texture DoodleTexture;
//TODO Start
	public Pixmap _doodleMap;
	private Vector2 _previousPaintPosition;
	public static EditWindow Instance;
//Stop
	
	
//TODO Added ImageTex, Super gets Color.Gray, RecTexture is added	
	public EditWindow(Vector2 scale, Vector2 position) {
		super(scale, position, Color.GRAY);
//Start
		Instance = this;
//Stop		
		//RecTexture = ImageTex;
		_doodleMap = new Pixmap((int) scale.x, (int)scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
		InputManager.Instance.Clickable.add(this);
	}//End Constructor
	
	public void paintAtPosition(Vector2 worldPosition) {
		Vector2 paintPosition = new Vector2(worldPosition.x - Position.x, Scale.y - worldPosition.y);
		int startX = (int) _previousPaintPosition.x;
		int StartY = (int) _previousPaintPosition.y;
		int endX = (int) paintPosition.x;
		int endY = (int) paintPosition.y;
		
		_doodleMap.drawLine(startX, StartY, endX, endY);
		_doodleMap.drawLine(startX+1, StartY, endX+1, endY);
		_doodleMap.drawLine(startX-1, StartY, endX-1, endY);
		_doodleMap.drawLine(startX, StartY+1, endX, endY+1);
		_doodleMap.drawLine(startX, StartY-1, endX, endY-1);
		
		
		//_doodleMap.drawLine( (int) _previousPaintPosition.x, (int)_previousPaintPosition.y, (int) paintPosition.x, (int) paintPosition.y);
		_previousPaintPosition = paintPosition;
		//_doodleMap.drawPixel((int)(worldPosition.x - Position.x), (int)(Scale.y - worldPosition.y));
		DoodleTexture = new Texture(_doodleMap);
	}
	
	public void onClickDown(Vector2 mousePosition) {
		if(_previousPaintPosition == null) 
			_previousPaintPosition = new Vector2(mousePosition.x - Position.x,Scale.y - mousePosition.y);
		
		paintAtPosition(mousePosition);
	}
	
	@Override
	public void onClickUp(Vector2 mousePosition) {	_previousPaintPosition = null; }
	
	@Override
	public void onClickDragged(Vector2 mousePosition) {	paintAtPosition(mousePosition);	}
	
}//End Class

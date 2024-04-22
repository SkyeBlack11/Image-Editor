package com.mygdx.imageeditor;

import java.io.IOException;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public Array<IClickable> Clickable = new Array<IClickable>();
	public Array<IHoverable> Hoverable = new Array<IHoverable>();
	public static InputManager Instance;
	private IHoverable _hoveredButton;
	private IClickable _currentlyClicked;
	private String filePath = "C:\\Users\\haley\\Desktop\\testImage.bmp";
	
	
	public InputManager(){
		Instance = this;
	}
	
//TODO Start
	private boolean _controlPressed;
	@Override
	public boolean keyDown(int keycode) {
		if(_controlPressed && keycode == Keys.S)
			try {ImageInputOutput.Instance.saveImage(filePath);} 
			catch (IOException e) {e.printStackTrace();}
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = true;
			
		
		return false;
	}
	
//Stop

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.CONTROL_LEFT)_controlPressed = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY);
		IClickable collision = CollisionManager.Instance.getClicked(new Vector2(worldPosition));
		if(collision != null) {collision.onClickDown(worldPosition);}
		_currentlyClicked = collision;
		return false;
		
	}//End Touch Down

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//if(_hoveredButton != null) {
			_currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
		//}TODO: This is no broken again. the hover feature is acting weird but I can draw lines properly.
		
		
		
		
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		if(_currentlyClicked != null) {
			_currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance._screenSize.y-screenY));
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable collision = CollisionManager.Instance.getHovered(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
		if(_hoveredButton != null && _hoveredButton != collision) {_hoveredButton.onHoverExit();}
		if(collision != null)collision.onHovered();		
		
		_hoveredButton = collision;
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}//End Class

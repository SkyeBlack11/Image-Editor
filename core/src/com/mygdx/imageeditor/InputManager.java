package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public Array<IClickable> Clickable = new Array<IClickable>();
	public Array<IHoverable> Hoverable = new Array<IHoverable>();
	public static InputManager Instance;
	private IHoverable _hoveredButton;
	private IClickable _currentlyClicked;

	public InputManager() {
		Instance = this;
	}

	public boolean keyDown(int keycode) {
		return false;
	}

	public boolean keyUp(int keycode) {
		return false;
	}

	public boolean keyTyped(char character) {
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY);
		IClickable collision = CollisionManager.Instance.getClicked(new Vector2(worldPosition));
		if (collision != null)
			collision.onClickDown(worldPosition);
		_currentlyClicked = collision;
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (_currentlyClicked != null)
			_currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
		
		return false;
	}

	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		//Is it me?
		mouseMoved(screenX, screenY);
		if(_currentlyClicked != null)
			_currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable collision = CollisionManager.Instance.getHovered(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
		if (collision != _hoveredButton && _hoveredButton != null)
			_hoveredButton.onHoverExit();
		if (collision != null)
			collision.onHovered();

		_hoveredButton = collision;
		return true;
	}

	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}

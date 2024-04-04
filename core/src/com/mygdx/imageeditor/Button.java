package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D implements IClickable, IHoverable {
	private Color _startColor;
	private Color _hoveredColor;

	public enum ButtonState {
		Clicked, Hovered, None
	};

	private ButtonState _state;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		_hoveredColor = new Color(_startColor.r / 2f, _startColor.g / 2f, _startColor.b / 2f, 1);
		InputManager.Instance.Clickable.add(this);
		InputManager.Instance.Hoverable.add(this);
		_state = ButtonState.None;
	}

	public void onHovered() {
		if (_state == ButtonState.Clicked)
			return;
		_recColor = _hoveredColor;
		if (_state == ButtonState.Hovered)
			return;
		_state = ButtonState.Hovered;
		generateTexture();
	}

	public void onHoverExit() {
		_state = ButtonState.None;
		_recColor = _startColor;
		generateTexture();
	}

	@Override
	public void onClickDragged(Vector2 mousePosition) {

	}

	@Override
	public void onClickDown(Vector2 mousePostion) {
		if (_state == ButtonState.Clicked)
			return;
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r / 4f, _startColor.g / 4f, _startColor.b / 4f, 1);
		generateTexture();

	}

	@Override
	public void onClickUp(Vector2 mousePostion) {
		_state = ButtonState.Hovered;
		_recColor = _hoveredColor;
		generateTexture();

	}

}

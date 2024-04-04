package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public interface IClickable {
	public void onClickDown(Vector2 mousePostion);

	public void onClickUp(Vector2 mousePostion);

	public void onClickDragged(Vector2 mousePosition);
}
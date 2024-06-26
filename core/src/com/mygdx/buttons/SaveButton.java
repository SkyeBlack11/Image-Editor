package com.mygdx.buttons;

import java.io.IOException;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utility.ImageInputOutput;

public class SaveButton extends Button {
	public SaveButton(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		ButtonText = "Save";
		if(ImageInputOutput.Instance.ImageFolderLocation == null)return;
		try {ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.ImageFolderLocation+"\\testImage.bmp");}
		catch(IOException e) {e.printStackTrace();}
	}
	
	public void onClickUp(Vector2 mousePosition) {
		super.onClickUp(mousePosition);
		System.out.println("Clicked Save!");
	}
}

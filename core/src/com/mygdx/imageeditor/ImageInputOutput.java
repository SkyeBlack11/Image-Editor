package com.mygdx.imageeditor;

import com.badlogic.gdx.Gdx;

public class ImageInputOutput {
	public static ImageInputOutput Instance;
	
	public ImageInputOutput() {
	Instance = this;
	}

	public void loadImage(String filePath) {
		byte[] bytes = Gdx.files.internal(filePath).readBytes();
		if(bytes[0] != 'B' || bytes[1] != 'M') {
			System.out.println(filePath + " is NOT a bitmap image");
			return;
			}
		byte[] fileSize = {bytes[2], bytes[3], bytes[4], bytes[5]};
		byte[] start = {bytes[10], bytes[11], bytes[12], bytes[13]};
		byte[] widthBytes = {bytes[18], bytes[19], bytes[20], bytes[21]};
		byte[] heightBytes = {bytes[22], bytes[23], bytes[24], bytes[25]};
		byte[] bitsPerPixel = {bytes[28], bytes[29]};
		int startPoint = Util.bytesToInt(start);
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
		if(bytesPerPixel != 3) {System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); return;}
		System.out.println("I'm going to load " + filePath);
		}
	
}

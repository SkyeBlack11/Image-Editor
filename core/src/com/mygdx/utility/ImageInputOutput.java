package com.mygdx.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer.Form;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.EditWindow;
import com.mygdx.imageeditor.Util;

public class ImageInputOutput {
	public static ImageInputOutput Instance;
	private byte[] _fileHeader;
	private Pixmap _pixels;
	public String ImageFolderLocation;
	

	public ImageInputOutput() {
		Instance = this;
	}
	
	public Pixmap loadImage(String filePath) {
		byte[] fileBytes = Gdx.files.internal(filePath).readBytes();
		if	(fileBytes[0] != 'B' || fileBytes[1] != 'M') {System.out.println(filePath + " is NOT a bitmap image"); return null;}
		
		int[] fileIntData = Util.unsignedBytes(fileBytes);
	
		byte[] fileSize = {fileBytes[2], fileBytes[3], fileBytes[4], fileBytes[5]};
		byte[] start = {fileBytes[10], fileBytes[11], fileBytes[12], fileBytes[13]};
		byte[] widthBytes = {fileBytes[18], fileBytes[19], fileBytes[20],fileBytes[21]};
		byte[] heightBytes = {fileBytes[22], fileBytes[23], fileBytes[24], fileBytes[25]};
		byte[] bitsPerPixel = {fileBytes[28], fileBytes[29]};
		int startPoint = Util.bytesToInt(start);
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel)/8;
		if (bytesPerPixel != 3) { System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); return null;}
		
		Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
		int r,g,b;
		int x = 0;
		int y = height-2;
		
		for (int i = startPoint; i <fileIntData.length -3; i +=3) {
			r = fileIntData[i];
			g = fileIntData[i+1];
			b = fileIntData[i+2];
			
			pixels.setColor(b/256f, g/256f, r/256f, 1);
			
			pixels.drawPixel(x, y);
			x +=1;
		
			if(x > width-1) {x =0; y-=1;}
			
		}//End for loop
		
		
				_fileHeader = new byte[startPoint];
				for(int spot = 0; spot < startPoint; spot++) {
					_fileHeader[spot] = fileBytes[spot];
				}
				
				_pixels = pixels;
				ImageFolderLocation = scrapeFolderLocation(filePath);

		return pixels;
	}//End load image
	
	public void saveImage( String filePath) throws IOException {
		FileOutputStream output = new FileOutputStream(filePath);
		byte[] color;
		byte[] colorData  = new byte[_pixels.getWidth()*_pixels.getHeight()*3];
		int colorIndex = 0;
		
		for (int y = _pixels.getHeight() - 1; y >= 0; y--) {
			for(int x = 0; x < _pixels.getWidth(); x++) {
				int tempColor = _pixels.getPixel(x, y);
				color = Util.intToSignedBytes(_pixels.getPixel(x, y));
				colorData[colorIndex] = color[2];
				colorData[colorIndex +1] = color[1];
				colorData[colorIndex +2] = color[0];
				colorIndex += 3;
				
			}//End second for loop
		}//End first for loop
		
		Pixmap doodle = EditWindow.Instance._doodleMap;
		Vector2 desiredSize = new Vector2(_pixels.getWidth(), _pixels.getHeight());
		
		colorIndex = 0;
		//System.out.println("Height is: "+ doodle.getHeight());
		//System.out.println("Width is: "+ doodle.getWidth());
		doodle = Util.scalePixmap(doodle, desiredSize);
		for(int y = doodle.getHeight() -1; y >= 0; y--) {
			for (int x = 0; x < doodle.getWidth(); x++) {
				color = Util.intToSignedBytes(doodle.getPixel(x,y));
				if(color[3]!= -1) {colorIndex+=3; continue;}
				colorData[colorIndex] = color[2];
				colorData[colorIndex +1] = color[1];
				colorData[colorIndex +2] = color[0];
				colorIndex +=3;
			}//End second for 
		}//End first for
		
		
		output.write(_fileHeader);
		output.write(colorData);
		output.close();
	}
	
	private String scrapeFolderLocation(String filepath) {
		StringBuilder builder = new StringBuilder(filepath);
		for(int i = filepath.length()-1; i >= 0; i--) {
			if (filepath.charAt(i) != '\\') continue;
				return builder.substring(0,i);
		}
		return null;
	}
	
}//End Class 




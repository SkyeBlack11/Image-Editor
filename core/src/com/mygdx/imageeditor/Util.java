package com.mygdx.imageeditor;

import java.awt.Color;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int result = 0;
		
		for (int i =0; i < bytes.length; i++) {
			result += (int) bytes[i] << (8*i);
		}
		
		return result;
	}//End bytesToInt
	
	public static int[] unsignedBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for (int i = 0; i<bytes.length; i++) {
			if(bytes[i] > 0)ints[i] = bytes[i];
			if(bytes[i] < 0) {
				int distance = bytes[i] - (-129);
				ints[i] = 127+distance;
			}//End if
		}//End for
		return ints;
	}//End Unsign

//TODO Start
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		for(int i = 0; i < result.length; i++) {
			int temp = (value << (i*8));
			result[i] = (byte)(temp >> 24);
		}//End for
		
		return result;
	}//End intToSignedBytes
	
	public static Pixmap scalePixmap(Pixmap source, Vector2 desiredSize) {
		int sourceX, sourceY;
		Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		for(int targetX = 0; targetX < target.getWidth(); targetX++) {
			for(int targetY = 0; targetY < target.getHeight(); targetY++) {
				sourceX = (int)Math.round(((float)(targetX)/target.getWidth())*source.getWidth());
				
				
				
				sourceY = (int)Math.round(((float)(targetY)/target.getHeight())*source.getHeight());
				//System.out.println(sourceY);
				target.drawPixel(targetX, targetY, source.getPixel(sourceX, sourceY));
				if(targetX > 500) {
					//System.out.println("Info");
					//System.out.println(targetX);
					//System.out.println(target.getWidth());
					//System.out.println("Is this the issue ?"+ source.getWidth());
					//System.out.println(target.getWidth()*source.getWidth());
					//System.out.println(targetX/target.getWidth()*source.getWidth());
					//System.out.println(Math.round(targetX/target.getWidth()* source.getWidth()));
					//System.out.println(sourceX);
					//System.out.println(sourceY);
				}
				
			}//end second for
			
		}//end first for
		
		
		
		return target;
	}
	
}//End Util class

package com.mygdx.imageeditor;
 
// EVERYTHING IN HERE SHOULD BE STATIC!!!
public class Util {
	public static int bytesToInt(byte[] bytes) {
		int result = 0;
		for(int i = 0; i < bytes.length; i++) {
		result += (int) bytes[i] << (8 * i);
		}
		return result;
	}
}

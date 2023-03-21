package com.tokio.proyectofinal.util;

import javafx.scene.text.Font;

public class FontUtil {
	
	private FontUtil() {};	
	public static Font getFont(String name, int size) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		return Font.loadFont(classloader.getResourceAsStream("font/" + name), size); 
		
	}
}

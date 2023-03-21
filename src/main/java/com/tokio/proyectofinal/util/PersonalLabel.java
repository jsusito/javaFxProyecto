package com.tokio.proyectofinal.util;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//Devuelven Label personalizados con un color, tipo de fuente y tamaño para el proyecto.
public class PersonalLabel extends Label{
	private PersonalLabel() {}
	
	
	public static Label getLabel(String text, int size) {
		Label label = new Label(text.toUpperCase());
		Font font = FontUtil.getFont("CircularStd-Medium.otf", size); 
		label.setFont(font);
		label.setMinSize(20, 60);
		label.setTextFill(Color.WHITE);
		return label;
	}
	
	public static Label getLabel(String text, int size, String nameFont) {
		Label label = new Label(text.toUpperCase());
		Font font = FontUtil.getFont(nameFont, size); 
		label.setFont(font);
		label.setMinSize(20, 60);
		label.setTextFill(Color.WHITE);
		return label;
	}
}

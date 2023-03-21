package com.tokio.proyectofinal.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageUtil {
	
	//La clase ImageView de JavaFx es un nodo que representa las imagenes en una escena
	public static ImageView getImageView(String name) {
		
	//La clase ClassLoader es un buscador de recursos, para cargar archivos desde un
	//ruta dada
	//Para este proyecto los iconos son de 100x105
		
	//Manera tipica de cargar recursos, es así, creamos un hilo para ClassLoader.
	//getResourceAsStream carga cualquier archivo o recurso mediante un flujo automatico
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		
		if(name.endsWith(".jpg"))
			return new ImageView(new Image(classloader.getResourceAsStream("images/" + name)));
		
		return new ImageView(new Image(classloader.getResourceAsStream("images/" + name + ".png")));
		
	}
	
	public static Image getImage(String name) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		var inputStream = classloader.getResourceAsStream("images/" + name);
		return new Image(inputStream);
		
		
	}

}

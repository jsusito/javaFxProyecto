package com.tokio.proyectofinal;


import java.io.IOException;

import com.tokio.proyectofinal.controller.Controller;
import com.tokio.proyectofinal.view.MainView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	public static MainView view;
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws IOException {
		
		view = new MainView(stage);
		Controller controller = new Controller(view);
		

//		//Para cuando la ruta esta en la carpeta de recursos
//		//Parent root = FXMLLoader.load(getClass().getResource("/com/AppView.fxml"));
//		
//		//Para indicar una ruta distinta.
//		//Parent root = FXMLLoader.load(App.class.getResource("view/View.fxml"));
		

	}

	@Override
	public void stop() throws Exception {

	}

}
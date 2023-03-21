package com.tokio.proyectofinal.view.center;

import com.tokio.proyectofinal.models.Heroe;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AppListViewHeroes extends VBox{

	public ListView<Heroe> heroesListView;
	
	private Label nameHeroes = new Label("Lista de Heroes");
	public FightButtons Topbuttons;
	private final boolean HEROES = true;
	public AppListViewHeroes() {
		super();
		initComponents();
	}
	private void initComponents() {
		
		Topbuttons = new FightButtons(HEROES);
		heroesListView = new ListView<>();
		heroesListView.setMaxSize(200, 250);
		heroesListView.setUserData("listViewHeroes");
		nameHeroes = new Label("Lista de Heroes");
		nameHeroes.setMinSize(20, 60);
		nameHeroes.setTextFill(Color.WHITE);
		nameHeroes.setFont(new Font("Arial", 24));
		getChildren().addAll(nameHeroes, heroesListView, Topbuttons);
	}
}

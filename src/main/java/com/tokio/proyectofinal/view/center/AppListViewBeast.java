package com.tokio.proyectofinal.view.center;

import com.tokio.proyectofinal.models.Beast;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AppListViewBeast extends VBox{
	public ListView<Beast> beastListView;
	Label nameBeast = new Label();
	public FightButtons topButtons;
	private final boolean HEROE = false;
	public AppListViewBeast() {
		super();
		initComponents();
	}
	private void initComponents() {
		topButtons = new FightButtons(HEROE);
		beastListView = new ListView<>();
		beastListView.setMaxSize(200, 250);
		beastListView.setUserData("listViewBeast");
		nameBeast = new Label("Lista de Bestias");
		nameBeast.setMinSize(20, 60);
		nameBeast.setTextFill(Color.WHITE);
		nameBeast.setFont(new Font("Arial", 24));
		getChildren().addAll(nameBeast, beastListView, topButtons);
	}
}

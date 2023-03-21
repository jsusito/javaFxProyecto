package com.tokio.proyectofinal.view.statusbar;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StatusBar extends VBox{
	
	public HBox buttonState;
	public Button fightButton;
	public Button fightStep;
	public Button fightContinue;
	public Button showTable;;
	public TextArea textArea;
	public StatusBar() {
		super();
		setPadding(new Insets(5, 5, 5, 5));
		setSpacing(20);
		initComponents();
	}

	private void initComponents() {
		HBox buttonState = new HBox();
		buttonState.setPadding(new Insets(5, 5, 5, 5));
		buttonState.setSpacing(20);
		fightStep = new Button("Por Etapas");
		fightStep.setUserData("fightStep");
		fightContinue = new Button(">>");
		fightContinue.setUserData("fightContinue");
		fightButton = new Button("¡LUCHA!");
		fightButton.setUserData("fight");
		showTable = new Button("Mostrar Tabla");
		showTable.setUserData("showTable");
		showTable.setVisible(false);
		textArea = new TextArea();
		textArea.setFont(Font.font(15));
		Separator separator = new Separator(Orientation.HORIZONTAL);
		separator.setMaxHeight(1);
		separator.setVisible(false);
		textArea.setMaxSize(500, 250);
		fightButton.setMaxSize(100, 20);
		buttonState.setAlignment(Pos.CENTER);
		buttonState.getChildren().addAll(fightButton, fightStep, fightContinue, showTable);
		setAlignment(Pos.CENTER);
		getChildren().addAll(separator,buttonState, textArea);
		
	}
}

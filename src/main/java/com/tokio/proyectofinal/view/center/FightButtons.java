package com.tokio.proyectofinal.view.center;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class FightButtons extends HBox{
	
	public final static String DOWN = "down";
	public final static String UP = "up";
	public final static String DELETE = "delete";
	
	public Button buttonUp;
	public  Button buttonDown;
	public Button buttonDelete;
	private final boolean HEROE;
	
	public FightButtons(boolean HEROE) {
		super();
		this.HEROE = HEROE;
		setPadding(new Insets(5, 5, 5, 5));
		setSpacing(20);
		initComponents();
	}

	private void initComponents() {
		buttonUp = new Button("Subir");
		buttonDown = new Button("Bajar");
		buttonDelete = new Button("Eliminar");
		
		if(HEROE) {
			buttonUp.setUserData("upHeroe");
			buttonDown.setUserData("downHeroe");
			buttonDelete.setUserData("deleteHeroe");
			}
		else {
			buttonUp.setUserData("upBeast");
			buttonDown.setUserData("downBeast");
			buttonDelete.setUserData("deleteBeast");
		}
		setAlignment(Pos.CENTER);
		getChildren().addAll(buttonUp, buttonDown, buttonDelete);
	}
}

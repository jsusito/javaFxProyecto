package com.tokio.proyectofinal.view.sides;

import com.tokio.proyectofinal.util.PersonalLabel;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class AddDataView<T> extends GridPane  {
private Label title, name, type, life, armor;
  public ComboBox<String> comboTextName;
  public Spinner<Integer> textLife;
  public Spinner<Integer> textArmor;
  public ComboBox<T> typePlayer;
  public Button addButton;
  private String titleText;
  private final Integer INICIAL_LIFE = 300;
  private final Integer INICIAL_ARMOR = 200;
  
  public AddDataView(String titleText) {
	  super();
	  this.titleText = titleText;
	  setPadding(new Insets(5, 5, 5, 5));
	  getColumnConstraints().add(new ColumnConstraints(85));
	  getColumnConstraints().add(new ColumnConstraints(85));
	  typePlayer = new ComboBox<>();
	  initComponents();

  }

private void initComponents() {
	title = PersonalLabel.getLabel(titleText, 24, "CircularStd-Medium.otf");

	name = PersonalLabel.getLabel("Nombre:", 14);
	type= PersonalLabel.getLabel("Tipo:", 14);
	life = PersonalLabel.getLabel("Vida:", 14);
	armor = PersonalLabel.getLabel("Armadura:", 14);
	comboTextName = new ComboBox<>();
	textLife = new Spinner<>(200, 700, INICIAL_LIFE, 10);
	textArmor = new Spinner<>(50, 400, INICIAL_ARMOR, 10);
	textLife.setUserData("spinnerLife");
	textArmor.setUserData("spinnerArmor");

	addButton = new Button("  Añadir  ");
	addButton.setMinSize(250, 40);
	setHgap(5); //espacio horizontal entre controles
	setVgap(2); //espacio vertical ....

	add(title, 0, 0,2,1);
	add(type, 0, 1);
	add(typePlayer, 1, 1);
	add(name, 0, 2);
	add(comboTextName, 1, 2);
	add(life, 0, 3);
	add(textLife, 1, 3);
	add(armor, 0, 4);
	add(textArmor, 1, 4);
	add(addButton, 0, 5, 3 , 3);
	}

	public void setInicialValue() {
		textLife.getEditor().setText(INICIAL_LIFE + "");
		textArmor.getEditor().setText(INICIAL_ARMOR + "");;
		textLife.getValueFactory().setValue(INICIAL_LIFE);
		textArmor.getValueFactory().setValue(INICIAL_ARMOR);
	}
}

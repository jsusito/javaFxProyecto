package com.tokio.proyectofinal.controller;

import com.tokio.proyectofinal.models.Beast;
import com.tokio.proyectofinal.models.Beast.WarrioBeasts;
import com.tokio.proyectofinal.models.Heroe;
import com.tokio.proyectofinal.models.Heroe.WarrioHeroe;
import com.tokio.proyectofinal.view.MainView;
import com.tokio.proyectofinal.view.center.FightButtons;
import com.tokio.proyectofinal.view.sides.AddDataView;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class Controller {
	private MainView view;
	private TextArea fightTextArea;
	private ControllerButtonListener buttonListener;

	public Controller(MainView view) {
		this.view = view;
		fightTextArea = getTextArea();
		buttonListener = new ControllerButtonListener(fightTextArea, this);
		
		initComponents();
		addButtonsListener();
		addComboBoxListener();
		addListViewListener();
		
	}

	private void initComponents() {
		// REllenamos los comboBox de los tipos de Rivales
		getTypeBeast().getItems().addAll(Beast.WarrioBeasts.values());
		getTypeHeroe().getItems().addAll(Heroe.WarrioHeroe.values());

		// Establecemos el userData de los botones de la vista
		addBeast().setUserData("addBeast");
		addHeroe().setUserData("addHeroe");
		getTypeHeroe().setUserData("heroeType");
		getTypeBeast().setUserData("beastType");
		getComboNameBeast().setUserData("beastName");
		getComboNameHeroe().setUserData("HeroeName");

		// Inicializamos cajas para evitar nulos
		getTypeBeast().getSelectionModel().select(0);
		getTypeHeroe().getSelectionModel().select(0);
		getComboNameBeast().getItems().addAll(Beast.nameOrcos);
		getComboNameHeroe().getItems().addAll(Heroe.nameHobbits);
		getComboNameBeast().getSelectionModel().select(0);
		getComboNameHeroe().getSelectionModel().select(0);
	}

	private void addButtonsListener() {
		
		getButtonFight().setOnAction(buttonListener);
		getButtonStep().setOnAction(buttonListener);
		getButtonContinue().setOnAction(buttonListener);
		getButtonShowTable().setOnAction(buttonListener);
		
		
		buttonOperationBeast().buttonDelete.setOnAction(buttonListener);
		buttonOperationBeast().buttonDown.setOnAction(buttonListener);
		buttonOperationBeast().buttonUp.setOnAction(buttonListener);
		buttonOperationHeroe().buttonDelete.setOnAction(buttonListener);
		buttonOperationHeroe().buttonDown.setOnAction(buttonListener);
		buttonOperationHeroe().buttonUp.setOnAction(buttonListener);
		addHeroe().setOnAction(buttonListener);
		addBeast().setOnAction(buttonListener);
		

	}

	private void addComboBoxListener() {
		getTypeHeroe().setOnAction(buttonListener);
		getTypeBeast().setOnAction(buttonListener);
		getComboNameBeast().setOnAction(buttonListener);
		getComboNameHeroe().setOnAction(buttonListener);
	}

	private void addListViewListener() {
		ControllerListViewAndSpinnerListener listener = new ControllerListViewAndSpinnerListener(this);
		getListViewHeroes().setOnMouseClicked(listener);
		getListViewBeast().setOnMouseClicked(listener);
		getListViewHeroes().setOnKeyPressed(listener);
		getListViewBeast().setOnKeyPressed(listener);
	}
	
	public String getLiveBeast() {
		return view.addDataBeast.textLife.getEditor().getText();
	}
	
	public String getLiveHeroe() {
		return view.addDataHeroes.textLife.getValue().toString();
	}
	
	public String getArmorBeast() {
		return view.addDataBeast.textArmor.getEditor().getText();
	}
	
	public String getArmorHeroe() {
		return view.addDataHeroes.textArmor.getValue().toString();
	}
	
	public ComboBox<String> getComboNameBeast() {
		return view.addDataBeast.comboTextName;
	}

	public ComboBox<String> getComboNameHeroe() {
		return view.addDataHeroes.comboTextName;
	}

	public Button addHeroe() {
		return view.addDataHeroes.addButton;
	}

	public Button addBeast() {
		return view.addDataBeast.addButton;
	}

	public ListView<Heroe> getListViewHeroes() {
		return view.ListAndImage.listViewHeroes.heroesListView;
	}

	public ListView<Beast> getListViewBeast() {
		return view.ListAndImage.listViewBeast.beastListView;
	}
	
	public ImageView getImageBeast() {
		return view.ListAndImage.beastImage;
	}
	
	public ImageView getImageHeroe() {
		return view.ListAndImage.heroesImage ;
	}

	public ComboBox<Beast.WarrioBeasts> getTypeBeast() {
		return view.addDataBeast.typePlayer;
	}

	public ComboBox<Heroe.WarrioHeroe> getTypeHeroe() {
		return view.addDataHeroes.typePlayer;
	}

	public Button getButtonFight() {
		return view.statusBar.fightButton;
	}
	
	public Button getButtonStep() {
		return view.statusBar.fightStep;
	}
	
	public AddDataView<WarrioBeasts> getDataViewBeast(){
		return view.addDataBeast;
	}
	
	public AddDataView<WarrioHeroe> getDataViewHeroe(){
		return view.addDataHeroes;
	}
	
	private Button getButtonContinue() {
		return view.statusBar.fightContinue;
	}

	private TextArea getTextArea() {
		return view.statusBar.textArea;

	}

	private FightButtons buttonOperationBeast() {
		return view.ListAndImage.listViewBeast.topButtons;
	}

	private FightButtons buttonOperationHeroe() {
		return view.ListAndImage.listViewHeroes.Topbuttons;
	}
	public Button getButtonShowTable() {
		return view.statusBar.showTable;
	}
	
	

}

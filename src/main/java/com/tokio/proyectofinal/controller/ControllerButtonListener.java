package com.tokio.proyectofinal.controller;

import static com.tokio.proyectofinal.view.center.FightButtons.DELETE;
import static com.tokio.proyectofinal.view.center.FightButtons.DOWN;
import static com.tokio.proyectofinal.view.center.FightButtons.UP;

import com.tokio.proyectofinal.App;
import com.tokio.proyectofinal.dao.Dao;
import com.tokio.proyectofinal.models.Beast;
import com.tokio.proyectofinal.models.Heroe;
import com.tokio.proyectofinal.util.ImageUtil;
import com.tokio.proyectofinal.view.AppTableView;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerButtonListener implements EventHandler<ActionEvent>{

	private Dao dao;
	private TextArea fightTextArea;
	private Controller controller;
		
	public ControllerButtonListener(TextArea fightTextArea, Controller controller) {
		this.controller = controller;
		this.fightTextArea = fightTextArea;
		 
		}

	@Override
	public void handle(ActionEvent event) {
				
		Node node = (Node) event.getSource();
		String userData = node.getUserData().toString();
		switch (userData) {
		
		//Cuando el usuario quiere ver por etapas iniciamos un multihilo para que el usuario cuando pulse en boton fightcontinue
		//la tarea que se repite cada 100ms lea que cambia la variable roundContinue y acabe de golpe sin más pasos.
		case "fightStep":
			ControllerRoundsBattles.roundStep = true;
			App.view.statusBar.fightStep.setDisable(true);
			Task<Void> task = new Task<>( ) {
				@Override
				protected Void call() throws Exception {
					initBattles();
					return null;
				} 
				
			};
			new Thread(task).start();
			break;
		
		case "fight":
			fight();
			break;
		case "fightContinue":
			ControllerRoundsBattles.roundContinue = true;
			break;
		case "upBeast":
			 changeBeastPosition(UP);
			break;
		case "upHeroe":
			changeHeroePosition(UP);
			break;
		case "downBeast":
			changeBeastPosition(DOWN);
			break;
		case "downHeroe":
			changeHeroePosition(DOWN);
			break;
		case "deleteBeast":
			changeBeastPosition(DELETE);
			break;
		case "deleteHeroe":
			changeHeroePosition(DELETE);
			break;
		case "addHeroe":
			addHeroe();
			break;
		case "addBeast":
			addBeast();
			break;
		case "heroeType":
			changeListHeroeType();
			break;
		case "beastType":
			changeListBeastType();
			break;
		case "beastName":
			changeImageBeast();
			break;
		case "HeroeName":
			changeImageHeroe();
			break;
		case "showTable":
			showTable();
			break;
		}
		
	}

	private void showTable() {
		AppTableView table = new AppTableView();
		table.show(ControllerRoundsBattles.results);
	}

	private void initBattles() {
		dao = new Dao();
		getItemBeast().forEach(beast -> dao.addBeast(beast));
		getItemHeroe().forEach(Heroe -> dao.addHeroe(Heroe));
		ControllerRoundsBattles roundBattles = new ControllerRoundsBattles(dao, fightTextArea);;
		roundBattles.fightTextArea.clear();
		roundBattles.init();
		App.view.statusBar.fightStep.setDisable(false);
		controller.getButtonShowTable().setVisible(true);
	}
	
	private void fight() {
		//Si anteriormente se le ha dado por  step al pulsar este boton acaba del todo la partida anterior
		if(ControllerRoundsBattles.roundStep) {
			ControllerRoundsBattles.roundContinue = true;
			ControllerRoundsBattles.roundStep = false;
		}
		else {
			ControllerRoundsBattles.roundStep = false;
			initBattles();
		}
	}

	private void changeImageBeast() {
	
		String name = controller.getComboNameBeast().getSelectionModel().getSelectedItem();
		if(name == null )
			return;
		name = name.concat(".png");
		Image image = ImageUtil.getImage(name);
		controller.getImageBeast().setImage(image);
	}

	private void changeImageHeroe() {
		String name = controller.getComboNameHeroe().getSelectionModel().getSelectedItem();
		if(name == null )
			return;
		name = name.concat(".png");
		Image image = ImageUtil.getImage(name);
		controller.getImageHeroe().setImage(image);
	}

	private void changeListBeastType() {
		final String LIVE_SAURON = "5000";
		final String ARMOR_SAURON = "1000";
		
		controller.getComboNameBeast().getItems().clear();
		String typePlayer = controller.getTypeBeast().getSelectionModel().getSelectedItem().toString();
		controller.getDataViewBeast().setInicialValue();
				
		if(typePlayer.equals("ORCOS")) {
			controller.getComboNameBeast().getItems().addAll(Beast.nameOrcos);
		}
		else if(typePlayer.equals("TRASGOS")) {
			controller.getComboNameBeast().getItems().addAll(Beast.nameTrasgos);
		}
		else if(typePlayer.equals("SAURON")) {
			controller.getDataViewBeast().textLife.getEditor().setText(LIVE_SAURON);
			controller.getDataViewBeast().textArmor.getEditor().setText(ARMOR_SAURON);
			controller.getComboNameBeast().getItems().addAll(Beast.nameSauron);
			
						
		}
		controller.getComboNameBeast().getSelectionModel().select(0);
	}

	private void changeListHeroeType() {
		controller.getComboNameHeroe().getItems().clear();
		String typePlayer = controller.getTypeHeroe().getSelectionModel().getSelectedItem().toString();
		controller.getDataViewHeroe().setInicialValue();
		
		if(typePlayer.equals("HOBBIT")) {
			controller.getComboNameHeroe().getItems().addAll(Heroe.nameHobbits);
		}
		else if(typePlayer.equals("ELFO")) {
			controller.getComboNameHeroe().getItems().addAll(Heroe.nameElfos);
		}
		else if(typePlayer.equals("HUMAN")) {
			controller.getComboNameHeroe().getItems().addAll(Heroe.nameHumans);
		}
		controller.getComboNameHeroe().getSelectionModel().select(0);
	}

	private void addBeast() {
		String name = controller.getComboNameBeast().getSelectionModel().getSelectedItem();
		String temp =  controller.getLiveBeast();
		int life = Integer.parseInt(temp);
		temp = controller.getArmorBeast();
		int armor = Integer.parseInt(temp);
		Beast.WarrioBeasts warrior = controller.getTypeBeast().getValue();
		ImageView image = controller.getImageBeast();
		Beast beast = new Beast(name, life, armor, image, warrior);
		controller.getListViewBeast().getItems().add(beast);
		controller.getDataViewBeast().setInicialValue();
	}

	private void addHeroe() {
		String name = controller.getComboNameHeroe().getSelectionModel().getSelectedItem();
		String temp =  controller.getLiveHeroe();
		int life = Integer.parseInt(temp);
		temp = controller.getArmorHeroe();
		int armor = Integer.parseInt(temp);
		Heroe.WarrioHeroe warrior = controller.getTypeHeroe().getValue();
		ImageView image = controller.getImageHeroe();
		Heroe heroe = new Heroe(name, life, armor, image, warrior);
		controller.getListViewHeroes().getItems().add(heroe);
		controller.getDataViewHeroe().setInicialValue();
	}

	private void changeBeastPosition(String changeAccion) {
	
		int i = getIndexBeast();
		
		if(i < 0 )
			return;
		if(changeAccion == UP) {
			if(i == 0) 
				return;
			Beast auxBeast = getItemBeast().get(i - 1);
			Beast beast = getItemBeast().get(i);
			getItemBeast().set(i - 1, beast);
			getItemBeast().set(i, auxBeast);
			controller.getListViewBeast().getSelectionModel().select(i - 1);
		}
		if(changeAccion == DOWN) {
			if(i == getItemBeast().size() - 1) 
				return;
			Beast auxBeast = getItemBeast().get(i + 1);
			Beast beast = getItemBeast().get(i);
			getItemBeast().set(i + 1, beast);
			getItemBeast().set(i, auxBeast);
			controller.getListViewBeast().getSelectionModel().select(i + 1);
		}
		if(changeAccion == DELETE) {
			getItemBeast().remove(i);
			
		}
		
	}
	
	private void changeHeroePosition(String changeAccion) {
		
		int i = getIndexHeroe();
		
		if(i < 0 )
			return;
		if(changeAccion == UP) {
			if(i == 0) 
				return;
			Heroe auxHeroe = getItemHeroe().get(i - 1);
			Heroe heroe = getItemHeroe().get(i);
			getItemHeroe().set(i - 1, heroe);
			getItemHeroe().set(i, auxHeroe);
			controller.getListViewHeroes().getSelectionModel().select(i - 1);
		}
		if(changeAccion == DOWN) {
			if(i == getItemHeroe().size() - 1) 
				return;
			Heroe auxHeroe = getItemHeroe().get(i + 1);
			Heroe beast = getItemHeroe().get(i);
			getItemHeroe().set(i + 1, beast);
			getItemHeroe().set(i, auxHeroe);
			controller.getListViewHeroes().getSelectionModel().select(i + 1);
		}
		if(changeAccion == DELETE) {
			getItemHeroe().remove(i);
			
		}
		
	}
	private int getIndexBeast() {
		return controller.getListViewBeast().getSelectionModel().getSelectedIndex();
	}
	private ObservableList<Beast> getItemBeast(){
		return controller.getListViewBeast().getItems();
	}
	private int getIndexHeroe() {
		return controller.getListViewHeroes().getSelectionModel().getSelectedIndex();
	}
	private ObservableList<Heroe> getItemHeroe(){
		return controller.getListViewHeroes().getItems();
	}
}

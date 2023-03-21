package com.tokio.proyectofinal.controller;

import com.tokio.proyectofinal.util.ImageUtil;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;

public class ControllerListViewAndSpinnerListener implements EventHandler<InputEvent> {

	private Controller controller;

	public ControllerListViewAndSpinnerListener(Controller controller) {
		super();
		this.controller = controller;
	}

	//Sirven para que cuando el usuario valla desplazandose en las ListView se vallan cambiando las imagenes
	@Override
	public void handle(InputEvent event) {
		Node node = (Node) event.getSource();
		String nameListView = node.getUserData().toString();
		switch (nameListView) {
		case "listViewHeroes":
			if (controller.getListViewHeroes().getSelectionModel().isEmpty())
				return;
			String name = controller.getListViewHeroes().getSelectionModel().getSelectedItem().getName();
			name = name.concat(".png");
			Image image = ImageUtil.getImage(name);
			controller.getImageHeroe().setImage(image);
			break;
		case "listViewBeast":
			if (controller.getListViewBeast().getSelectionModel().isEmpty())
				return;
			String nameBeast = controller.getListViewBeast().getSelectionModel().getSelectedItem().getName();
			if (nameBeast == null)
				return;
			nameBeast = nameBeast.concat(".png");
			Image imageBeast = ImageUtil.getImage(nameBeast);
			controller.getImageBeast().setImage(imageBeast);
			break;
		}
	}

}

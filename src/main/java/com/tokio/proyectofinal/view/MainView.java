package com.tokio.proyectofinal.view;



import com.tokio.proyectofinal.models.Beast;
import com.tokio.proyectofinal.models.Heroe;
import com.tokio.proyectofinal.util.ImageUtil;
import com.tokio.proyectofinal.view.center.AppListViewAndImage;
import com.tokio.proyectofinal.view.sides.AddDataView;
import com.tokio.proyectofinal.view.statusbar.StatusBar;
import com.tokio.proyectofinal.view.top.TopPanel;

import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class MainView{
	public BorderPane borderPane;;
	private Image imageMap;
	private Stage stage;
	public AppListViewAndImage ListAndImage;
	public StatusBar statusBar;
	public TopPanel topPanel;
	public AddDataView<Heroe.WarrioHeroe> addDataHeroes;
	public AddDataView<Beast.WarrioBeasts> addDataBeast;
	
	public MainView(Stage stage) {
		super();
		this.stage = stage;
		borderPane = new BorderPane();
		
		
		initComponents();
	}

	private void initComponents() {
		borderPane = new BorderPane();
		ListAndImage = new AppListViewAndImage();
		statusBar = new StatusBar();
		topPanel = new TopPanel();
		addDataHeroes = new AddDataView<>("HEROES");
		addDataBeast = new AddDataView<>("BESTIAS");
				
		imageMap = ImageUtil.getImage("mapa.jpg");
		Scene scene = new Scene(borderPane, 1300, 800);
		
		BackgroundImage bimg= new BackgroundImage(imageMap, BackgroundRepeat.NO_REPEAT, 
		BackgroundRepeat.NO_REPEAT,	BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background backGround = new Background(bimg);
		borderPane.setBackground(backGround);
		stage.setTitle("Batalla por la tierra media");
		borderPane.setTop(topPanel);
		borderPane.setLeft(addDataHeroes);
		borderPane.setRight(addDataBeast);
		borderPane.setBottom(statusBar);
		borderPane.setCenter(ListAndImage);
		
		stage.setMaxHeight(800);
		stage.setScene(scene);
		stage.show();
		
	}	
	
	
	
	
}

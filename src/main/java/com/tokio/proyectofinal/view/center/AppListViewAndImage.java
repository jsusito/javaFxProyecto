package com.tokio.proyectofinal.view.center;

import com.tokio.proyectofinal.util.ImageUtil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AppListViewAndImage extends HBox{
	public AppListViewHeroes listViewHeroes;
	public AppListViewBeast  listViewBeast;
	public ImageView heroesImage, beastImage;
		
	public AppListViewAndImage() {
		super();
		setPadding(new Insets(5, 5, 5, 5));
		setSpacing(10);
		initComponents();
	}

	private void initComponents() {
				
		listViewHeroes = new AppListViewHeroes();
		listViewBeast = new AppListViewBeast();
		heroesImage = new ImageView();
		beastImage = new ImageView();
		heroesImage.setImage(ImageUtil.getImage("frodo.png"));
		beastImage.setImage(ImageUtil.getImage("bolg.png"));
        getChildren().addAll( listViewHeroes, heroesImage, beastImage, listViewBeast);
        setAlignment(Pos.CENTER);
	}
}

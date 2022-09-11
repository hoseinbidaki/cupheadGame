package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Die extends Rectangle {
    private Pane pane;
    private Image image = new Image(Main.class.getResource(
            "Pic/Game/models/EggExplosionAssets/0.png").toExternalForm());

    public Die(Pane pane) {
        super(850, 300, 267, 250);
        this.pane = pane;
        this.setFill(new ImagePattern(image));
    }

    public void setImage(Image im) {
        this.image = im;
        this.setFill(new ImagePattern(image));
    }
}

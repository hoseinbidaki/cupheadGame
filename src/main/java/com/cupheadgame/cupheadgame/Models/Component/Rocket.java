package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Rocket extends Rectangle {
    private Image a1 = new Image(Main.class.getResource(
            "Pic/Game/models/rocket/1.png").toExternalForm());
    private Pane pane;
    public Rocket(Pane pane) {
        super(Airplane.getAirplane().getX() + 70, Airplane.getAirplane().getY() + 50, 80, 55);
        this.pane = pane;
        this.setFill(new ImagePattern(a1));
    }

    public void moveRight() {
        if (!hitRightWall()) {
            this.setX(this.getX() + 15);
        }
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 1400;
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(rectangle.getLayoutBounds());
    }

    public void remove() {
        this.pane.getChildren().remove(this);
    }
}

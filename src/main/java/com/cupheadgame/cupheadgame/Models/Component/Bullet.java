package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    private Image normal = new Image(Main.class.getResource(
            "Pic/Game/models/bullet/bullet.png").toExternalForm());

    private Pane pane;

    public Bullet(Pane pane) {
        super(Airplane.getAirplane().getX() + 70, Airplane.getAirplane().getY() + 50, 72, 15);
        this.pane = pane;
    }

    public void moveRight() {
        if (!hitRightWall()) {
            this.setFill(new ImagePattern(normal));
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

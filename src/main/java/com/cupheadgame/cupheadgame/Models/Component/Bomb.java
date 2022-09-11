package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bomb extends Rectangle {
    private Image bomb = new Image(Main.class.getResource(
            "Pic/Game/models/bullet/Bomb.png").toExternalForm());
    public Bomb() {
        super(Airplane.getAirplane().getX() + 70,
                Airplane.getAirplane().getY() + 50, 50, 50);
        this.setFill(new ImagePattern(bomb));
    }

    public void moveDown() {
        if (!hitFloor()) {
            this.setY(this.getY() + 15);
        }
    }
    public boolean hitFloor() {
        return this.getY() + this.getHeight() >= 800;
    }
    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(rectangle.getLayoutBounds());
    }
}

package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Airplane extends Rectangle {

    private static Airplane airplane;
    public static Airplane getAirplane() {
        if (airplane == null) {
            airplane = new Airplane();
        }
        return airplane;
    }
    private Image normal = new Image(Main.class.getResource(
            "Pic/Game/models/airplane/normal.png").toExternalForm());

    private Image up = new Image(Main.class.getResource(
            "Pic/Game/models/airplane/up.png").toExternalForm());

    private Image down = new Image(Main.class.getResource(
            "Pic/Game/models/airplane/down.png").toExternalForm());
    public Airplane() {
        super(220, 220, 121, 114);
        this.setFill(new ImagePattern(normal));
    }

    public void moveRight() {
        if (!hitRightWall()) {
            this.setFill(new ImagePattern(normal));
            this.setX(this.getX() + 15);
        }
    }

    public void moveLeft() {
        if (!hitLeftWall()) {
            this.setX(this.getX() - 15);
            this.setFill(new ImagePattern(normal));
        }
    }

    public void moveUp() {
        if (!hitTop()){
            this.setFill(new ImagePattern(up));
            this.setY(this.getY() - 15);
        }
    }

    public void moveDown() {
        if (!hitFloor()) {
            this.setFill(new ImagePattern(down));
            this.setY(this.getY() + 15);
        }
    }
    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 1400;
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }

    public boolean hitTop() {
        return this.getY() <= 0;
    }

    public boolean hitFloor() {
        return this.getY() + this.getHeight() >= 800;
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(rectangle.getLayoutBounds());
    }
}

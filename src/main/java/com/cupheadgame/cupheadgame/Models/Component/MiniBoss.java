package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Transitions.MiniBossTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniBoss extends Rectangle {
    public static ArrayList<MiniBoss> miniBosses = new ArrayList<>();
    public MiniBossTransition miniBossTransition;

    public static ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }

    private Pane pane;
    private Image image = new Image(
            Main.class.getResource("Pic/Game/models/miniBoss/1.png").toExternalForm());
    public MiniBoss(Pane pane, int x, int y) {
        super(x, y, 159, 109);
        this.pane = pane;
        this.setFill(new ImagePattern(image));
        miniBossTransition = new MiniBossTransition(this);
        miniBosses.add(this);
    }

    public void setMiniBossTransition(MiniBossTransition miniBossTransition) {
        this.miniBossTransition = miniBossTransition;
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(rectangle.getLayoutBounds());
    }

    public void remove() {
        pane.getChildren().remove(this);
        MiniBoss.getMiniBosses().remove(this);
    }

    public void moveLeft() {
        if (!hitLeftWall()) {
            this.setX(this.getX() - 0.75);
        }
    }
    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }

    public MiniBossTransition getMiniBossTransition() {
        return miniBossTransition;
    }
}

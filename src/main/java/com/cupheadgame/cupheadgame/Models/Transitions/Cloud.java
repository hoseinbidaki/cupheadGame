package com.cupheadgame.cupheadgame.Models.Transitions;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Cloud extends Transition {
    private ImageView imageView;
    public Cloud(ImageView imageView) {
        this.imageView = imageView;
        imageView.setY(200);
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        imageView.setX(imageView.getX() - 0.5);
        if (imageView.getX() <= -610) {
            this.stop();
            imageView.setX(0);
            this.play();
        }
    }
}

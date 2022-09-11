package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Boss;
import com.cupheadgame.cupheadgame.Models.Component.Rocket;
import com.cupheadgame.cupheadgame.Models.Game;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class RocketTransition extends Transition {
    private Rocket rocket;
    private Pane pane;

    private boolean getScore = false;
    private Image image[] = {
            new Image(Main.class.getResource(
                    "Pic/Game/models/rocket/1.png").toExternalForm()),
            new Image(Main.class.getResource(
                    "Pic/Game/models/rocket/2.png").toExternalForm()),
            new Image(Main.class.getResource(
                    "Pic/Game/models/rocket/3.png").toExternalForm())
    };

    public RocketTransition(Rocket rocket, Pane pane) {
        this.rocket = rocket;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        rocket.moveRight();
        int id = (int) (v * 2);
        rocket.setFill(new ImagePattern(image[id]));
        if (!getScore && Boss.getInstance().hasCollision(rocket)) {
            Boss.getInstance().setHeal(
                    Boss.getInstance().getHeal() - 100
            );
            getScore = true;
            Game.getGame().incScore(10);
            this.setCycleDuration(Duration.millis(0));
            this.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    pane.getChildren().remove(rocket);
                }
            });
        }
    }
}

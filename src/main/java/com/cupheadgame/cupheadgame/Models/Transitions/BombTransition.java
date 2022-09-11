package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Models.Component.Bomb;
import com.cupheadgame.cupheadgame.Models.Component.Boss;
import com.cupheadgame.cupheadgame.Models.Game;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BombTransition extends Transition {
    private Bomb bomb;
    private Pane pane;
    private boolean getScore = false;

    public BombTransition(Bomb bomb, Pane pane) {
        this.bomb = bomb;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(600));
    }
    @Override
    protected void interpolate(double v) {
        bomb.moveDown();
        if (!getScore && bomb.hasCollision(Boss.getInstance())) {
            Boss.getInstance().setHeal(
                    Boss.getInstance().getHeal() - 10
            );
            getScore = true;
            Game.getGame().incScore(+2);
            this.setCycleDuration(Duration.millis(0));
            this.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    pane.getChildren().remove(bomb);
                }
            });
        }

    }
}

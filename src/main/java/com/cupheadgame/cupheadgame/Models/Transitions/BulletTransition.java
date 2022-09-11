package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Models.Component.Boss;
import com.cupheadgame.cupheadgame.Models.Component.Bullet;
import com.cupheadgame.cupheadgame.Models.Component.MiniBoss;
import com.cupheadgame.cupheadgame.Models.Game;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BulletTransition extends Transition {

    private Bullet bullet;
    private Pane pane;
    private boolean getScore = false;
    public BulletTransition(Bullet bullet, Pane pane) {
        this.bullet = bullet;
        this.pane = pane;
        this.setCycleDuration(Duration.millis(700));
    }
    @Override
    protected void interpolate(double v) {
        bullet.moveRight();
        if (!getScore && Boss.getInstance().hasCollision(bullet)) {
            Boss.getInstance().setHeal(
                    Boss.getInstance().getHeal() - 5
            );
            Game.getGame().incScore(1);
            getScore = true;
            this.setCycleDuration(Duration.millis(0));
        }
        for (MiniBoss miniBoss : MiniBoss.miniBosses) {
            Game.getGame().incScore(1);
            if (miniBoss.hasCollision(bullet)) {
//                miniBoss.remove();
                Game.getGame().incScore(2);
                break;
            }
        }
    }
}


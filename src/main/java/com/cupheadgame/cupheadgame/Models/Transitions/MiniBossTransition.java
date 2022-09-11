package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.MiniBoss;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

public class MiniBossTransition extends Transition {

    private Image images[] = {
            new Image(
                    Main.class.getResource("Pic/Game/models/miniBoss/1.png").toExternalForm()),
            new Image(
                    Main.class.getResource("Pic/Game/models/miniBoss/2.png").toExternalForm()),
            new Image(
                    Main.class.getResource("Pic/Game/models/miniBoss/3.png").toExternalForm()),
            new Image(
                    Main.class.getResource("Pic/Game/models/miniBoss/4.png").toExternalForm())
    };

    private MiniBoss miniBoss;

    public MiniBossTransition(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                miniBoss.remove();
            }
        });
    }
    @Override
    protected void interpolate(double v) {
        int id = (int) (v * 3);
        miniBoss.setFill(new ImagePattern(images[id]));
        if (miniBoss.hitLeftWall()) {
            miniBoss.remove();
            return;
        }
        miniBoss.moveLeft();
    }
}

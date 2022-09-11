package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Die;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class DieTransition extends Transition {
    private Die die;

    private Image images [] = {
            new Image(Main.class.getResource(
                    "Pic/Game/models/EggExplosionAssets/0.png").toExternalForm()),
            new Image(Main.class.getResource(
                    "Pic/Game/models/EggExplosionAssets/1.png").toExternalForm()),
            new Image(Main.class.getResource(
                    "Pic/Game/models/EggExplosionAssets/2.png").toExternalForm()),
            new Image(Main.class.getResource(
                    "Pic/Game/models/EggExplosionAssets/3.png").toExternalForm())
    };
    public DieTransition(Die die) {
        this.die = die;
        this.setCycleDuration(Duration.millis(2500));
    }
    @Override
    protected void interpolate(double v) {
        int id = (int) (v * 3);
        die.setImage(images[id]);
    }
}

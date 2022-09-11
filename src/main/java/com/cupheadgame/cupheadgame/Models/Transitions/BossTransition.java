package com.cupheadgame.cupheadgame.Models.Transitions;

import com.cupheadgame.cupheadgame.Controller.GameController;
import com.cupheadgame.cupheadgame.Controller.GamePanel;
import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.Boss;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.Game;
import com.cupheadgame.cupheadgame.Models.Timer;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class BossTransition extends Transition {
    private Boss boss;
    private GameController gameController;
    private Image bosses[] =
            {
                    new Image(Main.class.getResource("Pic/Game/models/boss/1.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/2.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/3.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/4.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/5.png").toExternalForm()),
                    new Image(Main.class.getResource("Pic/Game/models/boss/6.png").toExternalForm())
            };
    public BossTransition(Boss boss, GameController gameController) {
        this.boss = boss;
        this.gameController = gameController;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);

    }

    private int id = 1;
    private int pre = 0;
    @Override
    protected void interpolate(double v) {
        if (boss.getHeal() <= 0) {
            User user = Database.getInstance().getLoggedInUser();
            int score = Game.getGame().getScore();
            user.setScore(
                    Math.max(
                            user.getScore(), score
                    )
            );
            Game.getGame().setTime(
                    Timer.getTimer().getSecs()
            );
            Database.getInstance().getGames().add(Game.getGame());
            Database.getInstance().saveData();
            Database.getInstance().saveGameData();
            this.stop();
            boss.remove();
            try {
                gameController.setDie(gameController.p);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                gameController.audioClip.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        id = (int) (v * 5);
        Game.getGame().incRocket();
        Timer.getTimer().incSec();
        boss.setImage(bosses[id]);
        gameController.updateRocket();
        gameController.updateScore();
        gameController.timerUpdate();
        gameController.UpdateBossHeal();
        if (Timer.getTimer().getS() > pre) {
            pre += 35;
            gameController.setMiniBoss(gameController.p);
        }
    }
}

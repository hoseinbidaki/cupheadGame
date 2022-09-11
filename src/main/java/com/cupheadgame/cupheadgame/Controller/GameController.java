package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Component.*;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.Game;
import com.cupheadgame.cupheadgame.Models.Timer;
import com.cupheadgame.cupheadgame.Models.Transitions.*;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class GameController extends Application {
    private Label label = new Label("");
    private Label rocket = new Label("");

    private Label timer = new Label("");

    private Label bossHeal = new Label("");
    private Image currentBomb = new Image(Main.class.getResource(
            "Pic/Game/models/BulletLogos/BulletLogo.png").toExternalForm());

    private Image muteImg = new Image(Main.class.getResource(
            "Pic/mute.png").toExternalForm());

    private Image tRocket = new Image(Main.class.getResource(
            "Pic/Game/models/rocket/t.png").toExternalForm());

    private Image rRocket = new Image(Main.class.getResource(
            "Pic/Game/models/rocket/r.png").toExternalForm());
    private Rectangle currentBombShape = new Rectangle(5, 700, 66, 66);
    private Rectangle rocketIcon = new Rectangle(139, 690, 127, 76);

    private Rectangle exit = new Rectangle(265, 700, 66, 66);
    private boolean bomb = true;
    private boolean firstAlet = false;

    private boolean mute = false;
    private AudioClip bultAudioSound = new AudioClip(
            Main.class.getResource("Musics/bult.mp3").toExternalForm()
    );

    private AudioClip bombAudioSound = new AudioClip(
            Main.class.getResource("Musics/bomb.mp3").toExternalForm()
    );

    public AudioClip audioClip = new
            AudioClip(Main.class.getResource("Musics/1.mp3").toExternalForm());

    private AudioClip rocketSound = new
            AudioClip(Main.class.getResource("Musics/rocket.mp3").toExternalForm());

    private AudioClip emptySound = new
            AudioClip(Main.class.getResource("Musics/empty.mp3").toExternalForm());

    public Pane p;

    private Die die;
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(Main.class.getResource("GameMenu.fxml"));
        p = pane;
        createBackground(pane);
        createAirplane(pane);
        setBulletLogos();
        setScoreLabel(pane);
        setRocketLabel(pane);
        setTimerLabel(pane);
        setBoss(pane);
        //setDie(pane);
        setMute(pane);
        setRocketIcon(pane);
        setExitButton(pane);
        setBossHeal(pane);
        pane.getChildren().add(currentBombShape);
        Scene scene = new Scene(pane);
        stage.setTitle("cupheadGame");
        stage.setScene(scene);
        stage.setResizable(false);
        pane.getChildren().get(2).requestFocus();
        stage.centerOnScreen();
        //stage.setFullScreen(true);
        stage.show();
    }
    public void initialize() {
        audioClip.setCycleCount(-1);
        if (!mute)
            audioClip.play();
        Game.newGame(Database.getInstance().getLoggedInUser());
    }

    private void createBackground(Pane pane) {
        Image image = new Image(
                Main.class.getResourceAsStream("Pic/Game/Background/birdhouse_bg_0008.png"));
        pane.getChildren().add(new ImageView(image));
        Image cloud = new Image(
                Main.class.getResourceAsStream("Pic/Game/Background/birdhouse_bg_0006.png"));
        ImageView cloudView = new ImageView(cloud);
        pane.getChildren().add(cloudView);
        new Cloud(cloudView).play();
    }

    private void createAirplane(Pane pane) {
        Airplane airplane = Airplane.getAirplane();
        airplane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.LEFT)
                    || keyEvent.getCode().equals(KeyCode.A)) {
                    airplane.moveLeft();
                }
                else if (keyEvent.getCode().equals(KeyCode.RIGHT)
                    || keyEvent.getCode().equals(KeyCode.D)) {
                    airplane.moveRight();
                }
                else if (keyEvent.getCode().equals(KeyCode.UP)
                    || keyEvent.getCode().equals(KeyCode.W)) {
                    airplane.moveUp();
                }
                else if (keyEvent.getCode().equals(KeyCode.DOWN)
                    || keyEvent.getCode().equals(KeyCode.S)) {
                    airplane.moveDown();
                }
                else if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    if (bomb) {
                        Bullet bullet = new Bullet(pane);
                        pane.getChildren().add(bullet);
                        BulletTransition bulletTransition = new BulletTransition(bullet, pane);
                        bulletTransition.play();
                        if (!mute)
                            bultAudioSound.play();
                        bulletTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                pane.getChildren().remove(bullet);
                            }
                        });
                    }
                    else {
                        Bomb bmb = new Bomb();
                        pane.getChildren().add(bmb);
                        BombTransition bombTransition = new BombTransition(bmb, pane);
                        bombTransition.play();
                        if (!mute)
                            bombAudioSound.play();
                        bombTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                pane.getChildren().remove(bmb);
                            }
                        });
                    }
                }
                else if (keyEvent.getCode().equals(KeyCode.TAB)) {
                    if (bomb) {
                        bomb = false;
                        currentBomb = new Image(Main.class.getResource(
                                "Pic/Game/models/BulletLogos/BombLogo.png").toExternalForm());
                    }
                    else {
                        bomb = true;
                        currentBomb = new Image(Main.class.getResource(
                                "Pic/Game/models/BulletLogos/BulletLogo.png").toExternalForm());
                    }
                    currentBombShape.setFill(new ImagePattern(currentBomb));
                }
                else if (keyEvent.getCode().equals(KeyCode.CONTROL)) {
                    if (Game.getGame().getRocket() >= 100) {
                        if (!mute)
                            rocketSound.play();
                        Game.getGame().setRocket(0);
                        Rocket r = new Rocket(pane);
                        pane.getChildren().add(r);
                        RocketTransition rocketTransition = new RocketTransition(r, pane);
                        rocketTransition.play();
                        rocketTransition.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                pane.getChildren().remove(r);
                            }
                        });
                    }
                    else {
                        if (!mute)
                            emptySound.play();
                    }
                }

            }
        });
        pane.getChildren().add(airplane);
    }

    private void setBulletLogos() {
        currentBombShape.setFill(new ImagePattern(currentBomb));
    }

    private void setScoreLabel(Pane pane) {
        label.setText("score: " + Game.getGame().getScore());
        label.setTranslateX(1250);
        label.setTranslateY(730);
        label.setFont(new Font(18));
        pane.getChildren().add(label);
    }

    private void setRocketLabel(Pane pane) {
        rocket.setText("rocket: " + Game.getGame().getRocket() + "%");
        rocket.setTranslateX(1250);
        rocket.setTranslateY(700);
        rocket.setFont(new Font(18));
        pane.getChildren().add(rocket);
    }

    private void setTimerLabel(Pane pane) {
        timer.setText(Timer.getTimer().getM()+" : " + Timer.getTimer().getS());
        timer.setTranslateX(50);
        timer.setTranslateY(5);
        timer.setFont(new Font(18));
        pane.getChildren().add(timer);
    }

    public void timerUpdate() {
        timer.setText(Timer.getTimer().getM()+" : " + Timer.getTimer().getS());
    }
    public void updateRocket() {
        String percent = String.valueOf(
                Math.round(Game.getGame().getRocket()));

        rocket.setText("rocket: " + percent + "%");
        if (Game.getGame().getRocket() >= 100) {
            rocket.setTextFill(Color.BLUE);
            rocketIcon.setFill(new ImagePattern(tRocket));
            if (!firstAlet) {
                firstAlet = true;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You can shoot rocket with press Ctrl!");
                alert.show();
            }
        }
        else {
            rocket.setTextFill(Color.BLACK);
            rocketIcon.setFill(new ImagePattern(rRocket));
        }
    }
    public void updateScore() {
        label.setText("score: " + Game.getGame().getScore());
    }

    private void setBoss(Pane pane) {
        Boss boss = Boss.getInstance();
        pane.getChildren().add(boss);
        BossTransition bossTransition = new BossTransition(boss, this);
        Boss.getInstance().bossTransition = bossTransition;
        bossTransition.play();
    }

    private void setMute(Pane pane) {
        Rectangle rectangle = new Rectangle(72, 700, 66, 66);
        rectangle.setFill(new ImagePattern(muteImg));
        rectangle.setCursor(Cursor.HAND);
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setEffect(new Glow());
                if (!mute) {
                    mute = true;
                    audioClip.stop();
                }
                else {
                    mute = false;
                    audioClip.play();
                }
            }
        });
        pane.getChildren().add(rectangle);
    }

    private void setRocketIcon(Pane pane) {
        rocketIcon.setFill(new ImagePattern(tRocket));
        pane.getChildren().add(rocketIcon);
    }

    public void setMiniBoss(Pane pane) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(1400);
            int y = random.nextInt(800);
            new MiniBoss(pane, x, y);
        }
        for (MiniBoss miniBoss : MiniBoss.miniBosses) {
            pane.getChildren().add(miniBoss);
            miniBoss.getMiniBossTransition().play();
        }
    }

    private void setExitButton(Pane pane) {
        exit.setFill(new ImagePattern(
                new Image(Main.class.getResource("Pic/exit.png").toExternalForm()
        )));
        exit.setCursor(Cursor.HAND);
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you want close the game ?");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                ButtonType cancelButton = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type.equals(okButton)) {
                        User user = Database.getInstance().getLoggedInUser();
                        int score = Game.getGame().getScore();
                        int s = (int) Game.getGame().getTime();
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
                        try {
                            audioClip.stop();
                            new GamePanel().start(Main.getStage());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
        pane.getChildren().add(exit);
    }

    private void setBossHeal(Pane pane) {
        bossHeal.setFont(new Font(18));
        bossHeal.setTranslateX(
                1000
        );
        bossHeal.setTranslateY(
                Boss.getInstance().getTranslateY() + 120
        );
        pane.getChildren().add(bossHeal);
    }

    public void UpdateBossHeal() {
        bossHeal.setText("heal: " + Boss.getInstance().getHeal());
        if (Boss.getInstance().getHeal() <= 1000) {
            bossHeal.setTextFill(Color.RED);
        }
        else {
            bossHeal.setTextFill(Color.BLACK);
        }
    }

    public void setDie(Pane pane) throws InterruptedException {
        AudioClip dieSound = new AudioClip(
                Main.class.getResource("Musics/die.mp3").toExternalForm()
        );
        dieSound.play();
        die = new Die(pane);
        DieTransition dieTransition = new DieTransition(die);
        dieTransition.play();
        pane.getChildren().add(die);

        dieTransition.setOnFinished(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION) ;
                        alert.setHeaderText(null);
                        alert.setContentText("You Win");
                        alert.show();
                        pane.getChildren().remove(die);
                        try {
                            new GamePanel().start(Main.getStage());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }

    private void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("for shoot u can press space and for change gun u can press tab");
        alert.show();
    }
}

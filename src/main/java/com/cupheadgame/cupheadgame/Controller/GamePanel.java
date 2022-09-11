package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
public class GamePanel extends Application {

    public Button playGame;
    public Button logout;
    public Button continueGame;
    public Button Profile;
    public Button setting;

    public static AudioClip playback = new AudioClip(
            Main.class.getResource("Musics/GamePanel.mp3").toExternalForm()
    );

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Game Panel");
        stage.setScene(Menu.getMenu().getScene());
        stage.centerOnScreen();
        stage.setResizable(false);
        Menu.getMenu().getParent().getChildrenUnmodifiable().get(0).requestFocus();
        if (!playback.isPlaying())
            playback.play();
        stage.show();
    }

    public void initialize() {

    }

    public void Lougout(MouseEvent mouseEvent) throws IOException {
        Database.getInstance().setLoggedInUser(null);
        Menu.menu = Menu.LoginMenu;
        Main.goToLoginPage();
        playback.stop();
    }

    public void newGame(MouseEvent mouseEvent) throws Exception {
        playback.stop();
        new GameController().start(Main.getStage());
    }

    public void continueGame(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("this option not implement yet!");
        alert.show();
    }

    public void profileView(MouseEvent mouseEvent) throws Exception {
        if (Database.getInstance().getLoggedInUser().getUsername().equals("guest")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("guest can not access to this option");
            alert.showAndWait();
            return;
        }
        new ProfilePage().start(Main.getStage());
    }

    public void viewScoreBoard(MouseEvent mouseEvent) throws Exception {
        new ScoreBoardPage().start(Main.getStage());
    }

    public void Setting(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("not implemented yet");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}

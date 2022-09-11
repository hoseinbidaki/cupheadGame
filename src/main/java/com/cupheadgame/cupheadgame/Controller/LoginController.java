package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

import java.io.IOException;

public class LoginController {
    private AudioClip audioClip;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button register;
    @FXML
    private Button guest;
    @FXML
    private Button submit;

    @FXML
    private Label error;

    public void Submit(MouseEvent mouseEvent) throws Exception {
        login();
    }

    public void login() throws Exception {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        User user = Database.getInstance().getUser(username);
        if (user == null) {
            error.setVisible(true);
        }
        else {
            if (user.getPassword().equals(password)) {
                Database.getInstance().setLoggedInUser(user);
                Menu.menu = Menu.GamePane;
                Main.playback.stop();
                new GamePanel().start(Main.getStage());
            }
            else {
                error.setVisible(true);
            }
        }
    }
    public void Register(MouseEvent mouseEvent) throws IOException {
        Main.goToRegisterPage();
    }

    public void guest(MouseEvent mouseEvent) throws Exception {
        Database.getInstance().setLoggedInUser(new User("guest", "", "unknown.jpg", 0));
        Menu.menu = Menu.GamePane;
        Main.playback.stop();
        new GamePanel().start(Main.getStage());
    }

    public void Clear() {
        error.setVisible(false);
    }

    public void checkEnterPressed(KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            login();
        }
        else {
            Clear();
        }
    }
}
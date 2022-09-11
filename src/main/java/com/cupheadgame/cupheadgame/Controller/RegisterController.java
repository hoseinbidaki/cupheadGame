package com.cupheadgame.cupheadgame.Controller;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Menu;
import com.cupheadgame.cupheadgame.Models.Database;
import com.cupheadgame.cupheadgame.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Random;

public class RegisterController {
    @FXML
    private ImageView avatar;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit;
    @FXML
    private Label error;
    private String imageName = "unknown.jpg";
    private Object StandardCopyOption;

    public void Submit(MouseEvent mouseEvent) throws IOException {
        register();
    }


    public void register() throws IOException {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        if (username.equals("") || password.equals("")) {
            error.setText("all field must be filled");
            error.setVisible(true);
            return;
        }
        if (Database.getInstance().getUser(username) != null) {
            Alert errormess = new Alert(Alert.AlertType.ERROR);
            errormess.setHeaderText(null);
            errormess.setContentText("already " + username + " is exist");
            errormess.showAndWait();
            return;
        }
        User user = new User(username, password, imageName, 0);
        Database.getInstance().addUser(user);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("register");
        alert.setHeaderText(null);
        alert.setContentText("register successfully");
        alert.showAndWait();
        clear();
        Database.getInstance().saveData();
    }

    public void setRandowm(MouseEvent mouseEvent) {
        Random rnd = new Random();
        int id = rnd.nextInt(8) + 1;
        imageName = id + ".jpg";
        try {
            Image image = new Image(Main.class.getResourceAsStream("Pic/Avatars/"+id+".jpg"));
            avatar.setImage(image);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Clear() {
        error.setVisible(false);
    }

    public void clear() {
        username.setText("");
        password.setText("");
        avatar.setImage(
                new Image(Main.class.getResourceAsStream("Pic/Avatars/unknown.jpg")));
    }

    public void EntetPressedCheck(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            register();
        }
        else {
            Clear();
        }
    }

    public void Back(MouseEvent mouseEvent) throws IOException {
        Menu.menu = Menu.LoginMenu;
        Main.goToLoginPage();
    }

    public void browser(MouseEvent mouseEvent) throws IOException, URISyntaxException {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("JPG files", "*.jpg"));
            fc.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PNG files", "*.png"));
            fc.setTitle("attach a file");
            File selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                Path source=Paths.get(String.valueOf(selectedFile));
                String path = Main.class.getResource("Pic/Avatars/")
                        .toURI().toString().substring(6);
                path += GenerateRandomName() + selectedFile.getName();
                File destination = new File(path);
                FileUtils.copyFile(selectedFile, destination);
                imageName = selectedFile.getName();
                avatar.setImage(
                        new Image(Main.class.getResource("Pic/Avatars/" + imageName).toExternalForm()));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private String GenerateRandomName() {
        String res = "";
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            char a = (char) ('a' + random.nextInt(26));
            res += a;
        }
        return res;
    }
}

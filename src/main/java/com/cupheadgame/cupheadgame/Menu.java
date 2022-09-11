package com.cupheadgame.cupheadgame;

import com.cupheadgame.cupheadgame.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public enum Menu {
    LoginMenu(230, 600, "LoginPage"),
    RegisterMenu(230, 600, "registerPage"),
    GamePane(295, 530, "GamePanel")
    ;

    public static Menu menu;
    public static Menu getMenu() {
        if (menu == null) menu = Menu.LoginMenu;
        return menu;
    }

    private int h;
    private int w;
    private String filename;
    private Parent parent;

    Menu(int h, int w, String filename) {
        this.h = h;
        this.w = w;
        this.filename = filename;
    }

    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(filename+".fxml"));
        parent = fxmlLoader.load();
        Scene scene = new Scene(parent, w, h);
        return scene;
    }

    public Parent getParent() {
        return parent;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public String getFilename() {
        return filename;
    }
}
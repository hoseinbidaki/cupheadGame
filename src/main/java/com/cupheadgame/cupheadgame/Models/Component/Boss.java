package com.cupheadgame.cupheadgame.Models.Component;

import com.cupheadgame.cupheadgame.Main;
import com.cupheadgame.cupheadgame.Models.Transitions.BossTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {
    private static Boss boss;
    public BossTransition bossTransition;

    static Pane pane;
    public static Boss getInstance() {
        if (boss == null) boss = new Boss(pane);
        return boss;
    }
    private Image image = new Image(
            Main.class.getResource("Pic/Game/models/boss/1.png").toExternalForm());

    private int heal;
    public Boss(Pane pane) {
        super(800, 120, 651, 509);
        this.setFill(new ImagePattern(image));
        this.heal = 4000;
        this.pane = pane;
    }

    public void setImage(Image image) {
        this.image = image;
        this.setFill(new ImagePattern(image));
    }
    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(rectangle.getLayoutBounds());
    }

    public static Boss getBoss() {
        return boss;
    }

    public static void setBoss(Boss boss) {
        Boss.boss = boss;
    }

    public Image getImage() {
        return image;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public void remove() {
        this.setVisible(false);
    }
}

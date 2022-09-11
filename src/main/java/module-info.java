module com.cupheadgame.cupheadgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;
    requires org.apache.commons.io;

    opens com.cupheadgame.cupheadgame to javafx.fxml;
    exports com.cupheadgame.cupheadgame;
    exports com.cupheadgame.cupheadgame.Controller;
    opens com.cupheadgame.cupheadgame.Controller to javafx.fxml;
    exports com.cupheadgame.cupheadgame.Models;
    opens com.cupheadgame.cupheadgame.Models to javafx.fxml;

}
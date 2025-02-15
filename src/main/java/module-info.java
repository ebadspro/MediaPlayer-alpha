module com.example.mediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.logging;
    requires java.prefs;

    opens com.example.mediaplayer to javafx.fxml;
    exports com.example.mediaplayer;

    opens com.example.musicplayer to javafx.fxml;
    exports com.example.musicplayer;

}
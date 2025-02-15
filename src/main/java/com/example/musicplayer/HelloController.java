package com.example.musicplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.*;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.net.URI;

public class HelloController {

    @FXML
    private HBox hboxContainer;  // Add fx:id="hboxContainer" to HBox in FXML



    @FXML
    private Button btnMute;

    private boolean isMuted = false;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnSelectmedia;

    @FXML
    private Button btnStop;

    @FXML
    private Label labelDuration;

    @FXML
    private Slider slider;


    @FXML
    private MediaView mediaView;
    private Media media;
    private MediaPlayer mediaPlayer;

    private boolean played = false;

    @FXML
    public void initialize() {
        hboxContainer.setOnMouseEntered(event -> {
            hboxContainer.setStyle("-fx-background-color: lightgray; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
        });

        hboxContainer.setOnMouseExited(event -> {
            hboxContainer.setStyle("-fx-background-color: transparent;");
        });
    }

    @FXML
    void btnPlay(ActionEvent event) {
        if (!played) {
            btnPlay.setText("⏸");
            mediaPlayer.play();
            played = true;
        } else {
            btnPlay.setText("▶");
            mediaPlayer.pause();
            played = false;
        }
    }
    @FXML
    void btnMute(ActionEvent event) {
        if (mediaPlayer != null) {
            isMuted = !isMuted;
            mediaPlayer.setMute(isMuted);
            btnMute.setText(isMuted ?   "🔊" : "🔇");
        }
    }


    @FXML
    void btnStop(ActionEvent event) {
        if (played) {
            btnPlay.setText("🚫");
            mediaPlayer.stop();
            played = false;
        }

    }

    @FXML
    void SelectMedia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Media");
        File SeletedFile = fileChooser.showOpenDialog(null);

        if (SeletedFile != null) {
            String url = SeletedFile.toURI().toString();
            Media media = new Media(url);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.currentTimeProperty().addListener((observableValue, oldValue, newValue) -> {
            slider.setValue(newValue.toSeconds());
            labelDuration.setText("Duration: " + (double)slider.getValue() + " / " + (double)media.getDuration().toSeconds());
            });

            mediaPlayer.setOnReady( () -> {
            Duration totalDuration = media.getDuration();
            slider.setMax(mediaPlayer.getTotalDuration().toSeconds());
            labelDuration.setText("Duration: " + totalDuration);
            });

            Scene scene = mediaView.getScene();
            mediaView.fitWidthProperty().bind(scene.widthProperty());
            mediaView.fitHeightProperty().bind(scene.heightProperty());
        }

        else {
            System.out.println("No Media Selected");
        }
    }



    @FXML
    private void sliderPressed(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

}


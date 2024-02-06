package main.Ressources.Java;

import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.media.Media;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the font
        InputStream fontStream = getClass().getResourceAsStream("/main/Ressources/Font/CustomFont.ttf");
        Font.loadFont(fontStream, 16);

        // Set the background
        StackPane root = new StackPane();
        root.setStyle("-fx-background-image: url('/main/Ressources/Images/wallpaper.gif'); "
                + "-fx-background-repeat: no-repeat; " + "-fx-background-size: cover;");

        // Load FXML
        Parent fxmlRoot = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        root.getChildren().add(fxmlRoot);

        // Set the stage properties
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess-JAV-501");

        // Set the application logo
        Image logo = new Image(getClass().getResourceAsStream("/main/Ressources/Images/blackKnight.png"));
        primaryStage.getIcons().add(logo);
        primaryStage.show();

        // Play the menu music
        String musicFile = "/main/Ressources/Sound/07. Flower Garden.mp3"; // Path to your audio file 
        Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        

        // Play the audio
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
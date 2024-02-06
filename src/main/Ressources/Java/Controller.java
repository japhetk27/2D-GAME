package main.Ressources.Java;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Controller {

    @FXML
    private Label label;

    public void playButtonAction() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Choose game mode");
        dialog.setHeaderText("Choose your game mode");

        ButtonType ChessButton = new ButtonType("Play calssic chess");
        ButtonType ChessButton2 = new ButtonType("Play custom chess");

        dialog.getDialogPane().getButtonTypes().addAll(ChessButton, ChessButton2, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ChessButton) {
                showChessBoard();
            } else if (result.get() == ChessButton2) {
                showChessBoar02();
            }
        }
    }

    public void showChessBoard() {
        Stage stage = new Stage();
        stage.setTitle("Chess-JAV-501");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        // Set the application logo for the chess board
        Image logo = new Image(getClass().getResourceAsStream("/main/Ressources/Images/blackKnight.png"));
        stage.getIcons().add(logo);
        stage.show();

        // Chess board theme by default
        new Game(new GridPane(), null, "Standard");

        Scene scene = new Scene(Game.cb.chessBoard, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void showChessBoar02(){
        Stage stage = new Stage();
        stage.setTitle("Chess-JAV-501");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        // Set the application logo for the chess board
        Image logo = new Image(getClass().getResourceAsStream("/main/Ressources/Images/blackKnight.png"));
        stage.getIcons().add(logo);
        stage.show();

        // Chess board theme by default
        new Game(new GridPane(), null, "");

        Scene scene = new Scene(Game.cb.chessBoard, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void setThemeAction() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Chess Themes");
        dialog.setHeaderText("Choose your theme");

        // Create a ToggleGroup for radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();

        // Create radio buttons for each theme
        RadioButton classicButton = new RadioButton("Classic");
        RadioButton coralButton = new RadioButton("Coral");
        RadioButton duskButton = new RadioButton("Dusk");
        RadioButton wheatButton = new RadioButton("Wheat");
        RadioButton marineButton = new RadioButton("Marine");
        RadioButton emeraldButton = new RadioButton("Emerald");
        RadioButton sandcastleButton = new RadioButton("Sandcastle");
        RadioButton blackAndWhiteButton = new RadioButton("Black and White");

        // Set radio buttons
        classicButton.setToggleGroup(toggleGroup);
        coralButton.setToggleGroup(toggleGroup);
        duskButton.setToggleGroup(toggleGroup);
        wheatButton.setToggleGroup(toggleGroup);
        marineButton.setToggleGroup(toggleGroup);
        emeraldButton.setToggleGroup(toggleGroup);
        sandcastleButton.setToggleGroup(toggleGroup);
        blackAndWhiteButton.setToggleGroup(toggleGroup);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(classicButton, coralButton, duskButton, wheatButton, marineButton, emeraldButton,
                sandcastleButton, blackAndWhiteButton);
        dialog.getDialogPane().setContent(vBox);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            String selectedTheme = selectedRadioButton.getText();
            ThemeManager.setCurrentTheme(selectedTheme);
        }

    }

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + " Running on Java " + javaVersion + ".");
    }
}
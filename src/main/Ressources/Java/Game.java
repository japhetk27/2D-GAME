package main.Ressources.Java;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class Game  {

    public static Piece currentPiece;
    public static String currentPlayer;
    public static ChessBoard cb;
    private boolean game;

    // Gregg
    public Game(GridPane chessBoard, String theme, String mode) {
        // Gregg
        Game.cb = new ChessBoard(chessBoard, theme, mode);
        currentPiece = null;
        currentPlayer = "white";
        this.game = true;
        addEventHandlers(cb.chessBoard);
    }

    private void addEventHandlers(GridPane chessBoard) {
        chessBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    handleLeftClick(event.getTarget());
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    handleRightClick(event.getTarget(), event.getSceneX(), event.getSceneY());
                }
            }
        });
    }

    private void handleLeftClick(EventTarget target) {
        // Clicked on square
        if (target instanceof Square) {
            Square square = (Square) target;
            if (square.occupied) {
                Piece newPiece = (Piece) square.getChildren().get(0);
                // Selecting a new piece
                if (currentPiece == null) {
                    currentPiece = newPiece;
                    // currentPiece.getAllPossibleMoves();
                    if (!currentPiece.getColor().equals(currentPlayer)) {
                        currentPiece = null;
                        return;
                    }
                    selectPiece(game);
                }
                // Selecting other piece of same color || Killing a piece
                else {
                    if (currentPiece.color.equals(newPiece.color)) {
                        deselectPiece(false);
                        currentPiece = newPiece;
                        // currentPiece.getAllPossibleMoves();
                        selectPiece(game);
                    } else {
                        killPiece(square);
                    }
                }
            }
            // Dropping a piece on blank square
            else {
                dropPiece(square);
            }
        }
        // Clicked on piece
        else if (target instanceof Piece) {
            Piece newPiece = (Piece) target;
            Square square = (Square) newPiece.getParent();
            // Selecting a new piece
            if (currentPiece == null) {
                currentPiece = newPiece;
                if (!currentPiece.getColor().equals(currentPlayer)) {
                    currentPiece = null;
                    return;
                }
                selectPiece(game);
            }
            // Selecting other piece of same color || Killing a piece
            else {
                if (currentPiece.color.equals(newPiece.color)) {
                    deselectPiece(false);
                    currentPiece = newPiece;
                    selectPiece(game);
                } else {
                    killPiece(square);
                }
            }
        }
    }

    private void handleRightClick(EventTarget target, double sceneX, double sceneY) {
        if (target instanceof Square) {
            Square square = (Square) target;
            showGodModeDialog(square, sceneX, sceneY);
        }
    }

    private void showGodModeDialog(Square square, double sceneX, double sceneY) {
        GodModeDialog dialog = new GodModeDialog(square);
        Optional<Piece> result = dialog.showAndWait();

        result.ifPresent(selectedPiece -> {
            addPieceToSquare(square, selectedPiece);
        });
    }

    private void addPieceToSquare(Square square, Piece piece) {
        square.getChildren().add(piece);
        square.occupied = true;
        piece.posX = square.x;
        piece.posY = square.y;
    }

    private void selectPiece(boolean game) {
        if (!game) {
            currentPiece = null;
            return;
        }

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.BLACK);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        currentPiece.setEffect(borderGlow);
        currentPiece.getAllPossibleMoves();
        currentPiece.showAllPossibleMoves(true);
    }

    private void deselectPiece(boolean changePlayer) {
        currentPiece.setEffect(null);
        currentPiece.showAllPossibleMoves(false);
        currentPiece = null;
        if (changePlayer)
            currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    private void dropPiece(Square square) {
        if (!currentPiece.possibleMoves.contains(square.name))
            return;

        // Play moove sound
        String musicFile = "/main/Ressources/Sound/move-self.mp3";
        Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // Play the sound
        mediaPlayer.play();

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);

    }

    private void killPiece(Square square) {
        if (!currentPiece.possibleMoves.contains(square.name))
            return;

        Piece killedPiece = (Piece) square.getChildren().get(0);
        if (killedPiece.type.equals("King")) {
            this.game = false;

            // Create a popup for game over
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("The King has been killed. Game over! " + currentPlayer + " won!");
            alert.showAndWait();
            

            // Play game over sound
            String musicFile = "/main/Ressources/Sound/winSound.mp3";
            Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            // Play the sound
            mediaPlayer.play();
        }

        // Play capture sound
        String musicFile = "/main/Ressources/Sound/capture.mp3";
        Media sound = new Media(getClass().getResource(musicFile).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // Play the sound
        mediaPlayer.play();

        Square initialSquare = (Square) currentPiece.getParent();
        square.getChildren().remove(0);
        square.getChildren().add(currentPiece);
        square.occupied = true;
        initialSquare.getChildren().removeAll();
        initialSquare.occupied = false;
        currentPiece.posX = square.x;
        currentPiece.posY = square.y;
        deselectPiece(true);
    }

}



package main.Ressources.Java;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;

public class GodModeDialog extends Dialog<Piece> {

    private Piece selectedPiece;

    public GodModeDialog(Square square) {
        setTitle("God Mode");
        setResultConverter(f -> selectedPiece);
        HBox hbox = new HBox();
        hbox.getChildren().add(new GodModePieceLabel(square, new Queen("white", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Queen("black", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Rook("white", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Rook("black", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Bishop("white", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Bishop("black", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Knight("white", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Knight("black", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Pawn("white", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new Pawn("black", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new King("white", 0, 0)));
        hbox.getChildren().add(new GodModePieceLabel(square, new King("black", 0, 0)));
        getDialogPane().setContent(hbox);
    }

    private class GodModePieceLabel extends Label {

        private Piece piece;

        GodModePieceLabel(Square square, Piece piece) {
            ImageView imageView = new ImageView(piece.getImage());
            setGraphic(imageView);
            this.piece = piece;
            setOnMouseReleased(e -> onMouseReleased(square, e));
        }

        private void onMouseReleased(Square square, MouseEvent e) {
            selectedPiece = piece;
            getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
            close();
            e.consume();
        }
    }
}

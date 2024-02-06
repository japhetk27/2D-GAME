package main.Ressources.Java;

import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public abstract class Piece extends ImageView {
    String type;
    String color;
    int posX, posY;
    ArrayList<String> possibleMoves;

    public Piece(String color, int posX, int posY) {
        this.color = color;
        this.posX = posX;
        this.posY = posY;
        addEventHandler();
    }

    public String getColor() {
        return this.color;
    }

    public void setPiece(Image image) {
        this.setImage(image);
    }

    public void setImage() {
        this.setPiece(new Image("main/Ressources/Images/" + this.color.toLowerCase() + this.type + ".png"));
    }

    private void addEventHandler() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getAllPossibleMoves();
            }
        });
    }

    public void getAllPossibleMoves() {
        if (Game.cb != null && Game.cb.squares != null) {
            possibleMoves = new ArrayList<>();

            for (Square square : Game.cb.squares) {
                int targetX = square.x;
                int targetY = square.y;

                if (isValidMove(targetX, targetY)) {
                    possibleMoves.add(square.name);
                }
            }
        } else {
            System.out.println("Erreur : Game.cb n'est pas initialisé !");
        }
    }

    private boolean isValidMove(int targetX, int targetY) {

        switch (this.type) {
            case "Pawn":
                // le pion : peut avancer d'une case vers l'avant
                int direction = this.color.equals("white") ? 1 : -1;
                if (targetX == this.posX && targetY == this.posY + direction) {
                    return true;
                }
                break;

            case "Rook":
                if (targetX == this.posX || targetY == this.posY) {
                    return true;
                }
                break;

            default:

                return false;
        }
        return true;
    }

    public void showAllPossibleMoves(boolean val) {
        if (val) {
            Glow glow = new Glow();
            glow.setLevel(0.3);
            for (String move : possibleMoves) {
                Square square = getSquareByName(move);
                square.setEffect(glow);

                Piece piece = getPieceByName(move);
                if (piece == null)
                    continue;
                if (piece.type.equals("King")) {
                    square.setBorder(new Border(new BorderStroke(Color.DARKRED,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.5))));
                } else {
                    square.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.2))));
                }

            }
        } else {
            for (String move : possibleMoves) {
                Square square = getSquareByName(move);
                square.setEffect(null);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    public Square getSquareByName(String name) {
        for (Square square : Game.cb.squares) {
            if (square.name.equals(name)) {
                return square;
            }
        }

        return null;
    }

    public Piece getPieceByName(String name) {
        for (Square square : Game.cb.squares) {
            if (square.getChildren().size() == 0)
                continue;

            if (square.name.equals(name))
                return (Piece) square.getChildren().get(0);

        }
        return null;
    }

    @Override
    public String toString() {
        return this.color + " " + this.type;
    }
}
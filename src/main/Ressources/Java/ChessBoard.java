package main.Ressources.Java;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class ChessBoard {

    GridPane chessBoard;
    String theme;

    // Gregg 
    String mode;
    public ArrayList<Square> squares = new ArrayList<>();

    // mode : Gregg
    public ChessBoard(GridPane chessBoard, String theme, String mode){
        this.chessBoard = chessBoard;
        this.theme = theme;
        this.mode = mode;
        makeBoard(this.chessBoard, theme);
    }


    private void makeBoard(GridPane chessBoard, String theme){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                Square square = new Square(i,j);
                square.setName("Square" + i + j); // Set name of square
                square.setPrefHeight(100); // Set size of square (height)
                square.setPrefWidth(100); // Set size of square (width)
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                setTheme(square, theme, i, j);
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
        if (mode.equals("Standard")) // Gregg
            addPieces();
       
    }

    private void setTheme(Square square, String theme, int i, int j){
        Color squareColor = ThemeManager.getSquareColor(i, j);
        square.setBackground(new Background(new BackgroundFill(squareColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void addPiece(Square square, Piece piece){
        square.getChildren().add(piece);
        square.occupied = true;
    }

    private void addPieces(){
        for(Square square : squares){
            if(square.occupied) continue;
            if(square.y == 1){
                addPiece(square, new Pawn("black", square.x, square.y)); // Add black pawn
            }
            else if(square.y == 6){
                addPiece(square, new Pawn("white", square.x, square.y)); // Add white pawn
            }
            else if(square.y == 0){
                if(square.x == 4){
                    addPiece(square, new King("black", square.x, square.y)); // Add black king
                }
                if(square.x == 3){
                    addPiece(square, new Queen("black", square.x, square.y)); // Add black queen
                }
                if(square.x == 2 || square.x == 5){
                    addPiece(square, new Bishop("black", square.x, square.y)); // Add black bishop
                }
                if(square.x == 1 || square.x == 6){
                    addPiece(square, new Knight("black", square.x, square.y)); // Add black knight
                }
                if(square.x == 0 || square.x == 7){
                    addPiece(square, new Rook("black", square.x, square.y)); // Add black rook
                }
            }
            else if(square.y == 7){
                if(square.x == 4){
                    addPiece(square, new King("white", square.x, square.y)); // Add white king
                }
                if(square.x == 3){
                    addPiece(square, new Queen("white", square.x, square.y)); // Add white queen
                }
                if(square.x == 2 || square.x == 5){
                    addPiece(square, new Bishop("white", square.x, square.y)); // Add white bishop
                }
                if(square.x == 1 || square.x == 6){
                    addPiece(square, new Knight("white", square.x, square.y)); // Add white knight
                }
                if(square.x == 0 || square.x == 7){
                    addPiece(square, new Rook("white", square.x, square.y)); // Add white rook
                }
            }

        }
    }

}
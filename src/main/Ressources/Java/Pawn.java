package main.Ressources.Java;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(String color, int posX, int posY) {
        super(color, posX, posY);
        this.type = "Pawn";
        setImage();
    }

    @Override
    public void getAllPossibleMoves() {
        int x = this.posX;
        int y = this.posY;
        ArrayList<String> moves = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();

        int direction = color.equals("white") ? -1 : 1;

        // Regular move one square forward
        moves.add("Square" + x + (y + direction));

        // Capture moves
        moves.add("Square" + (x + 1) + (y + direction)); // Capture to the right
        moves.add("Square" + (x - 1) + (y + direction)); // Capture to the left

        // Double move from starting position
        if ((color.equals("black") && posY == 1) || (color.equals("white") && posY == 6)) {
            moves.add("Square" + x + (y + 2 * direction));
        }

        // Filter out invalid moves
        for (String move : moves) {
            if (isValidMove(move)) {
                possibleMoves.add(move);
            }
        }
    }

    private boolean isValidMove(String move) {

        Square targetSquare = getSquareByName(move);

        // Check if the target square is valid
        if (targetSquare == null) {
            return false;
        }

        // Check for regular move one square forward
        if (move.equals("Square" + this.posX + (this.posY + (color.equals("white") ? -1 : 1)))) {
            return !targetSquare.occupied;
        }

        // Check for capture moves
        if (move.equals("Square" + (this.posX + 1) + (this.posY + (color.equals("white") ? -1 : 1))) ||
                move.equals("Square" + (this.posX - 1) + (this.posY + (color.equals("white") ? -1 : 1)))) {
            return targetSquare.occupied && !getPieceByName(move).getColor().equals(Game.currentPlayer);
        }

        // Check for double move from starting position
        if ((color.equals("black") && posY == 1) || (color.equals("white") && posY == 6)) {
            return move.equals("Square" + this.posX + (this.posY + 2 * (color.equals("white") ? -1 : 1))) &&
                    !targetSquare.occupied
                    && !getSquareByName("Square" + this.posX + (this.posY + (color.equals("white") ? -1 : 1))).occupied;
        }

        return false;
    }

}

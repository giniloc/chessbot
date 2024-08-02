package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteKnight.png" : "blackKnight.png", board);

    }


    @Override
    public boolean isValidMove(Coordinate newCoords) {

        Piece destinationPiece = board.getPiece(newCoords.getRow(), newCoords.getCol());
        if (destinationPiece != null && destinationPiece.isWhite == this.isWhite) {
            return false;
        }

        return true;
    }

    @Override
    public ArrayList<Coordinate> getValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        // Hier alle moves uitvoeren die in dezelfde rij komen als de rook
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                if ((i == this.row + 1 && j == this.col + 2) || (i == this.row + 2 && j == this.col + 1) || (i == this.row + 2 && j == this.col - 1)
                        || (i == this.row + 1 && j == this.col - 2) || (i == this.row - 1 && j == this.col - 2)
                        || (i == this.row - 2 && j == this.col - 1) || (i == this.row - 2 && j == this.col + 1) || (i == this.row - 1 && j == this.col + 2)) {
                    Coordinate newCoords = new Coordinate(i, j);
                    if (isValidMove(newCoords)) {
                        validMoves.add(newCoords);
                    }
                }
            }
        }


        return validMoves;
    }

    @Override
    public boolean isSuicideMove(Coordinate newCoords) {
        return false;
    }
}

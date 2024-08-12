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
        int newRow = newCoords.getRow();
        int newCol = newCoords.getCol();

        int rowDiff = Math.abs(newRow - this.row);
        int colDiff = Math.abs(newCol - this.col);

        // Controleer of de beweging een L-vorm heeft
        boolean isLShapedMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        // Controleer of de bestemming niet bezet is door een stuk van dezelfde kleur
        Piece destinationPiece = board.getPiece(newRow, newCol);
        boolean isDestinationOccupiedBySameColor = destinationPiece != null && destinationPiece.isWhite() == this.isWhite();

        // De zet is geldig als het een L-vormige beweging is en de bestemming niet bezet is door een stuk van dezelfde kleur
        return isLShapedMove && !isDestinationOccupiedBySameColor;
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

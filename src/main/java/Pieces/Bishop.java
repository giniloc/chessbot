package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteBishop.png" : "blackBishop.png", board);

    }


    @Override
    public boolean isValidMove(Coordinate newCoords) {

        // Bekijk welke richting het uitgaat
        int rowDirection = Integer.compare(newCoords.getRow(), this.row);
        int colDirection = Integer.compare(newCoords.getCol(), this.col);

        // Bekijk of er geen stukken in de weg staan want een loper kan niet springen
        int currentRow = this.row + rowDirection;
        int currentCol = this.col + colDirection;
        while (currentRow != newCoords.getRow() || currentCol != newCoords.getCol()) {
            Piece piece = board.getPiece(currentRow, currentCol);
            if (piece != null) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // Laatste check of het stuk dat in de weg staat niet van eigen soort is
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
                if (Math.abs(i - this.row) == Math.abs(j - this.col)) {
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

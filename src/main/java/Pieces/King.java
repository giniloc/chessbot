package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

public class King extends Piece{
    public King(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteKing.png" : "blackKing.png", board);

    }

    @Override
    public boolean isValidMove(Coordinate newCoords) {
        int newRow = newCoords.getRow();
        int newCol = newCoords.getCol();

        int rowDiff = Math.abs(newRow - row);
        int colDiff = Math.abs(newCol - col);

        // Koning kan één veld in elke richting verplaatsen
        return rowDiff <= 1 && colDiff <= 1;

    }

    @Override
    public ArrayList<Coordinate> getValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        // Controleer alle mogelijke zetten binnen 1 veld rondom de koning
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                // Controleer of de nieuwe positie binnen het bord ligt
                if (newRow >= 0 && newRow < board.getRows() && newCol >= 0 && newCol < board.getCols()) {
                    Piece pieceAtNewPosition = board.getPiece(newRow, newCol);
                    Coordinate newCoords = new Coordinate(newRow, newCol);

                    // Controleer of de zet geldig is volgens de koningbeweging
                    if (isValidMove(newCoords)) {
                        // Controleer of er geen stuk van dezelfde kleur op de nieuwe positie staat
                        if (pieceAtNewPosition == null || pieceAtNewPosition.isWhite() != this.isWhite()) {
                            validMoves.add(newCoords);
                        }
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

    public Coordinate getCoords() {
        return coords;
    }


}



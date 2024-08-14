package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

public class King extends Piece{
    private boolean hasMoved = false;
    public King(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteKing.png" : "blackKing.png", board);

    }
    @Override
    public void move(Coordinate coords) {
        super.move(coords);
        hasMoved = true; // Markeer dat de koning is bewogen
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

        // Voeg reguliere koningbewegingen toe
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < board.getRows() && newCol >= 0 && newCol < board.getCols()) {
                    Piece pieceAtNewPosition = board.getPiece(newRow, newCol);
                    Coordinate newCoords = new Coordinate(newRow, newCol);
                    if (isValidMove(newCoords)) {
                        if (pieceAtNewPosition == null || pieceAtNewPosition.isWhite() != this.isWhite()) {
                            validMoves.add(newCoords);
                        }
                    }
                }
            }
        }

        // Controleer voor rokade
        if (!hasMoved && !board.isKingInCheck(isWhite)) {
            // Kleine rokade (O-O)
            if (canCastle(true)) {
                validMoves.add(new Coordinate(row, col + 2));
            }
            // Grote rokade (O-O-O)
            if (canCastle(false)) {
                validMoves.add(new Coordinate(row, col - 2));
            }
        }

        return validMoves;
    }

    private boolean canCastle(boolean kingside) {
        int rookCol = kingside ? 7 : 0;
        int direction = kingside ? 1 : -1;

        Piece rook = board.getPiece(row, rookCol);

        // Controleer of de toren nog niet bewogen is en dat er geen stukken tussen de koning en de toren staan
        if (rook instanceof Rook && !((Rook) rook).hasMoved()) {
            for (int i = col + direction; i != rookCol; i += direction) {
                if (board.getPiece(row, i) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }



    @Override
    public boolean isSuicideMove(Coordinate newCoords) {
        return false;
    }

    public Coordinate getCoords() {
        return coords;
    }
    public King getKing() {
        return this;
    }
    public boolean hasMoved() {
        return hasMoved;
    }


}



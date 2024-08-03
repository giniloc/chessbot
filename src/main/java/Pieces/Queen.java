package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteQueen.png" : "blackQueen.png", board);
    }

    @Override
    public boolean isValidMove(Coordinate newCoords) {
        // Check of de koningin binnen de grenzen van het bord beweegt
        if (newCoords.getRow() < 0 || newCoords.getRow() >= board.getRows() ||
                newCoords.getCol() < 0 || newCoords.getCol() >= board.getCols()) {
            return false;
        }

        // Bekijk welke richting het uitgaat
        int rowDirection = Integer.compare(newCoords.getRow(), this.row);
        int colDirection = Integer.compare(newCoords.getCol(), this.col);

        // Rechtlijnige beweging (zoals een toren)
        if (this.row == newCoords.getRow() || this.col == newCoords.getCol()) {
            return isValidStraightMove(newCoords, rowDirection, colDirection);
        }
        // Diagonale beweging (zoals een loper)
        else if (Math.abs(newCoords.getRow() - this.row) == Math.abs(newCoords.getCol() - this.col)) {
            return isValidDiagonalMove(newCoords, rowDirection, colDirection);
        }

        // Anders is het geen geldige beweging
        return false;
    }

    private boolean isValidStraightMove(Coordinate newCoords, int rowDirection, int colDirection) {
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

    private boolean isValidDiagonalMove(Coordinate newCoords, int rowDirection, int colDirection) {
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

        // Voeg rechtlijnige bewegingen toe (zoals een toren)
        for (int i = 0; i < board.getRows(); i++) {
            if (i != this.row) {
                Coordinate newCoords = new Coordinate(i, this.col);
                if (isValidMove(newCoords)) {
                    validMoves.add(newCoords);
                }
            }
        }

        for (int j = 0; j < board.getCols(); j++) {
            if (j != this.col) {
                Coordinate newCoords = new Coordinate(this.row, j);
                if (isValidMove(newCoords)) {
                    validMoves.add(newCoords);
                }
            }
        }

        // Voeg diagonale bewegingen toe (zoals een loper)
        for (int i = 1; i < board.getRows(); i++) {
            // Diagonale bewegingen: linksboven, rechtsboven, linksonder, rechtsonder
            Coordinate[] diagonalMoves = new Coordinate[]{
                    new Coordinate(this.row - i, this.col - i),
                    new Coordinate(this.row - i, this.col + i),
                    new Coordinate(this.row + i, this.col - i),
                    new Coordinate(this.row + i, this.col + i)
            };

            for (Coordinate newCoords : diagonalMoves) {
                if (isValidMove(newCoords)) {
                    validMoves.add(newCoords);
                }
            }
        }

        return validMoves;
    }

    @Override
    public boolean isSuicideMove(Coordinate newCoords) {
        // Voeg logica toe om te controleren of de zet je koning in gevaar brengt
        return false;
    }
}


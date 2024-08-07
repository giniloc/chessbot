package Pieces;

import Utils.Board;
import Utils.Coordinate;
import java.util.ArrayList;
public class Rook extends Piece {
    public Rook(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteRook.png" : "blackRook.png", board);
    }

    @Override
    public boolean isValidMove(Coordinate newCoords) {
        // dit is mss overbodig omdat de getValidMoves functie enkel zal genereren op zelfde lijn/kolom
        if (newCoords.getRow() != this.row && newCoords.getCol() != this.col) {
            return false;
        }

        // mss kan dit niet eens maar toch goed om te checken denk ik dan
        if (newCoords.getRow() < 0 || newCoords.getRow() >= board.getRows() ||
                newCoords.getCol() < 0 || newCoords.getCol() >= board.getCols()) {
            return false;
        }

        // compare returnt +1, 0 of -1 als de eerste parameter groter, gelijk of kleiner is dan de tweede parameter
        int rowDirection = Integer.compare(newCoords.getRow(), this.row);
        int colDirection = Integer.compare(newCoords.getCol(), this.col);

        // dit is de voorbereiding om te checken op andere schaakstukken die op de baan van verplaatsing staan
        int currentRow = this.row + rowDirection;
        int currentCol = this.col + colDirection;

        while (currentRow != newCoords.getRow() || currentCol != newCoords.getCol()) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false;
            }
            currentRow += rowDirection;//rowDirection is +1 of -1
            currentCol += colDirection;//colDirection is +1 of -1,
            // misschien dat hier nog een check in moet als je op dezelfde plek duwt dat hij dat dan ook als geldige zet ziet
        }

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
            if (i != this.row) {
                Coordinate newCoords = new Coordinate(i, this.col);
                if (isValidMove(newCoords)) {
                    validMoves.add(newCoords);
                }
            }
        }
        // Hier alle moves uitvoeren die in dezelfde kolom komen als de rook
        for (int j = 0; j < board.getCols(); j++) {
            if (j != this.col) {
                Coordinate newCoords = new Coordinate(this.row, j);
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

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
        return false;
    }

    @Override
    public ArrayList<Coordinate> getValidMoves() {
        return null;
    }

    @Override
    public boolean isSuicideMove(Coordinate newCoords) {
        return false;
    }
}
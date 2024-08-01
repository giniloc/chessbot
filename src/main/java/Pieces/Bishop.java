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

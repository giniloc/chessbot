package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whiteRook.png" : "blackRook.png", board);

    }


    @Override
    public boolean isValidMove(int newRow, int newCol) {
        return false;
    }

    @Override
    public ArrayList<Coordinate> getValidMoves() {
        return null;
    }
}

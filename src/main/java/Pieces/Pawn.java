package Pieces;

import Utils.Coordinate;

import java.util.ArrayList;
import Utils.Board;

public class Pawn extends Piece {
    public Pawn(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whitePawn.png" : "blackPawn.png", board);
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

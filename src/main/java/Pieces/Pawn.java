package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

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
        ArrayList<Coordinate> validMoves = new ArrayList<>();

        if (isWhite){

            /*

            Huidige positie checken
            check of pawn al bewogen heeft
            TODO: boolean hasMoved toevoegen
            Opvragen van potentiele moves
                1.  linksvoor, 1 vooruit, rechtsvoor
                2. Als nog niet bewogen, 2 vooruit
                3. Checken voor en passant (5de rank voor wit, 4de rank voor zwart)
                    TODO: boolean justJumped toevoegen en 1 beurt true laten staan.
                    TODO: uitzoeken hoe het best de en passant weg te halen
             Check voor elke potentiele move of legal is en voeg toe aan validmoves coordlist

             */
        } else {

        }

        return validMoves;
    }

    @Override
    public boolean isSuicideMove() {
        return false;
    }
}

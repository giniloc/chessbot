package Pieces;

import Utils.Board;
import Utils.Coordinate;

import java.util.ArrayList;

//TODO IN FUNCTIE OM MOVE UITEINDELIJK ECHT TE DOEN MOET ER GECHECKT WORDEN OF DE GEMAAKT MOVE EEN JUMP VAN TWEE WAS OM DE JUSTJUMPED VOOR EN PASSANT OP TRUE TE ZETTEN

public class Pawn extends Piece {
    private boolean hasMoved;
    private boolean justJumped;

    public Pawn(int row, int col, boolean isWhite, Board board) {
        super(row, col, isWhite, isWhite ? "whitePawn.png" : "blackPawn.png", board);
        this.hasMoved = false;
        this.justJumped = false;
    }

    @Override
    public boolean isValidMove(Coordinate newCoords) {


        //snelle out of bounds check
        if(!(newCoords.getRow()<board.getRows() && newCoords.getRow() > 0 && newCoords.getCol()<board.getCols() && newCoords.getCol() > 0)){
            return false;
        }

        //geen out of bounds, check of stukken in de weg staan.
        if(isWhite){
            //movement wit
            if (newCoords.getRow() - coords.getRow() == 1){
                //1 vooruit bewegen
                switch(newCoords.getCol() - coords.getCol()) {
                    case -1:
                        //linksdiagonaal bewegen
                        if(board.getPiece(newCoords.getRow(), newCoords.getCol()) == null){
                            //en passant check
                            Piece enpassantPion = board.getPiece(coords.getRow(), coords.getCol()-1);
                            if (enpassantPion instanceof Pawn && !enpassantPion.isWhite){
                                if(((Pawn) enpassantPion).justJumped){
                                    return true;
                                }
                            }
                            return false;
                        } else{
                            return !board.getPiece(newCoords.getRow(), newCoords.getCol()).isWhite;
                        }
                    case 0:
                        //rechtdoor bewegen
                        if(board.getPiece(newCoords.getRow(), newCoords.getCol()) == null){
                            return true;
                        }
                        break;
                    case 1:
                        //rechtsdiagonaal bewegen
                        if(board.getPiece(newCoords.getRow(), newCoords.getCol()) == null){
                            //en passant check
                            Piece enpassantPion2 = board.getPiece(coords.getRow(), coords.getCol()+1);
                            if (enpassantPion2 instanceof Pawn && !enpassantPion2.isWhite){
                                if(((Pawn) enpassantPion2).justJumped){
                                    return true;
                                }
                            }
                            return false;
                        } else{
                            return !board.getPiece(newCoords.getRow(), newCoords.getCol()).isWhite();
                        }
                    default:
                        return false;
                }
            } else {
                //2 vooruit bewegen
                boolean valid = true;
                for(int i=0; i<2;i++){
                    if(board.getPiece(newCoords.getRow()+i, newCoords.getCol()) != null){
                        valid = false;
                    }
                }
                return valid;
            }
        } else {
            //movement zwart
            if(coords.getRow() - newCoords.getRow() == 1){
                //1 vooruit bewegen
                switch(newCoords.getCol() - coords.getCol()) {
                    case -1:
                        //linksdiagonaal bewegen
                        if(board.getPiece(newCoords.getRow(), newCoords.getCol()) == null){
                            //en passant check
                            Piece enpassantPion = board.getPiece(coords.getRow(), coords.getCol()-1);
                            if(enpassantPion instanceof Pawn && enpassantPion.isWhite){
                                if(((Pawn) enpassantPion).justJumped){
                                    return true;
                                }
                            }
                            return false;
                        } else{
                            return board.getPiece(newCoords.getRow(), newCoords.getCol()).isWhite;
                        }
                    case 0:
                        //rechtdoor bewegen
                        if(board.getPiece(newCoords.getRow(), newCoords.getCol()) == null){
                            return true;
                        }
                        break;
                    case 1:
                        //rechtsdiagonaal bewegen
                        if((board.getPiece(newCoords.getRow(), newCoords.getCol()) == null) | !(board.getPiece(newCoords.getRow(), newCoords.getCol()).isWhite)){
                            //en passant check
                            Piece enpassantPion2 = board.getPiece(coords.getRow(), coords.getCol()-1);
                            if(enpassantPion2 instanceof Pawn && enpassantPion2.isWhite){
                                if(((Pawn) enpassantPion2).justJumped){
                                    return true;
                                }
                            }
                            return false;
                        } else{
                            return board.getPiece(newCoords.getRow(), newCoords.getCol()).isWhite;
                        }
                    default:
                        return false;
                }
            } else{
                //2 vooruit bewegen
                boolean valid = true;
                for(int i=0; i<2;i++){
                    if(board.getPiece(newCoords.getRow()-i, newCoords.getCol()) != null) {
                        valid = false;
                    }
                }
                return valid;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Coordinate> getValidMoves() {
        ArrayList<Coordinate> validMoves = new ArrayList<>();
        ArrayList<Coordinate> potentialMoves = new ArrayList<>();

        int currRow = coords.getRow();
        int currCol = coords.getCol();

        if (isWhite){
            //1 vooruit
            for(int i=-1; i<=1; i++){
                potentialMoves.add(new Coordinate(currRow-1, currCol+i));
                System.out.println("1 Vooruit White");
                System.out.println("potential move: " + (currRow-1) + ", " + (currCol+i));
            }
            //2 vooruit
            if(!hasMoved) {
                potentialMoves.add(new Coordinate(currRow-2, currCol));
                System.out.println("2 Vooruit White");

                System.out.println("potential move: " + (currRow-2) + ", " + currCol);

            }

            //checken welke moves valid zijn
            for(Coordinate coord: potentialMoves){
                if(isValidMove(coord) && !isSuicideMove(coord)) validMoves.add(coord);
            }
        } else {
            //1 vooruit
            for(int i=-1; i<=1; i++){
                potentialMoves.add(new Coordinate(currRow+1, currCol+i));
                System.out.println("1 Vooruit Black");

                System.out.println("potential move: " + (currRow+1) + ", " + (currCol+i));

            }
            //2 vooruit
            if(!hasMoved) {
                potentialMoves.add(new Coordinate(currRow+2, currCol));
                System.out.println("2 Vooruit Black");

                System.out.println("potential move: " + (currRow+2) + ", " + currCol);

            }

            //checken of valid zijn
            for(Coordinate coord: potentialMoves) {
                if (isValidMove(coord) && !isSuicideMove(coord)) validMoves.add(coord);
            }
        }
        for(int i=0; i < validMoves.size(); i++) {
            System.out.println(validMoves.get(i).toString());
        }
        System.out.println("End move");

        ;        return validMoves;
    }

    @Override
    public boolean isSuicideMove(Coordinate newCoords) {
        return false;
    }
}

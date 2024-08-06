package com.example.chessbot;

import Pieces.*;
import Utils.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessScene extends Application {

    public static final int TILE_SIZE = 100;
    private Board board;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        board = new Board(8, 8, gridPane);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((row + col) % 2 == 0) {
                    rect.setFill(Color.WHITE);
                } else {
                    rect.setFill(Color.GRAY);
                }
                rect.widthProperty().bind(gridPane.widthProperty().divide(8));
                rect.heightProperty().bind(gridPane.heightProperty().divide(8));
                gridPane.add(rect, col, row);
                board.setTile(rect, row, col);
            }
        }

        for (int i = 0; i < 8; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(12.5); // 100/8 = 12.5
            gridPane.getColumnConstraints().add(colConst);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(12.5); // 100/8 = 12.5
            gridPane.getRowConstraints().add(rowConst);
        }

        setupPieces(gridPane);

        Scene scene = new Scene(gridPane, TILE_SIZE * 8, TILE_SIZE * 8);

        primaryStage.setTitle("2D Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupPieces(GridPane gridPane) {
        // Initialize pawns
        for (int col = 0; col < 8; col++) {
            addPiece(new Pawn(1, col, false, board), gridPane);
            addPiece(new Pawn(6, col, true, board), gridPane);
        }

        // Initialize other pieces
        addPiece(new Rook(0, 0, false, board), gridPane);
        addPiece(new Rook(0, 7, false, board), gridPane);
        addPiece(new Rook(7, 0, true, board), gridPane);
        addPiece(new Rook(7, 7, true, board), gridPane);

        addPiece(new Knight(0, 1, false, board), gridPane);
        addPiece(new Knight(0, 6, false, board), gridPane);
        addPiece(new Knight(7, 1, true, board), gridPane);
        addPiece(new Knight(7, 6, true, board), gridPane);

        addPiece(new Bishop(0, 2, false, board), gridPane);
        addPiece(new Bishop(0, 5, false, board), gridPane);
        addPiece(new Bishop(7, 2, true, board), gridPane);
        addPiece(new Bishop(7, 5, true, board), gridPane);

        addPiece(new Queen(0, 3, false, board), gridPane);
        addPiece(new Queen(7, 3, true, board), gridPane);

        addPiece(new King(0, 4, false, board), gridPane);
        addPiece(new King(7, 4, true, board), gridPane);
    }

    public void addPiece(Piece piece, GridPane gridPane) {
        board.placePiece(piece, piece.getRow(), piece.getCol());
        piece.fitWidthProperty().bind(gridPane.widthProperty().divide(8));
        piece.fitHeightProperty().bind(gridPane.heightProperty().divide(8));
        gridPane.add(piece, piece.getCol(), piece.getRow());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

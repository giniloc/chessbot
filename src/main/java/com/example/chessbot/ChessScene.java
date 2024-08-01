package com.example.chessbot;

import Pieces.*;
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
    private Piece[][] board = new Piece[8][8];

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

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

        setupPieces();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    piece.fitWidthProperty().bind(gridPane.widthProperty().divide(8));
                    piece.fitHeightProperty().bind(gridPane.heightProperty().divide(8));
                    gridPane.add(piece, col, row);
                }
            }
        }

        Scene scene = new Scene(gridPane, TILE_SIZE * 8, TILE_SIZE * 8);

        primaryStage.setTitle("2D Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupPieces() {
        // Initialize pawns
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn(1, col, false);
            board[6][col] = new Pawn(6, col, true);
        }

        // Initialize other pieces
        board[0][0] = new Rook(0, 0, false);
        board[0][7] = new Rook(0, 7, false);
        board[7][0] = new Rook(7, 0, true);
        board[7][7] = new Rook(7, 7, true);

        board[0][1] = new Knight(0, 1, false);
        board[0][6] = new Knight(0, 6, false);
        board[7][1] = new Knight(7, 1, true);
        board[7][6] = new Knight(7, 6, true);

        board[0][2] = new Bishop(0, 2, false);
        board[0][5] = new Bishop(0, 5, false);
        board[7][2] = new Bishop(7, 2, true);
        board[7][5] = new Bishop(7, 5, true);

        board[0][3] = new Queen(0, 3, false);
        board[7][3] = new Queen(7, 3, true);

        board[0][4] = new King(0, 4, false);
        board[7][4] = new King(7, 4, true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

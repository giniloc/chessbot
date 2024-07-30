package com.example.chessbot;

import Pieces.Pawn;
import Pieces.Piece;
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

        // Ensure each column and row resizes with the window
        for (int i = 0; i < 8; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(12.5); // 100/8 = 12.5
            gridPane.getColumnConstraints().add(colConst);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(12.5); // 100/8 = 12.5
            gridPane.getRowConstraints().add(rowConst);
        }

        // Create and add chess pieces
        Piece[][] board = new Piece[8][8];
        board[1][0] = new Pawn(1, 0, false);
        board[1][1] = new Pawn(1, 1, false);
        board[1][2] = new Pawn(1, 2, false);
        board[1][3] = new Pawn(1, 3, false);
        board[1][4] = new Pawn(1, 4, false);
        board[1][5] = new Pawn(1, 5, false);
        board[1][6] = new Pawn(1, 6, false);
        board[1][7] = new Pawn(1, 7, false);

        // Add more pieces here...
        board[6][0] = new Pawn(6, 0, true);
        board[6][1] = new Pawn(6, 1, true);
        board[6][2] = new Pawn(6, 2, true);
        board[6][3] = new Pawn(6, 3, true);
        board[6][4] = new Pawn(6, 4, true);
        board[6][5] = new Pawn(6, 5, true);
        board[6][6] = new Pawn(6, 6, true);
        board[6][7] = new Pawn(6, 7, true);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    gridPane.add(piece, col, row);
                    piece.fitWidthProperty().bind(gridPane.widthProperty().divide(8));
                    piece.fitHeightProperty().bind(gridPane.heightProperty().divide(8));
                }
            }
        }

        Scene scene = new Scene(gridPane, TILE_SIZE * 8, TILE_SIZE * 8);

        primaryStage.setTitle("2D Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

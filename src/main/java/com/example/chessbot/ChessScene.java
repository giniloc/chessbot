package com.example.chessbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;

public class ChessScene extends Application {

    public static final int TILE_SIZE = 100;

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        // Add chess pieces
        String[][] board = {
                {"blackRook", "blackKnight", "blackBishop", "blackQueen", "blackKing", "blackBishop", "blackKnight", "blackRook"},
                {"blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn"},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn"},
                {"whiteRook", "whiteKnight", "whiteBishop", "whiteQueen", "whiteKing", "whiteBishop", "whiteKnight", "whiteRook"}
        };
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String piece = board[row][col];
                if (!piece.isEmpty()) {
                    Image image = new Image(getClass().getResourceAsStream("/" + piece + ".png"));
                    ImageView imageView = new ImageView(image);
                    imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(8));
                    imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(8));
                    gridPane.add(imageView, col, row);
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

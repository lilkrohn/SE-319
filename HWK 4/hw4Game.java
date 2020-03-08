package hw4;

/**
 * @author Lillian Krohn
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class hw4Game extends Application {

	private boolean isWinner = false;
	private char turn = 'X'; // start the game with x's turn
	private int turns = 0;

	private Label status = new Label("Turn: X");
	private String updateStatus = "";

	public static void main(String[] args) { // launch the game
		launch(args);
	}

	public Square[][] square = new Square[3][3];

	@Override
	public void start(Stage stage) {
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		Scene scene = new Scene(border, 410, 410);
		Button restartGame = new Button("Restart Game");
		restartGame.setOnAction(e -> resetGame());
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				grid.add(square[row][col] = new Square(), col, row);
			}
		}
		border.setCenter(grid);
		border.setBottom(restartGame);
		border.setTop(status);

		stage.setTitle("Tic-Tac-Toe");
		stage.setScene(scene);
		stage.show();
	}

	public void resetGame() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				square[i][j].setState(' ');
			}
		}
		turns = 0;
		isWinner = false;
		turn = 'X';
		status.setText("New Game. Turn: X");
	}

	public boolean winScenarios(char XorO) {
		if (square[0][0].getState() == XorO && square[1][1].getState() == XorO && square[2][2].getState() == XorO) {
			return true;
		}
		if (square[0][2].getState() == XorO && square[1][1].getState() == XorO && square[2][0].getState() == XorO) {
			return true;
		}
		if (square[0][0].getState() == XorO && square[0][1].getState() == XorO && square[0][2].getState() == XorO) {
			return true;
		}
		if (square[1][0].getState() == XorO && square[1][1].getState() == XorO && square[1][2].getState() == XorO) {
			return true;
		}
		if (square[2][0].getState() == XorO && square[2][1].getState() == XorO && square[2][2].getState() == XorO) {
			return true;
		}
		if (square[0][0].getState() == XorO && square[1][0].getState() == XorO && square[2][0].getState() == XorO) {
			return true;
		}
		if (square[0][1].getState() == XorO && square[1][1].getState() == XorO && square[2][1].getState() == XorO) {
			return true;
		}
		if (square[0][2].getState() == XorO && square[1][2].getState() == XorO && square[2][2].getState() == XorO) {
			return true;
		}

		return false;
	}

	public boolean isATie() { // checks for a tie game
		return turns == 9;
	}

	public class Square extends StackPane {
		private char state = ' ';
		private Image x;
		private Image o;
		private ImageView x2;
		private ImageView o2;

		public Square() {

			this.setStyle("-fx-border-color: #000000;-fx-background-color: #A0D5E8;"); // traditional board colors
			this.setPrefSize(150, 150);
			this.setOnMouseClicked(e -> clicked());

			x = new Image(getClass().getResource("x.jpg").toExternalForm());
			o = new Image(getClass().getResource("o.jpg").toExternalForm());

			x2 = new ImageView(x);
			o2 = new ImageView(o);

			x2.setFitWidth(120);
			x2.setPreserveRatio(true);

			o2.setFitWidth(120);
			o2.setPreserveRatio(true);

			this.getChildren().addAll(x2, o2);
			x2.setVisible(false);
			o2.setVisible(false);
		}

		public void setState(char turn) {
			if (turn == 'X') {
				x2.setVisible(true);
				o2.setVisible(false);
				turns++;
			} else if (turn == 'O') {
				o2.setVisible(true);
				x2.setVisible(false);
				turns++;
			} else {
				o2.setVisible(false);
				x2.setVisible(false);
			}
			state = turn;
		}

		public char getState() {
			return state;
		}

		private void clicked() {

			if (state == ' ' && isWinner == false) {
				setState(turn);
				if (winScenarios(turn)) {
					isWinner = true;
					updateStatus = turn + " wins!";
				} else if (isATie()) {
					isWinner = true;
					updateStatus = "Tie Game.";
				} else {
					if (turn == 'X') {
						turn = 'O';
						updateStatus = "Turn: " + turn;
					} else {
						turn = 'X';
						updateStatus = "Turn: " + turn;
					}

				}
				status.setText(updateStatus);
			}
		}
	}
}

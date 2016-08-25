package application;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	Board board;

	@Override
	public void start(Stage primaryStage) {
		try {
			board = new Board();

			BorderPane root = new BorderPane();
			root.setPrefSize(550, 550);
			Scene scene = new Scene(root, 550, 550);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.getChildren().add(board);
			initGame(root);

			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initGame(BorderPane root) {
		board.initBoard();
		board.drawBoard();
		
		Button b = new Button("GO!");
		b.setPrefSize(100, 100);
		root.setBottom(b);
		b.setOnMouseClicked(e -> {
			startGame();
			b.setVisible(false);
		});
	}

	private void startGame() {
		TimerService service = new TimerService();
		AtomicInteger count = new AtomicInteger(0);
		service.setCount(count.get());
		service.setPeriod(Duration.millis(500));
		service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
		Logic l = new Logic();
			@Override
			public void handle(WorkerStateEvent t) {
				System.out.println("Called : " + t.getSource().getValue() + " time(s)");
				l.setNeighbours(board);
				board.updateBoard();
				count.set((int) t.getSource().getValue());
			}
		});
		service.start();
		
		
		for (int i = 0; i < 100; i++) {

			service.restart();

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

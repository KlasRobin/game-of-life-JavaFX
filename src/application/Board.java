package application;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class Board extends Parent {

	private Cell[][] cells;
	private GridPane pane;

	public Board() {
		cells = new Cell[50][50];
		pane = new GridPane();
		
	}

	public void drawBoard() {

		for (int i = 0; i < cells[0].length; i++) {

			for (int j = 0; j < cells.length; j++) {
				cells[i][j] = new Cell(false, 0);
				pane.add(cells[i][j],i,j);
			}
			
		}
		
		getChildren().add(pane);
	}
	
	public void updateBoard() {
		pane.getChildren().remove(0, 99);
		
		for (int i = 0; i < cells[0].length; i++) {

			for (int j = 0; j < cells.length; j++) {
				pane.add(cells[i][j],i,j);
			}
			
		}
		
		
	}
	
	
	public void cleanBoard(){
		getChildren().remove(pane);
	}

	public void initBoard() {
		for (int i = 0; i < cells[0].length; i++) {

			for (int j = 0; j < cells.length; j++) {
				cells[i][j] = new Cell(false, 0);
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	public void wakeCell(int y, int x){
		
		cells[y][x].setAlive(true);
		
	}


}

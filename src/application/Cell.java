package application;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Parent {

	private boolean isAlive;
	private int neighbours;
	private Rectangle square;
	
	

	public Cell() {}

	public Cell(boolean isAlive, int neighbours) {
		super();
		this.isAlive = isAlive;
		this.neighbours = neighbours;
		square = new Rectangle(10, 10);
		square.setStroke(Color.BLACK);
		square.setFill((isAlive?Color.RED:Color.WHITE));
		getChildren().add(square);
		
		square.setOnMouseEntered(e->{
			
			if(e.isAltDown()){
				setAlive(true);
			}else if(e.isShiftDown()){
				setAlive(false);
			}
			
			
		});
		
	}

//	public void drawState(){
//		state
//	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		
		this.isAlive = isAlive;
		square.setFill((isAlive?Color.RED:Color.WHITE));
	}

	public int getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}

	
	public void addNeighbour(){
		neighbours++;
	}
	
	private void changeState(){
		if(isAlive){
			setAlive(false);
		}else{
			setAlive(true);
		}
		
	}

}
